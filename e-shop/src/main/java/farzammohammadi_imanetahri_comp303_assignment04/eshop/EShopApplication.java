package farzammohammadi_imanetahri_comp303_assignment04.eshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(EShopApplication.class, args);
    }

}
