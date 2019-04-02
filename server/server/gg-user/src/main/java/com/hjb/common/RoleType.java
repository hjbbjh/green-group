package com.hjb.common;

import com.fasterxml.jackson.annotation.JsonValue;
import com.hjb.common.constant.CodeInfo;
import com.hjb.common.exception.GreenGroupException;
import com.hjb.dto.Msg;
import lombok.Data;

import java.util.Collections;
import java.util.Objects;

/**
 * ClassName: RoleType
 * Description:
 * Created by hjb on 2019/3/29 18:00
 */

public enum RoleType {

    USER("user"),
    ADMIN("admin");

    private String type;
    RoleType(String type){
        this.type = type;
    }

    @JsonValue
    public String getType(){
        return this.type;
    }
}
