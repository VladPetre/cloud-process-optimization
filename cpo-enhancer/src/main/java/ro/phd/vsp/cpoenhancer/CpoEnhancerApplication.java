package ro.phd.vsp.cpoenhancer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"ro.phd.vsp.cpoenhancer.service", "ro.phd.vsp.cpoenhancer", "ro.phd.vsp.cpoenhancer.config"})
public class CpoEnhancerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CpoEnhancerApplication.class, args);
    }

}
