package com.hjb.service;

import com.hjb.dto.Msg;
import com.hjb.dto.UserLoginDto;
import com.hjb.dto.UserRegisterDto;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * ClassName: UserService
 * Description:
 * Created by hjb on 2019/3/29 17:49
 */
public interface UserService {

    Msg<String> register( UserRegisterDto userRegisterDto);

    Msg<String> login(UserLoginDto userLoginDto);

}
