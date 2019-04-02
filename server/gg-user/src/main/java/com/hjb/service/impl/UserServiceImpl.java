package com.hjb.service.impl;

import com.hjb.common.UserState;
import com.hjb.common.constant.CodeInfo;
import com.hjb.common.exception.GreenGroupException;
import com.hjb.config.Config;
import com.hjb.dao.entity.GgUser;
import com.hjb.dao.repository.GgUserRepository;
import com.hjb.dto.Msg;
import com.hjb.dto.UserLoginDto;
import com.hjb.dto.UserRegisterDto;
import com.hjb.service.UserService;
import com.hjb.utils.KeycloakAdminUtil;
import org.aspectj.apache.bcel.classfile.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * ClassName: UserServiceImpl
 * Description:
 * Created by hjb on 2019/3/29 17:50
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private KeycloakAdminUtil keycloakAdminUtil;
    @Autowired
    private Config config;
    @Autowired
    private GgUserRepository ggUserRepository;

    @Override
    public Msg<String> register(UserRegisterDto userRegisterDto) {
        Msg<String> result = new Msg<>(CodeInfo.SUCCESS);
        Random random = new Random();
        GgUser ggUser = new GgUser();
        ggUser.setPhone(userRegisterDto.getPhone());
        ggUser.setPassword(userRegisterDto.getPassword());
        ggUser.setHeadPicPath(config.getHeadDefaultPic());
        ggUser.setNickName("用户"+random.nextLong());
        ggUser.setUserState(UserState.NORMAL);
        ggUser.setRoles(userRegisterDto.getRoles());
        ggUserRepository.save(ggUser);
        Msg<String> keycloakCreateUserResult = keycloakAdminUtil.createUser(userRegisterDto);
        if(!keycloakCreateUserResult.checkSuccess()){
            ggUserRepository.delete(ggUser);
            throw new GreenGroupException(keycloakCreateUserResult);
        }
        return result;
    }

    @Override
    public Msg<String> login(UserLoginDto userLoginDto) {
        Msg<String> result = new Msg<>(CodeInfo.SUCCESS);
        GgUser ggUser = ggUserRepository.findByPhone(userLoginDto.getPhone());
        if(null == ggUser){
            throw new GreenGroupException(new Msg<>(CodeInfo.USER_NOT_EXSIST));
        }
        result.setData(keycloakAdminUtil.getUserAccessToken(userLoginDto.getPhone(),userLoginDto.getPassword()));
        return result;
    }
}
