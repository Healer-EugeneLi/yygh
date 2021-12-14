package com.eugeneli.yygh.hosp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @ClassName: ServiceHospApplication
 * @Description:
 * @Author EugeneLi
 * @Date: 2021/8/18
 * @Time: 20:59
 */

@SpringBootApplication
@ComponentScan(basePackages = "com.eugeneli")
public class ServiceHospApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceHospApplication.class, args);
    }
}
