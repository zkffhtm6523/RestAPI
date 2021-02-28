package com.miniWeb.restAPI.model.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
// list 형태로 선언하고 제네릭을 통한 형태 다양화
public class ListResult<T> extends CommonResult{
    private List<T> list;
}
