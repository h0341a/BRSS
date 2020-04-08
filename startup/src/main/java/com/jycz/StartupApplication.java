package com.jycz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author ling
 * @data 2020/4/4 16:37
 */
@SpringBootApplication
@MapperScan("com.jycz.common.dao")
public class StartupApplication {
    public static void main(String[] args){
        SpringApplication.run(StartupApplication.class, args);
    }
}
