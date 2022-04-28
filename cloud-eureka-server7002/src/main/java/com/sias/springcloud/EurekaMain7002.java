package com.sias.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author Edgar
 * @create 2022-04-28 17:43
 * @faction:
 */

@SpringBootApplication
/*把者模块注入进去，小的到大的，用的也是不一眼的*/
@EnableEurekaServer
public class EurekaMain7002 {

    public static void main(String[] args) {
        SpringApplication.run(EurekaMain7002.class,args);
    }
}
