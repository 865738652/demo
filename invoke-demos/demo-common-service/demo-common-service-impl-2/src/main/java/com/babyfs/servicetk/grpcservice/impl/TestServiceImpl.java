package com.babyfs.servicetk.grpcservice.impl;

import com.babyfs.servicetk.apicore.SingleItemReply;
import com.babyfs.servicetk.core.security.ICryptoService;
import com.babyfs.servicetk.grpcservice.api.TestService;
import com.babyfs.servicetk.grpcservice.api.msg.TestClass;
import com.babyfs.servicetk.grpcservice.api.msg.TestReply;
import com.babyfs.servicetk.grpcservice.api.msg.TestRequest;
import com.babyfs.servicetk.rpcserver.grpc.GrpcServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;


@GrpcServiceImpl
public class TestServiceImpl implements TestService {
    @Autowired
    ICryptoService cryptoService;

    @Override
    public TestReply test(TestRequest request) {
        TestReply reply = new TestReply();
        reply.setItem("222222Hello, your id is:" + request.getId());
        return reply;
    }

    @Override
    public SingleItemReply<TestClass> testSingleReply() {
        return null;
    }

    @Override
    public SingleItemReply<String> writeARedisKey(String key) {
        return null;
    }

    @Override
    public SingleItemReply<Long> readARedisKey(String key) {
        return null;
    }

    @Override
    public SingleItemReply<String> testEs(String key) {
        return null;
    }

    @Override
    public TestReply testException() {
        throw new RuntimeException("test exception");
    }

    @Override
    public String getServerUserInfo() {
        return "Test2";
    }
}
