package com.babyfs.servicetk.grpcservice.api.msg;

import com.babyfs.servicetk.apicore.Request;

public class TestRequest extends Request {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
