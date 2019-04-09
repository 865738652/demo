package com.babyfs.servicetk.demos.grpcservice.client;

import com.alibaba.fastjson.TypeReference;
import com.babyfs.servicetk.apicore.SingleItemReply;
import com.babyfs.servicetk.demo.DemoServiceGrpc;
import com.babyfs.servicetk.demo.SayReply;
import com.babyfs.servicetk.demo.SayRequest;
import com.babyfs.servicetk.demo.api.FirstLevelService;
import com.babyfs.servicetk.demo.api.msg.SendReply;
import com.babyfs.servicetk.demo.api.msg.SendRequest;
import com.babyfs.servicetk.grpcapicore.ClientBuilder;
import com.babyfs.servicetk.grpcapicore.ProxyBuilder;
import com.babyfs.servicetk.grpcservice.api.Test1Service;
import com.babyfs.servicetk.grpcservice.api.TestService;
import com.babyfs.servicetk.grpcservice.api.msg.TestClass;
import com.babyfs.servicetk.grpcservice.api.msg.TestReply;
import com.babyfs.servicetk.grpcservice.api.msg.TestRequest;
import com.google.common.base.Stopwatch;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.concurrent.*;

public class TestConnect {
    public static void main(String[] args) throws Exception {
        testFastJson();
//        testFastJson();
//        TestRequest testRequest = new TestRequest();
//        testRequest.setId(1);
//
//        // todo:the first invoke cost too long
//        Stopwatch stopwatch = Stopwatch.createStarted();
//        TestReply test = testService.test(testRequest);
//        System.out.println(test.getItem() + ", it cost:" + stopwatch.stop().elapsed().toMillis() + "ms");
//
//
//        testRequest.setId(2);
//
//        //ok, this is a second invoke;
//        stopwatch.reset();
//        stopwatch.start();
//        TestReply test1 = testService.test(testRequest);
//        System.out.println(test1.getItem() + ", it cost:" + stopwatch.stop().elapsed().toMillis() + "ms");

//        stopwatch.reset().start();
//        testService.testException();
//        System.out.println(test1.getItem() + ", it cost:" + stopwatch.stop().elapsed().toMillis() + "ms");

    }

    private static void testRedisCluster() {
        TestService testService = ProxyBuilder.buildProxy(TestService.class);
        testService.writeARedisKey("kata");
        SingleItemReply<Long> kata = testService.readARedisKey("kata");
        System.out.println(kata.getItem());
    }

    private static void testES() {
        TestService testService = ProxyBuilder.buildProxy(TestService.class);
        System.out.println(testService.testEs("ff").getItem());
    }

    private static void getGenericInfo() {
        Type genericSuperclass = TestReply.class.getGenericSuperclass();
        System.out.println(genericSuperclass.getTypeName());

    }

    private static void testFastJson() {
        TestService testService = ProxyBuilder.buildProxy(TestService.class);
        System.out.println(testService.testSingleReply().getItem().getTest());
    }

    private static void testMultiInterface() {
        TestService testService = ProxyBuilder.buildProxy(TestService.class);
        System.out.println(testService.testSingleReply().getItem().getTest());
        Test1Service test1Service = ProxyBuilder.buildProxy(Test1Service.class);
        test1Service.test(new TestRequest());
        try {
            System.in.read();
        } catch (IOException e) {
        }
    }

    private static void testMultiServer() throws Exception {
        TestService testService = ClientBuilder.buildClient(TestService.class);
        Test1Service test1Service = ClientBuilder.buildClient(Test1Service.class);
        FirstLevelService firstLevelService = ClientBuilder.buildClient(FirstLevelService.class);
        DemoServiceGrpc.DemoServiceBlockingStub stub = ClientBuilder.buildClient(DemoServiceGrpc.DemoServiceBlockingStub.class);

        //Common Thread Pool
        ExecutorService pool = new ThreadPoolExecutor(10, 10,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(10), new ThreadFactoryBuilder()
                .setNameFormat("demo-pool-%d").build(),
                new ThreadPoolExecutor.AbortPolicy());
        String in = "g";
        String quitStr = "q";
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        while (!quitStr.equalsIgnoreCase(in)) {
//            TimeUnit.SECONDS.sleep(1);
            in = bufferedReader.readLine();
            System.out.println("start a new run!");

            for (int i = 0; i < 10; i++) {
                final int item = i;
                try {
                    pool.execute(() -> {
                        TestRequest testRequest = new TestRequest();
                        testRequest.setId(item);
                        Stopwatch stopwatch = Stopwatch.createStarted();
                        TestReply test = testService.test(testRequest);
                        System.out.println("invoke test service:" + test.getItem() + ", it cost:" + stopwatch.stop().elapsed().toMillis() + "ms");
                        stopwatch.reset().start();
                        test1Service.test(new TestRequest());
                        System.out.println("invoke test1 service: cost " + stopwatch.stop().elapsed().toMillis() + "ms");
                        stopwatch.reset().start();
                        SayReply hahaha = stub.say(SayRequest.newBuilder().setMsg("hahaha").build());
                        System.out.println("invoke DemoServiceGrpc stub: cost " + stopwatch.stop().elapsed().toMillis() + "ms, info:" + hahaha.getResult());
                        SendRequest sr = new SendRequest();
                        sr.setMsg("fasdfdsfsdafsafdf");
                        stopwatch.reset().start();
                        SendReply send = firstLevelService.send(sr);
                        System.out.println("invoke firstlevel service: cost " + stopwatch.stop().elapsed().toMillis() + "ms, info:" + send.getItem());
                        System.out.println("batch " + item + " has ended");
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        }
    }
}
