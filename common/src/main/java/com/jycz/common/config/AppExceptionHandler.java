package com.jycz.common.config;

import com.jycz.common.response.BusinessException;
import com.jycz.common.response.ErrCodeEnum;
import com.jycz.common.response.ErrInfoInter;
import com.jycz.common.response.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

/**
 * @author ling
 * @data 2020/4/7 17:29
 */
@RestControllerAdvice
public class AppExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @ExceptionHandler
    public Result exceptionHandler(Exception e) {
        if (e instanceof BusinessException) {
            return Result.ofFail(((BusinessException) e));
        }else if (e instanceof BindException){
           //校验失败返回的异常
            return Result.ofFail(ErrCodeEnum.PARAMETERS_INVALID, Objects.requireNonNull(((BindException) e).getBindingResult().getFieldError()).getDefaultMessage());
        }
        else {
            //error级别的错误会写入日志文件
            logger.error(e.getMessage());
            return Result.ofFail(ErrCodeEnum.UNKNOWN_ERROR);
        }
    }
}
