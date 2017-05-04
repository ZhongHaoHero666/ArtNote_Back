package rml.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import rml.model.HttpResult;
import rml.model.LoginResultModle;
import rml.request.RegisterUserRequest;
import rml.request.UserRequest;
import rml.service.UserService;

/**
 * Created by BBLink on 2017/5/3.
 */

@Controller
@RequestMapping("/User")
public class UserController {


    @Autowired
    UserService userService;


    @RequestMapping("/register")
    @ResponseBody
    public HttpResult registerUser(RegisterUserRequest registerUserRequest) {
        HttpResult httpResult;
        httpResult = userService.registerUser(registerUserRequest);

        return httpResult;
    }

    @RequestMapping("/login")
    @ResponseBody
    public HttpResult loginUser(UserRequest request) {

        HttpResult<LoginResultModle> httpResult = userService.loginUser(request);


        return httpResult;
    }
}
