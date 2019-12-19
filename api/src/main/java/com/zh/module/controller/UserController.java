package com.zh.module.controller;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.zh.module.annotation.UserLoginToken;
import com.zh.module.encrypt.MD5;
import com.zh.module.entity.Users;
import com.zh.module.service.UsersService;
import com.zh.module.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * @description:
 * @author: zhaohe
 * @create: 2019-10-17 18:53
 */
@RestController
@RequestMapping(value = "users")
public class UserController {

    @Autowired
    private UsersService usersService;

    @PostMapping(value = "register")
    public String register(){
        return "success-------" + DateUtils.getCurrentDateStr() + "---------" + usersService.selectAll(new HashMap<>());
    }

    @PostMapping("/login")
    public Object login(@RequestBody Users user) throws Exception {
        JSONObject jsonObject=new JSONObject();
        Users userForBase = usersService.login(user);
        if(userForBase==null){
            jsonObject.put("message","登录失败,用户不存在");
            return jsonObject;
        }else {
            if (!userForBase.getPassword().equals(MD5.getMd5(user.getPassword()))){
                jsonObject.put("message","登录失败,密码错误");
                return jsonObject;
            }else {
                String token = getToken(userForBase);
                jsonObject.put("token", token);
                jsonObject.put("user", userForBase);
                return jsonObject;
            }
        }
    }

    @UserLoginToken
    @GetMapping("/getMessage")
    public String getMessage(){
        return "你已通过验证";
    }

    public String getToken(Users user) {
        String token="";
        // 将 user id 保存到 token 里面
        token= JWT.create().withAudience(user.getId().toString())
        // 以 password 作为 token 的密钥
                .sign(Algorithm.HMAC256(user.getPassword()));
        return token;
    }
}
