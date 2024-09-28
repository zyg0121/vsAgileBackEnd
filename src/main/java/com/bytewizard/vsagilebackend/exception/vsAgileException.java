package com.bytewizard.vsagilebackend.exception;

import com.bytewizard.vsagilebackend.utils.JsonData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class vsAgileException {
    @ExceptionHandler
    public JsonData exceptionHandler(Throwable e) {
        //得到异常信息
        String message = e.getMessage();
        //保存到数据库,通过网页显示异常，判断异常的等级

        final Logger logger = LoggerFactory.getLogger(vsAgileException.class);
        logger.error("Exception occurred", e);//把异常打印到日志
        //返回错误信息给前端
        return new JsonData(500, "服务器出错,到控制台查看错误详细信息!", message);
    }
}