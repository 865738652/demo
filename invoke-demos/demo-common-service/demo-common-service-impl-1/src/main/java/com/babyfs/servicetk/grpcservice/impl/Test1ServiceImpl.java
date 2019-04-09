package com.babyfs.servicetk.grpcservice.impl;

import com.babyfs.servicetk.grpcservice.api.Test1Service;
import com.babyfs.servicetk.grpcservice.api.msg.TestReply;
import com.babyfs.servicetk.grpcservice.api.msg.TestRequest;
import com.babyfs.servicetk.rpcserver.grpc.GrpcServiceImpl;

@GrpcServiceImpl
public class Test1ServiceImpl implements Test1Service {
    @Override
    public TestReply test(TestRequest request) {
        return null;
    }
}
