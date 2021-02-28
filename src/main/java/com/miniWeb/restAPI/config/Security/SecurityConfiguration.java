package com.miniWeb.restAPI.config.Security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final JwtTokenProvider jwtTokenProvider;
    
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
            .httpBasic().disable() // Rest API이므로 기본설정 X / 기본설정은 비인증 시 로그인폼 화면으로 redirect됨
            .csrf().disable() // Rest API이므로 csrf 보안이 필요없음
                              // JWT Token으로 인증하므로 Session은 필요없으므로 생성 X
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
                .authorizeRequests() // 다음 request에 대한 사용 권한 체크
                                     // 가입 및 인증 주소는 누구나 접근 가능
                    .antMatchers("/*/signin","/*/signup").permitAll()
                                     // helloworld로 시작하는 GET 요청 resource는 누구나 접근 가능
                    .antMatchers(HttpMethod.GET, "helloworld/**").permitAll()
                    .anyRequest().hasRole("USER") // 그 외 나머지 요청은 모두 인증된 회원만 접근 가능
        .and()                                    // JWT token 필터를 ID / Password 인증 필터 전에 넣는다.
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
    }

    // Swagger는 예외 처리
    @Override
    public void configure(WebSecurity web){
        web.ignoring().antMatchers(
                "/v2/api-docs","/swagger-resources/**",
                           "/swagger-ui.html", "/webjars/**", "/swagger/**"
                            );
    }
}
