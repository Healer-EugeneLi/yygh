package com.eugeneli.yygh.cmn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @ClassName: ServiceCmnApplication
 * @Description:
 * @Author EugeneLi
 * @Date: 2021/8/22
 * @Time: 22:50
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.eugeneli"})
public class ServiceCmnApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceCmnApplication.class, args);
    }
}
