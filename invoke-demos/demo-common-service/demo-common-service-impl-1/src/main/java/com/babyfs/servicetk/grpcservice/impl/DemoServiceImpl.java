package com.babyfs.servicetk.grpcservice.impl;

import com.babyfs.servicetk.demo.DemoServiceGrpc;
import com.babyfs.servicetk.demo.SayReply;
import com.babyfs.servicetk.demo.SayRequest;
import com.babyfs.servicetk.rpcserver.grpc.GrpcServer;
import io.grpc.stub.StreamObserver;

/**
 * @author kata
 */
@GrpcServer
public class DemoServiceImpl extends DemoServiceGrpc.DemoServiceImplBase {
    @Override
    public void say(SayRequest request, StreamObserver<SayReply> responseObserver) {
        String msg = request.getMsg();
        responseObserver.onNext(SayReply.newBuilder().setResult("this is 11111, " + msg + ", accepted").build());
        responseObserver.onCompleted();
    }
}
