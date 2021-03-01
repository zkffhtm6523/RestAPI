package com.miniWeb.restAPI.model.social;

import lombok.Getter;
import lombok.Setter;

// 카카오 Token API 연동 시 Mapping을 위한 Model 생성
@Setter
@Getter
public class RetKakaoAuth {
    private String access_token;
    private String token_type;
    private String refresh_token;
    private long expires_in;
    private String scope;
}
