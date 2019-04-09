package com.babyfs.demos.controllers;

import com.babyfs.demos.msg.Info;
import com.babyfs.servicetk.grpcservice.api.TestService;
import com.babyfs.starters.contract.user.Token;
import com.babyfs.starters.contract.user.UserContext;
import com.babyfs.starters.webstarter.validation.TokenValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/demo")
public class DemoTestController {

    @Autowired
    private TestService testService;


    @RequestMapping("/info")
    @ResponseBody
    @TokenValidate
    public Info info() {
        Token userContext = UserContext.get();
        if (userContext != null) {
            return new Info(1, userContext.getUserEntity().getName());
        }
        return new Info(1, "unknown");
    }

    @RequestMapping("/test")
    @ResponseBody
    public Info invokeTest() {


        String serverUserInfo = testService.getServerUserInfo();
        return new Info(1, "unknown");
    }
}
