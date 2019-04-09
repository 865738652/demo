package com.babyfs.servicetk.grpcservice.api;

import com.babyfs.servicetk.apicore.SingleItemReply;
import com.babyfs.servicetk.grpcservice.api.msg.TestClass;
import com.babyfs.servicetk.grpcservice.api.msg.TestReply;
import com.babyfs.servicetk.grpcservice.api.msg.TestRequest;

public interface TestService {
    TestReply test(TestRequest request);

    TestReply testException();

    SingleItemReply<TestClass> testSingleReply();

    SingleItemReply<String> writeARedisKey(String key);

    SingleItemReply<Long> readARedisKey(String key);

    SingleItemReply<String> testEs(String key);

    String getServerUserInfo();
}
