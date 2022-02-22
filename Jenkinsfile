pipeline {

    agent any
    environment {
        registry = "vladstep/${PARAM_MODULE}"
        imageTag = "latest"
        registryCredential = 'dockerhub_id'
        dockerImage = ''
    }

    parameters {
       choice(name: "PARAM_MODULE",
              choices: ["cpo-caller","cpo-gateway","cpo-localization","cpo-enhancer","cpo-processor","cpo-notification"],
              description: "Sample multi-choice parameter")
       string(name: "PARAM_BRANCH", defaultValue: "master", trim: true, description: "branch name")
    }

    stages {

        stage('clean workspace') {
            steps {
                cleanWs()
                sh '''
                  echo "build module: ${PARAM_MODULE}"
                  echo "from branch: ${PARAM_BRANCH}"
                '''
            }
        }
//         stage('Cloning Git') {
//             steps {
//             sh '''
//                 git clone https://github.com/VladPetre/cloud-process-optimization.git
//                '''
//             }
//         }
//
//         stage('maven build') {
//              steps {
//                 sh '''
//                     cd cloud-process-optimization/$params.MODULE
//                     mvn install
//                 '''
//              }
//         }
//
//         stage('Building docker image') {
//             steps {
//               sh '''
//                 cp cloud-process-optimization/$params.MODULE/Dockerfile ./Dockerfile
//               '''
//               script {
//                   dockerImage = docker.build registry + ":" + imageTag
//               }
//             }
//         }
//
//         stage('push docker image') {
//             steps {
//                 script {
//                     docker.withRegistry( '', registryCredential ) {
//                         dockerImage.push()
//                     }
//                 }
//             }
//         }
//
//
//         stage('Deploy App to Kube') {
//               steps {
//                 sh '''
//                     cp cloud-process-optimization/$params.MODULE/deployment-service.yml ./deployment-service.yml
//                 '''
//                 script {
//                 //!! kubernetes CD plugin should be different than 2.11.x
//                   kubernetesDeploy(configs: "deployment-service.yml", kubeconfigId: "kubeConfigLocal")
//                 }
//               }
//         }
//
//         stage('Cleaning up docker') {
//             steps {
//                 sh "docker rmi $registry:$imageTag"
//             }
//         }
    }
}