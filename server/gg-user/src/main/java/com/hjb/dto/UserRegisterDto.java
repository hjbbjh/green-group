package com.hjb.dto;

import com.hjb.common.RoleType;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * ClassName: UserRegisterDto
 * Description:
 * Created by hjb on 2019/3/29 17:38
 */
@Data
public class UserRegisterDto {
    @NotBlank(message="手机号不能为空")
    private String phone;
    @NotBlank(message="密码不能为空")
    private String password;
    @Size(min = 1,max = 2,message = "角色有两种")
    private List<RoleType> roles;

}
