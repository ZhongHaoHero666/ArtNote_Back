package rml.service;

import rml.model.HttpResult;
import rml.model.RegisterUserModle;

/**
 * Created by BBLink on 2017/5/3.
 */
public interface UserService {

    //登录

    //注册  默认验证码是0000
    HttpResult registerUser(RegisterUserModle registerUserModle);

}
