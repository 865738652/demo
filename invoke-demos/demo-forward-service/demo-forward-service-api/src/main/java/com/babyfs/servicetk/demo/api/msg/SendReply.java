package com.babyfs.servicetk.demo.api.msg;


import com.babyfs.servicetk.apicore.SingleItemReply;

public class SendReply extends SingleItemReply<String> {
    public SendReply(String item) {
        super(item);
    }
}
