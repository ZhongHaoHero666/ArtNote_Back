package rml.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import rml.model.HttpResult;
import rml.model.LoginResultModle;
import rml.model.TestBean;
import rml.request.RegisterUserRequest;
import rml.request.UserRequest;
import rml.service.UserService;
import rml.util.CreateFileUtil;
import rml.util.OkHttpUtil;

import javax.annotation.Resource;
import javax.swing.text.AbstractDocument;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

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

    @RequestMapping(value = "/upPicture")
    @ResponseBody
    public HttpResult uploaditem(String userId, @RequestParam MultipartFile upload) { //此名称=表单文件上传项的name
        HttpResult httpResult = null;


        String filePath = "H:/" + userId + "/icon.jpg";
        try {
            CreateFileUtil.createFile(filePath);
            upload.transferTo(new File(filePath));

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(upload.getName());
        return httpResult;
    }

//    @Resource(name = "testBean") TestBean testBean;
//
//    @RequestMapping(value = "/test")
//    @ResponseBody
//    public String test(String testContet) {
//
//        testBean.sayHello();
//
//
//        return "";
//    }
}
