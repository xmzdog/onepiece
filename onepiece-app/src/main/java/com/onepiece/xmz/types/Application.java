package com.onepiece.xmz.types;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created with IntelliJ IDEA.
 * User: xiangmz
 * Date: 2025/4/23
 * Time: 17:34
 * Description: No Description
 */
@SpringBootApplication
@Configurable
@MapperScan("com.onepiece.xmz.dao")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
