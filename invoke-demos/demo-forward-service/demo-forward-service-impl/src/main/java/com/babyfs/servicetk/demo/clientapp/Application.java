package com.babyfs.servicetk.demo.clientapp;

import com.babyfs.servicetk.core.EnableAllTool;
import com.babyfs.servicetk.grpcapicore.ClientBuilder;
import com.babyfs.servicetk.grpcservice.api.TestService;
import com.babyfs.servicetk.rpcserver.grpc.EnableGrpc;
import com.babyfs.servicetk.rpcserver.grpc.client.Client;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author kata
 */
@SpringBootApplication
@EnableGrpc(scanBaseImplPackages = {"com.babyfs.servicetk.demo.clientapp.impl"})
@Client(TestService.class)
public class Application {

    public static void main(String[] args) {
        new SpringApplicationBuilder(Application.class)
                .web(WebApplicationType.NONE)
                .run(args);
    }
}
