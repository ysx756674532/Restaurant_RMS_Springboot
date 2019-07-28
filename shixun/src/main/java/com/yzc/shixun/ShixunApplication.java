package com.yzc.shixun;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.yzc.shixun.dao")
public class ShixunApplication {

     public static void main(String[] args) {
        SpringApplication.run(ShixunApplication.class, args);
     }

}
