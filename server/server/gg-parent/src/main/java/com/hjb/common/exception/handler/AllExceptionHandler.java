package com.hjb.common.exception.handler;

import com.hjb.common.constant.CodeInfo;
import com.hjb.common.exception.GreenGroupException;
import com.hjb.dto.Msg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * ClassName: UserMain
 * Description:
 * Created by hjb on 2019/3/29 9:26
 */
@RestControllerAdvice
public class AllExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(AllExceptionHandler.class);

    @ExceptionHandler(value = GreenGroupException.class)
    public Msg<String> handleGreenGroupException(GreenGroupException gE){
        logger.error("catch an business exception: "+gE.getMsg());
        return gE.getMsg();
    }

    @ExceptionHandler(value = Exception.class)
    public Msg<String> handleException(Exception e){
        Msg<String> msg = new Msg<>(CodeInfo.EXCEPTION);
        msg.setData(e.getMessage());
        logger.error("catch an exception: "+e.getMessage());
        return msg;
    }


}
