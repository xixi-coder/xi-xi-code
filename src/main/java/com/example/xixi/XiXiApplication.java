package com.example.xixi;

import com.example.xixi.binfashizhan.chapter02.CachedServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class XiXiApplication {

    public static void main(String[] args) {
        SpringApplication.run(XiXiApplication.class, args);
    }

//    @Bean
//    public ServletRegistrationBean servletRegistrationBean() {
//        return new ServletRegistrationBean(new CachedServlet(), "/*");
//    }

    @Bean
    public RestTemplate restTemplate() {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        //60s
        requestFactory.setConnectTimeout(600*1000);
        requestFactory.setReadTimeout(600*1000);
        return new RestTemplate(requestFactory);
    }
}
