package com.example.mybatisredisdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author QC
 * @data 2021-7-23
 */
@SpringBootApplication
@MapperScan("com.example.mybatisredisdemo.mapper")
public class MybatisRedisDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisRedisDemoApplication.class, args);
    }

}