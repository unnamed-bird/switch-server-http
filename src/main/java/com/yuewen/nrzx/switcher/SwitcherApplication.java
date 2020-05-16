package com.yuewen.nrzx.switcher;

import com.qq.tars.spring.annotation.EnableTarsServer;
import com.qq.tars.spring.annotation.TarsHttpService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * project : switch-server
 *
 * @author liyuqi
 */
@SpringBootApplication
@EnableTarsServer
@EnableCaching
public class SwitcherApplication {

    public static void main(String[] args) {
        SpringApplication.run(SwitcherApplication.class, args);
    }
}
