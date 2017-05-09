package rml.service;

import org.springframework.web.multipart.MultipartFile;
import rml.model.HttpResult;
import rml.model.LoginResultModle;
import rml.request.RegisterUserRequest;
import rml.request.UserRequest;

/**
 * Created by BBLink on 2017/5/3.
 */
public interface UserService {

    //登录

    //注册  默认验证码是0000
    HttpResult registerUser(RegisterUserRequest registerUserRequest);

    //登录
    HttpResult<LoginResultModle> loginUser(UserRequest request);//登录

    //上传头像
    HttpResult updataUserIcon(String userId, MultipartFile icon);

}
