package rml.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import rml.model.HttpResult;
import rml.model.RegisterUserModle;
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
    public HttpResult registerUser(RegisterUserModle registerUserModle) {
        HttpResult httpResult;
        httpResult = userService.registerUser(registerUserModle);


        return httpResult;

    }
}
