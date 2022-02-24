package com.example.xixi;

import com.example.xixi.binfashizhan.chapter02.CachedServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class XiXiApplication {

    public static void main(String[] args) {
        SpringApplication.run(XiXiApplication.class, args);
    }

    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        return new ServletRegistrationBean(new CachedServlet(), "/*");
    }
}
