package com.babyfs.servicetk.grpcservice.impl;

import com.babyfs.servicetk.core.es.BaseESServcieImpl;
import org.elasticsearch.client.Client;
import org.springframework.stereotype.Component;

public class EsTestServiceImpl extends BaseESServcieImpl {

    public EsTestServiceImpl() {
        super("retailers.goods", "retailers.goods");
    }

    public Client getClient() {
        return this.client;
    }
}
