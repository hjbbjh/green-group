package com.hjb.common.constant;

import com.alibaba.fastjson.JSON;

/**
 * ClassName: UserMain
 * Description:
 * Created by hjb on 2019/3/29 9:26
 */
public enum  CodeInfo {

    SUCCESS("0","OK"),
    EXCEPTION("-1","服务器错误！"),
    HTTP_REQUEST_ERROR("-2","HTTP请求失败！"),


    //user
    USER_NOT_EXSIST("10000","用户不存在！"),
    ;

    CodeInfo(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private String code;

    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

}
