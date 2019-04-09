package com.babyfs.servicetk.demo.api;

import com.babyfs.servicetk.demo.api.msg.SendReply;
import com.babyfs.servicetk.demo.api.msg.SendRequest;

public interface FirstLevelService {
    SendReply send(SendRequest request);
}
