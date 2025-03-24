package com.atguigu.cloud.exp;

import com.atguigu.cloud.resp.ResultData;
import com.atguigu.cloud.resp.ReturnCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultData<String> exceptionHandler(Exception ex){
        System.out.println("#####come in GlobalExceptionHandler");
        log.error("全局异常信息：{}", ex.getMessage(),ex);
//        ResultData<String> resultData = new ResultData<>();
//        resultData.setCode(ReturnCodeEnum.RC500.getCode());
//        resultData.setMessage(ex.getMessage());
//        return resultData;

        return ResultData.fail(ReturnCodeEnum.RC500.getCode(), ex.getMessage());
    }
}
