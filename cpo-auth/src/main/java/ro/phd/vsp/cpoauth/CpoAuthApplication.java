package ro.phd.vsp.cpoauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"ro.phd.vsp.cpoauth.service",
    "ro.phd.vsp.cpoauth.controller", "ro.phd.vsp.cpoauth.config",
})
public class CpoAuthApplication {

  public static void main(String[] args) {
    SpringApplication.run(CpoAuthApplication.class, args);
  }

}
