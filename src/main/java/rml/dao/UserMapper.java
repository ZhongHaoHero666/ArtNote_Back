package rml.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import rml.model.LoginResultModle;
import rml.request.RegisterUserRequest;
import rml.request.SafeQuestionRequest;
import rml.request.UserRequest;

/**
 * Created by BBLink on 2017/5/3.
 */
@Service("userMapping")
public interface UserMapper {

    int checkRegisterUser(String mobile);

    //注册用户
    int registerUser(RegisterUserRequest registerUserRequest);

    //检测登录
    int checkLogin(UserRequest request);


    //根据手机号得到用户ID
    String getUserIdByMobile(String mobile);


    //登陆成功后，根据用户id得到想要的信息
    LoginResultModle getLoginResultModle(String userId);

    //查看是该帐号是否在新设备登录
    int checkDevice(UserRequest request);

    //插入与帐号userid关联的新设备
    void insertDevice(UserRequest request);

    //根据用户ID 查看是否是第一次登录
    boolean isNotFirstLogin(String userId);

    boolean updataUserIcon(@Param("userId") String userId, @Param("icon") String icon);

    //设置密保问题
    int setSafeQuestion(SafeQuestionRequest safeQuestionRequest);

    int searchSafeQuestionIsExit(String userId);

    //根据Id获得密保问题
    SafeQuestionRequest getSafeQuestionById(String userId);

    //修改密码
    void setPassword(@Param("userId")String userId,@Param("password") String password);
}
