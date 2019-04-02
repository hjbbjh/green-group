package com.hjb;

import com.hjb.common.RoleType;
import com.hjb.dto.UserRegisterDto;
import com.hjb.utils.KeycloakAdminUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * ClassName: KeycloakTest
 * Description:
 * Created by hjb on 2019/3/29 17:57
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class KeycloakTest {

    @Autowired
    private KeycloakAdminUtil keycloakAdminUtil;

    @Test
    public void testCreateUser() throws Exception{
        System.out.println(keycloakAdminUtil.getAdminMasterAccessToken());
        //System.out.println(keycloakAdminUtil.getAdminGreenGroupAccessToken());
        //System.out.println(keycloakAdminUtil.getUserByUsername("18896724284").getUsername());
        //System.out.println(keycloakAdminUtil.getUserAccessToken("hjb","1"));
/*        UserRegisterDto userRegisterDto = new UserRegisterDto();
        userRegisterDto.setUsername("18896724284");
        userRegisterDto.setPassword("skj4942735");
        List<RoleType> role = new ArrayList<>();
        role.add(RoleType.USER);
        userRegisterDto.setRoles(role);
        keycloakAdminUtil.createUser(userRegisterDto);*/

        //System.out.println(keycloakAdminUtil.getAdminChePublicAccessToken());
/*        System.out.println(keycloakAdminUtil.getAdminChePublicAccessToken());

        UserRegisterDto userRegisterDto = new UserRegisterDto();
        userRegisterDto.setUsername("hjb1");
        userRegisterDto.setPassword("skj4942735");
        userRegisterDto.setRoles(Collections.singletonList(RoleType.USER));
        keycloakAdminUtil.createUser(userRegisterDto);*/
    }
}
