package com.babyfs.servicetk.grpcservice.api.msg;

import java.io.Serializable;

public class TestClass implements Serializable {
    String test;

    public TestClass(String test) {
        this.test = test;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }
}
