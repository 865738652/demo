package com.babyfs.servicetk.grpcservice.api;

import com.babyfs.servicetk.grpcservice.api.msg.TestReply;
import com.babyfs.servicetk.grpcservice.api.msg.TestRequest;

public interface Test1Service {
    TestReply test(TestRequest request);
}
