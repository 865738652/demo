package com.babyfs.servicetk.grpcservice.impl;

import com.babyfs.servicetk.apicore.SingleItemReply;
import com.babyfs.servicetk.core.security.ICryptoService;
import com.babyfs.servicetk.grpcservice.api.TestService;
import com.babyfs.servicetk.grpcservice.api.msg.TestClass;
import com.babyfs.servicetk.grpcservice.api.msg.TestReply;
import com.babyfs.servicetk.grpcservice.api.msg.TestRequest;
import com.babyfs.servicetk.rpcserver.grpc.GrpcServiceImpl;
import com.babyfs.starters.contract.user.Token;
import com.babyfs.starters.contract.user.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;


@GrpcServiceImpl
public class TestServiceImpl implements TestService {


    @Override
    public TestReply test(TestRequest request) {
        TestReply reply = new TestReply();
        reply.setItem("11111Hello, your id is:" + request.getId());
        return reply;
    }

    @Override
    public TestReply testException() {
        throw new RuntimeException("test exception");
    }

    @Override
    public SingleItemReply<TestClass> testSingleReply() {
        return new SingleItemReply<>(new TestClass("test"));
    }

//    @Override
//    public SingleItemReply<String> writeARedisKey(String key) {
//        this.redisTemplate.opsForValue().set(key, (long)key.hashCode());
//        return new SingleItemReply<>("success");
//    }
//
//    @Override
//    public SingleItemReply<Long> readARedisKey(String key) {
//        return new SingleItemReply<>(this.redisTemplate.opsForValue().get(key));
//    }
//
//    @Override
//    public SingleItemReply<String> testEs(String key) {
//        return new SingleItemReply<>(esTestService.getClient().getClass().getName().toString());
//    }

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
    public String getServerUserInfo() {
        Token userContext = UserContext.get();
        if (userContext != null) {
            return userContext.getUserEntity().getName();
        }
        return "unknown";
    }
}
