package com.hjb.common;

import com.fasterxml.jackson.annotation.JsonValue;
import com.hjb.common.constant.CodeInfo;
import com.hjb.common.exception.GreenGroupException;
import com.hjb.dto.Msg;

import java.util.Objects;

/**
 * ClassName: UserState
 * Description:
 * Created by hjb on 2019/4/2 10:03
 */
public enum UserState {
    NORMAL("正常"),
    LOCKING("封号"),
    DELETE("删除");
    private String state;
    UserState(String state){
        this.state = state;
    }

    @JsonValue
    public String getState(){
        return this.state;
    }
}
