package com.babyfs.servicetk.grpcservice;

import com.babyfs.servicetk.core.EnableAllTool;
import com.babyfs.servicetk.core.es.EnableES;
import com.babyfs.servicetk.core.redis.RedisTemplateAutoConfiguration;
import com.babyfs.servicetk.rpcserver.grpc.EnableGrpc;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
@EnableGrpc(scanBaseImplPackages = {"com.babyfs.servicetk.grpcservice.impl"})
public class Application {
    public static void main(String[] args) {
        new SpringApplicationBuilder(Application.class)
                .web(WebApplicationType.NONE)
                .run(args);
    }
}