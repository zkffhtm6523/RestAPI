package com.miniWeb.restAPI.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
// 결과가 단일건인 API를 담는 모델
// 제네릭을 선언하므로 어떠한 형태의 값도 넣을 수 있음
// CommonResult를 상속받으므로 api 요청 결과도 같이 출력
public class SingleResult<T> extends CommonResult {
    private T data;
}
