package rml.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rml.dao.UserMapper;
import rml.model.HttpResult;
import rml.model.RegisterUserModle;
import rml.service.UserService;

/**
 * Created by BBLink on 2017/5/3.
 */


@Service("userService")
public class UserServiceimp implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public HttpResult registerUser(RegisterUserModle registerUserModle) {
        HttpResult httpResult = new HttpResult();

        if (registerUserModle.getPassword() != null && registerUserModle.getUserName() != null && registerUserModle.getVerificationCode() !=null) {
            String time = String.valueOf(System.currentTimeMillis());
            registerUserModle.setCreateTime(time);
            registerUserModle.setUpdateTime(time);
            int state = userMapper.registerUser(registerUserModle);
            //检验验证码是否正确
            if (registerUserModle.getVerificationCode().equals("0000")) {
                if (1 == state) {
                    httpResult.setState("注册成功");
                    httpResult.setCode(200);
                } else {
                    httpResult.setState("注册失败，请稍后尝试");
                    httpResult.setCode(-1);
                }
            } else {
                httpResult.setState("请输入正确的验证码");
                httpResult.setCode(-1);
            }
        } else {
            httpResult.setState("用户名或者密码及验证码不能为空");
            httpResult.setCode(-1);
        }
        return httpResult;
    }
}