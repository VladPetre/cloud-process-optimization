pipeline {

    agent any
    environment {
        registry = "cpo/${PARAM_MODULE}"
        registryRoot = "http://foxtrot.lan:5000"
        registryClr = "foxtrot.lan:5000/cpo/${PARAM_MODULE}"
        imageTag = "latest"
        dockerImage = ''
    }

    parameters {
        choice(name: "PARAM_MODULE",
                choices: ["cpo-caller", "cpo-enhancer", "cpo-gateway", "cpo-localization", "cpo-notification", "cpo-processor"],
                description: "Module to be built")
        string(name: "PARAM_BRANCH", defaultValue: "main", trim: true, description: "branch name")
        booleanParam name: 'SHOULD_DEPLOY', defaultValue: true, description: "should deploy"
    }

    stages {

        stage('Maven build') {
            steps {
                git branch: params.PARAM_BRANCH, poll: false, url: 'https://github.com/VladPetre/cloud-process-optimization.git'
                dir("${params.PARAM_MODULE}") {
                    sh '''
                      mvn clean install -DskipTests
                  '''
                }
            }
        }

        stage('Docker Build') {
            steps {
                dir("${params.PARAM_MODULE}") {
                    script {
                        dockerImage = docker.build registry + ":" + imageTag
                    }
                }
            }
        }

        stage('Docker push') {
            steps {
                script {
                    docker.withRegistry(registryRoot) {
                        dockerImage.push()
                    }
                }
            }
        }

        stage('Kube Deploy') {
            when {
                beforeAgent true
                expression {
                    return params.SHOULD_DEPLOY
                }
            }

            steps {
                withKubeConfig([credentialsId: 'k3s-sierra-config', serverUrl: 'https://192.168.7.153:6443']) {
                    dir("${params.PARAM_MODULE}") {
                    sh '''
                      deployName="$(grep 'name:' deployment.yaml | head -1 | awk -F: '{print $2}' | tr -d " ")"
                      kubectl -n cpo delete deployment $deployName
                      sleep 5
                      kubectl apply -f deployment.yaml
                      kubectl -n cpo rollout status deployment $deployName --watch --timeout=5m
                    '''
                    }
                }
            }
        }
    }

    post {
        always {
            sh "docker rmi $registryClr:$imageTag"
            cleanWs()
        }
    }
}