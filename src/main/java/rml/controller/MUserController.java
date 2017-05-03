package rml.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import rml.dto.AccessToken;
import rml.dto.ReturnJson;
import rml.dto.ReturnUser;
import rml.model.User;
import rml.model.UserWeapp;
import rml.request.UserRequest;
import rml.service.JsonMapper;
import rml.service.MUserServiceI;
import rml.util.OkHttpUtil;

@Controller
@RequestMapping("/User")
public class MUserController {

    public static final String appid = "wxa97f30ee6cd9ffc0";
    public static final String GRANT_TYPE = "authorization_code";
    public static final String secret = "96b0d8c25e65644476f1657991429bbb";
    public static final String TOKEN_URL = "https://api.weixin.qq.com/sns/jscode2session";

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    private MUserServiceI muserService;

    private JsonMapper jsonMapper;
//


    public JsonMapper getJsonMapper() {
        return jsonMapper;
    }

    @Autowired
    public void setJsonMapper(JsonMapper jsonMapper) {
        this.jsonMapper = jsonMapper;
    }


    public MUserServiceI getMuserService() {
        return muserService;
    }

    @Autowired
    public void setMuserService(MUserServiceI muserService) {
        this.muserService = muserService;
    }

    @RequestMapping(value = "/listUser")
    public String listUser(HttpServletRequest request) {

        List<User> list = muserService.getAll();
        request.setAttribute("userlist", list);
        return "listUser";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public ReturnJson addUser(UserRequest userRequest) {
        ReturnJson json = new ReturnJson();
        if (StringUtils.isEmpty(userRequest.getMobile())) {
            json.setErrorCode(1001);
            json.setReturnMessage("输入参数为空或者输入参数不正确");
            json.setServerStatus(1);
        }
        String id = UUID.randomUUID().toString().replaceAll("-", "");
        User user = new User();
        user.setUserId(id);
        user.setCreateTime(System.currentTimeMillis());
        user.setLoginType(userRequest.getLoginType());
        user.setPwd(userRequest.getPwd());
        user.setUserName(userRequest.getMobile());
        user.setLastLoginTime(System.currentTimeMillis());
        user.setStatus("NORMAL");
        try {
            muserService.insert(user);
        } catch (Exception e) {
            logger.error(e.getMessage());
            json.setReturnMessage("服务器错误");
            json.setServerStatus(2);
            json.setErrorCode(1002);
            return json;
        }
        json.setReturnValue(user.getUserId());
        json.setReturnMessage("调用成功");
        return json;
    }

    @RequestMapping(value = "/appLogin", method = RequestMethod.POST)
    @ResponseBody
    public ReturnUser appLogin(String code) {
        ReturnUser json = new ReturnUser();
        if (code == null) {
            json.setErrorCode(1001);
            json.setReturnMessage("输入参数为空");
            json.setServerStatus(1);
        }

        try {

            AccessToken dto = null;
            HashMap<String, String> params = new HashMap<String, String>();
            params.put("appid", appid);
            params.put("secret", secret);
            params.put("js_code", code);
            params.put("grant_type", GRANT_TYPE);
            String tokenStr = OkHttpUtil.run(TOKEN_URL, null, params);
            HashMap<String, Object> tokenInfo = jsonMapper.toMap(tokenStr);

            if (tokenInfo != null && !tokenInfo.containsKey("errcode")) {
                dto = new AccessToken();

                dto.setOpenid(String.valueOf(tokenInfo.get("openid")));

                dto.setSessionKey(String.valueOf(tokenInfo.get("session_key")));
                // dto.setScope(String.valueOf(tokenInfo.get("scope")));
                if (dto != null) {
                    UserWeapp userWeapp = new UserWeapp();

                    muserService.insertWeapp(userWeapp);
                }
            } else {
                json.setErrorCode(1001);
                json.setReturnMessage("微信请求失败");
                json.setServerStatus(1);
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return json;
    }

}
