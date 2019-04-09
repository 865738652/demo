package com.babyfs.demos;


import com.babyfs.servicetk.core.grpcclient.Client;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author kata
 */
@SpringBootApplication
@Client(com.babyfs.servicetk.grpcservice.api.TestService.class)
public class WebApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(WebApplication.class).
                web(WebApplicationType.SERVLET).run(args);
    }
}
