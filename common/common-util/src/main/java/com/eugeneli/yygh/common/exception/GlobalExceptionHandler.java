package com.eugeneli.yygh.common.exception;

import com.eugeneli.yygh.common.result.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName: GlobalExceptionHandler
 * @Description:
 * @Author EugeneLi
 * @Date: 2021/8/19
 * @Time: 20:12
 */
@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e){

        e.printStackTrace();
        return Result.fail();
    }


    @ExceptionHandler(YyghException.class)
    @ResponseBody
    public Result error(YyghException e){

        e.printStackTrace();
        return Result.fail();
    }


}
