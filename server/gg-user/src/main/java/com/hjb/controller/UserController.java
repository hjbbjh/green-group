package com.hjb.controller;

import com.hjb.common.constant.CodeInfo;
import com.hjb.dto.Msg;
import com.hjb.dto.UserLoginDto;
import com.hjb.dto.UserRegisterDto;
import com.hjb.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName: UserController
 * Description:
 * Created by hjb on 2019/3/29 10:27
 */
@RestController
@RequestMapping("/user")
@Api(value = "绿小萌用户服务接口", tags = "gg-user")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "用户注册", notes = "注册绿小萌用户")
    @ApiImplicitParam(name = "userRegisterDto", value = "用户注册信息", dataType = "UserRegisterDto")
    @PostMapping("/register")
    public Msg<String> register(@RequestBody UserRegisterDto userRegisterDto) {
        Msg<String> result = new Msg<>(CodeInfo.SUCCESS);
        userService.register(userRegisterDto);
        return result;
    }

    @ApiOperation(value = "用户登录", notes = "登录绿小萌")
    @ApiImplicitParam(name = "userLoginDto", value = "用户登录信息", dataType = "UserLoginDto")
    @PostMapping("/login")
    public Msg<String> login(@RequestBody UserLoginDto userLoginDto) {
        Msg<String> result = new Msg<>(CodeInfo.SUCCESS);
        return result;
    }

    @GetMapping("/hello")
    public Msg<String> hello() {
        Msg<String> result = new Msg<>(CodeInfo.SUCCESS);
        return result;
    }


}
