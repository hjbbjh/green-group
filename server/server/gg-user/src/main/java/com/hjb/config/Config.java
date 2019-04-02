package com.hjb.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * ClassName: Config
 * Description:
 * Created by hjb on 2019/3/29 14:42
 */
@Data
@Configuration
public class Config {

    @Value("${keycloak.auth-server-url}")
    private String keycloakAutuServerUrl;

    @Value("${keycloak.realm}")
    private String GGRealm;

    @Value("${keycloak.resource}")
    private String GGClient;

    @Value("${gg.keycloak.admin.username}")
    private String adminUsername;

    @Value("${gg.keycloak.admin.passwrod}")
    private String adminPassword;

    @Value("${gg.keycloak.master.realm}")
    private String masterRealm;

    @Value("${gg.keycloak.admin.client}")
    private String masterClient;

    @Value("${gg.keycloak.get.user.token.prefix}")
    private String userTokenUrlPrefix;

    @Value("${gg.keycloak.get.user.token.postfix}")
    private String userTokenUrlPostfix;
    @Value("${gg.user.default.head.pic.path}")
    private String headDefaultPic;


}
