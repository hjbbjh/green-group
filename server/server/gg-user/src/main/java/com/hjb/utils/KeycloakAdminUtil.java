package com.hjb.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hjb.common.RoleType;
import com.hjb.common.constant.CodeInfo;
import com.hjb.config.Config;
import com.hjb.dto.KeycloakErrorDto;
import com.hjb.dto.Msg;
import com.hjb.dto.UserRegisterDto;
import org.jboss.resteasy.client.jaxrs.internal.ClientResponse;
import org.jboss.resteasy.spi.HttpResponse;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * ClassName: UserMain
 * Description:
 * Created by hjb on 2019/3/29 9:26
 */
@Component
public class KeycloakAdminUtil {

    @Autowired
    private Config config;

    private Keycloak getAdminMsterKeycloak(){
        System.out.println(config.getKeycloakAutuServerUrl()+
                config.getMasterRealm()+
                config.getAdminUsername()+
                config.getAdminPassword()+
                config.getMasterClient());
        return Keycloak.getInstance(config.getKeycloakAutuServerUrl(),
                config.getMasterRealm(),
                config.getAdminUsername(),
                config.getAdminPassword(),
                config.getMasterClient());
    }
    private Keycloak getAdminGreenGroupKeycloak(){
        System.out.println(config.getKeycloakAutuServerUrl()+
                config.getGGRealm()+
                config.getAdminUsername()+
                config.getAdminPassword()+
                config.getGGClient());
        return Keycloak.getInstance(config.getKeycloakAutuServerUrl(),
                config.getGGRealm(),
                config.getAdminUsername(),
                config.getAdminPassword(),
                config.getGGClient());
    }

    public String getUserAccessToken(String username,String password){
        Map<String,String> params = new HashMap<>();
        params.put("client_id",config.getGGClient());
        params.put("grant_type","password");
        params.put("username",username);
        params.put("password",password);


        Msg<String> responseDto = HttpClientUtils.doPostWithParams(config.getUserTokenUrlPrefix()+config.getGGRealm()+config.getUserTokenUrlPostfix(),params);
        JSONObject resultData = JSONObject.parseObject(responseDto.getData());
        return "Bearer " + resultData.getString("access_token");
    }

    public String getAdminMasterAccessToken(){
        return "Bearer " + getAdminMsterKeycloak().tokenManager().getAccessTokenString();
    }
    public String getAdminGreenGroupAccessToken(){
        return "Bearer " + getAdminGreenGroupKeycloak().tokenManager().getAccessTokenString();
    }


    public UserRepresentation getUserByUsername(String username){
        RealmResource realm = getAdminMsterKeycloak().realm(config.getGGRealm());
        return realm.users().search(username).get(0);
    }
    public List<UserRepresentation> getAllUsers(){
        RealmResource realm = getAdminMsterKeycloak().realm(config.getGGRealm());
        return realm.users().list();
    }

    public Msg<String> createUser(UserRegisterDto userRegisterDto){

        Msg<String> msg = new Msg<>(CodeInfo.SUCCESS);

        RealmResource realm = getAdminMsterKeycloak().realm(config.getGGRealm());

        UserRepresentation user = new UserRepresentation();
        user.setUsername(userRegisterDto.getPhone());
        user.setEnabled(true);
        List<CredentialRepresentation> credentials = new ArrayList<>();
        CredentialRepresentation cr = new CredentialRepresentation();
        cr.setType(CredentialRepresentation.PASSWORD);
        cr.setValue(userRegisterDto.getPassword());
        cr.setTemporary(false);
        credentials.add(cr);
        user.setCredentials(credentials);
        user.setRealmRoles(userRegisterDto.getRoles().stream().map(RoleType::getType).collect(Collectors.toList()));

        UsersResource usersResource = realm.users();
        Response response = usersResource.create(user);
        int status = response.getStatus();
        if(status != 201){
            msg.setCode(String.valueOf(status));
            msg.setMsg(response.readEntity(KeycloakErrorDto.class).getErrorMessage());
        }
        return msg;
    }
}
