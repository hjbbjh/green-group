package com.hjb.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * ClassName: UserLoginDto
 * Description:
 * Created by hjb on 2019/3/29 17:36
 */
@Data
public class UserLoginDto {
    @NotBlank(message="手机号不能为空")
    private String phone;
    @NotBlank(message="密码不能为空")
    private String password;
}
