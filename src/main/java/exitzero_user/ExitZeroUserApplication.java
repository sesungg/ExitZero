package exitzero_user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
public class ExitZeroUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExitZeroUserApplication.class, args);
    }

}
