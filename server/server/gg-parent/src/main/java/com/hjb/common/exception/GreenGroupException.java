package com.hjb.common.exception;


import com.hjb.dto.Msg;
import lombok.Data;

/**
 * ClassName: UserMain
 * Description:
 * Created by hjb on 2019/3/29 9:26
 */
@Data
public class GreenGroupException extends RuntimeException {
    protected Msg<String> msg;

    public GreenGroupException(Msg<String> msg) {
        this.msg = msg;
    }
    public GreenGroupException(Throwable cause, Msg<String> msg) {
        super(msg.toString(),cause);
        this.msg = msg;
    }
    public GreenGroupException(String message, Msg<String> msg) {
        super(message+":::"+msg);
        this.msg = msg;
    }

    public GreenGroupException(String message, Throwable cause, Msg<String> msg) {
        super(message+":::"+msg, cause);
        this.msg = msg;
    }
}
