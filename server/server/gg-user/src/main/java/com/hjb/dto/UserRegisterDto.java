package com.hjb.dto;

import com.hjb.common.RoleType;
import lombok.Data;

import java.util.List;

/**
 * ClassName: UserRegisterDto
 * Description:
 * Created by hjb on 2019/3/29 17:38
 */
@Data
public class UserRegisterDto {
    private String phone;
    private String password;
    private List<RoleType> roles;

}
