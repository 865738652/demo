package com.babyfs.servicetk.demo.clientapp.impl;

import com.babyfs.servicetk.demo.api.FirstLevelService;
import com.babyfs.servicetk.demo.api.msg.SendReply;
import com.babyfs.servicetk.demo.api.msg.SendRequest;
import com.babyfs.servicetk.grpcservice.api.TestService;
import com.babyfs.servicetk.grpcservice.api.msg.TestRequest;
import com.babyfs.servicetk.rpcserver.grpc.GrpcServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

@GrpcServiceImpl
public class FirstLevelServiceImpl implements FirstLevelService {

    @Autowired
    TestService testService;

    @Override
    public SendReply send(SendRequest request) {
        TestRequest testRequest = new TestRequest();
        testRequest.setName(request.getMsg());
        System.out.println("first level invoke");
        long start = System.currentTimeMillis();
        String item = testService.test(testRequest).getItem();
        System.out.println("first level invoke" + (System.currentTimeMillis() - start) + "ms");
        return new SendReply("firstlevel-invoke" + item);
    }
}
