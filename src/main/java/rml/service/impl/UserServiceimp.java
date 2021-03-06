package rml.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import rml.dao.UserMapper;
import rml.model.HttpResult;
import rml.model.LoginResultModle;
import rml.request.RegisterUserRequest;
import rml.request.SafeQuestionRequest;
import rml.request.UserRequest;
import rml.service.UserService;
import rml.util.Base64Util;

/**
 * Created by BBLink on 2017/5/3.
 */


@Service("userService")
public class UserServiceimp implements UserService {
    @Autowired
    UserMapper userMapper;


    /**
     * 注册
     *
     * @param registerUserRequest
     * @return
     */
    @Override
    public HttpResult registerUser(RegisterUserRequest registerUserRequest) {
        HttpResult httpResult = new HttpResult();

        if (registerUserRequest.getPassword() != null && registerUserRequest.getUserName() != null
                && registerUserRequest.getVerificationCode() != null && registerUserRequest.getMobile() != null) {
            if (userMapper.checkRegisterUser(registerUserRequest.getMobile()) >= 1) {//如果已经注册
                httpResult.setState("该手机已经注册");
                httpResult.setCode(-1);
            } else {
                String time = String.valueOf(System.currentTimeMillis());
                registerUserRequest.setCreateTime(time);
                registerUserRequest.setUpdateTime(time);
                int state = userMapper.registerUser(registerUserRequest);
                //检验验证码是否正确
                if (registerUserRequest.getVerificationCode().equals("0000")) {
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
            }

        } else {
            httpResult.setState("请确保用户名、密码、手机号及验证码填写完毕");
            httpResult.setCode(-1);
        }
        return httpResult;
    }

    /**
     * 登录
     *
     * @param request
     * @return
     */
    @Override
    public HttpResult<LoginResultModle> loginUser(UserRequest request) {
        HttpResult<LoginResultModle> httpResult = new HttpResult<LoginResultModle>();
        LoginResultModle loginResultModle;
        //先查用户表得到用户ID
        String userId = userMapper.getUserIdByMobile(request.getMobile());


        request.setUserId(userId);
        //拿用户ID得到password进行匹配
        if (userMapper.checkLogin(request) > 0) {//登录成功
            httpResult.setCode(200);
            loginResultModle = userMapper.getLoginResultModle(userId);
            loginResultModle.setUserId(userId);
            int i = userMapper.checkDevice(request);
            httpResult.setData(loginResultModle);
            if (i >= 1) {
                httpResult.setState("登录成功");
            } else {
                //在新设备登录
                //需要插入device号码

                boolean isNotFirstLogin = userMapper.isNotFirstLogin(userId);
                if (isNotFirstLogin) {
                    request.setRemark("其他设备");
                } else {
                    request.setRemark("第一次登陆的设备");
                }

                userMapper.insertDevice(request);
                httpResult.setState("登录成功，这是你在新设备第一次登录");
            }

        } else {
            httpResult.setState("未找到匹配的账户密码");
            httpResult.setCode(-1);

        }
        return httpResult;
    }

    @Override
    public HttpResult updataUserIcon(String userId, MultipartFile icon) {

        HttpResult httpResult = new HttpResult();
//        icon.getBytes()
        try {
            String iconStr = Base64Util.getImageStr(icon);
            userMapper.updataUserIcon(userId, iconStr);
            httpResult.setCode(200);
            httpResult.setState("更新头像成功");
        } catch (Exception e) {

            httpResult.setCode(-1);
            httpResult.setState(e.getMessage());
        }


        return httpResult;
    }

    @Override
    public HttpResult setSafeQuestion(SafeQuestionRequest safeQuestionRequest) {
        HttpResult httpResult = new HttpResult();
        if (userMapper.searchSafeQuestionIsExit(safeQuestionRequest.getUserId()) == 0) {
            if (userMapper.setSafeQuestion(safeQuestionRequest) != -1) {

                httpResult.setCode(200);
                httpResult.setState("设置安全问题成功");
                return httpResult;
            }

            httpResult.setCode(-1);
            httpResult.setState("设置安全问题失败");
            return httpResult;
        } else {

            httpResult.setCode(-1);
            httpResult.setState("安全问题已经设置");
            return httpResult;
        }

    }

    @Override
    public HttpResult<SafeQuestionRequest> getSafeQuestionById(String userId) {
        HttpResult<SafeQuestionRequest> httpResult = new HttpResult<SafeQuestionRequest>();
        httpResult.setData(userMapper.getSafeQuestionById(userId));
        return httpResult;
    }

    @Override
    public HttpResult setPassword(String userId, String password) {
        HttpResult httpResult = new HttpResult();
        try {
            userMapper.setPassword(userId, password);
            httpResult.setCode(200);
            httpResult.setState("更新密码成功");
        } catch (Exception e) {
            httpResult.setCode(-1);
            httpResult.setState("更新密码失败");
        }
        return httpResult;
    }
}