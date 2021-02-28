package com.miniWeb.restAPI.advice.exception;

public class CEmailSigninFailedException extends RuntimeException{
    // 생성자 추가하기
    public CEmailSigninFailedException(String msg, Throwable t) {
        super(msg, t);
    }

    public CEmailSigninFailedException(String msg) {
        super(msg);
    }

    public CEmailSigninFailedException() {
        super();
    }
}
