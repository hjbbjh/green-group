package com.hjb.dto;


import com.alibaba.fastjson.JSON;
import com.hjb.common.constant.CodeInfo;
import lombok.Data;

/**
 * ClassName: UserMain
 * Description:
 * Created by hjb on 2019/3/29 9:26
 */
@Data
public class Msg<T> {

    protected String code;
    protected String msg;
    protected T data;

    public Msg() {
    }

    public Msg(CodeInfo codeInfo) {
        this.code = codeInfo.getCode();
        this.msg = codeInfo.getMsg();
    }

    public Msg(CodeInfo codeInfo,T data) {
        this.code = codeInfo.getCode();
        this.msg = codeInfo.getMsg();
        this.data = data;
    }
    public Msg(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Msg<T> set(Msg<Object> msg){
        this.code = msg.getCode();
        this.msg = msg.getMsg();
        return this;
    }

    public Boolean checkSuccess(){
        return code.equals(CodeInfo.SUCCESS.getCode());
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
