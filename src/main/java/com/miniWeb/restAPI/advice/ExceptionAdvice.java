package com.miniWeb.restAPI.advice;

import com.miniWeb.restAPI.advice.exception.CUserNotFoundException;
import com.miniWeb.restAPI.model.response.CommonResult;
import com.miniWeb.restAPI.service.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
// 예외 발생 시 json 형태로 결과 반환
@RestControllerAdvice
//@RestControllerAdvice(basePackages = "com.miniweb.restAPI")
public class ExceptionAdvice {
    private final ResponseService responseService;

//    1. CUserNotFoundException 미적용
//    // Exception.class는 최상위 예외 객체
//    @ExceptionHandler(Exception.class)
//    // 해당 Exception이 발생하면 response에 출력되는 HttpStatus Code가 500으로 가도록 설정
//    // 성공 시 200
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    protected CommonResult defaultException(HttpServletRequest request, Exception e){
//        return responseService.getFailResult();
//    }
//    2. CUserNotFoundException 적용
    @ExceptionHandler(CUserNotFoundException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult userNotFoundException(HttpServletRequest request, CUserNotFoundException e){
        return responseService.getFailResult();
    }

}
