package com.gj.gejigeji;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

import java.util.TimeZone;

@EnableAspectJAutoProxy
@EnableScheduling
@SpringBootApplication
public class GejigejiApplication {

    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("GMT+8:00"));
        SpringApplication.run(GejigejiApplication.class, args);
    }
}
