package com.miniWeb.restAPI.controller.v1;

import com.miniWeb.restAPI.advice.exception.CUserNotFoundException;
import com.miniWeb.restAPI.entity.User;
import com.miniWeb.restAPI.model.response.CommonResult;
import com.miniWeb.restAPI.model.response.ListResult;
import com.miniWeb.restAPI.model.response.SingleResult;
import com.miniWeb.restAPI.repo.UserJpaRepo;
import com.miniWeb.restAPI.service.ResponseService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"2, user"})
@RequiredArgsConstructor
@RestController // 결과값을 JSON으로 출력합니다.
@RequestMapping(value = "/v1")
public class UserController {

    private final UserJpaRepo userJpaRepo;
    // 결과를 처리할 Service
    private final ResponseService responseService;

    // 유효한 JWT 토큰 설정 후 User 리소스 사용할 수 있음
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "X-AUTH-TOKEN",
                    value = "로그인 성공 후 access_token",
                    required = true,
                    dataType = "String",
                    paramType = "header"
            )
    })
    @ApiOperation(value = "회원 리스트 조회", notes = "모든 회원을 조회한다.")
    @GetMapping(value = "/users")
    public ListResult<User> findAllUser() {
        // 결과 데이터가 복수일 경우 getListResult를 이용하여 결과를 출력
        return responseService.getListResult(userJpaRepo.findAll());
    }

    // 유효한 JWT 토큰 설정 후 User 리소스 사용할 수 있음
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "X-AUTH-TOKEN",
                    value = "로그인 성공 후 access_token",
                    required = true,
                    dataType = "String",
                    paramType = "header"
            )
    })
    @ApiOperation(value = "회원 단건 조회", notes = "회원번호로 회원을 조회한다")
    @GetMapping(value = "/user")
    public SingleResult<User> findUserById(@ApiParam(value = "언어", defaultValue = "ko") @RequestParam String lang) {
        // SecurityContext에서 인증받은 회원의 정보를 얻어온다.
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String id = authentication.getName();
        // 결과데이터가 단일건인경우 getSingleResult를 이용해서 결과를 출력한다.
        return responseService.getSingleResult(userJpaRepo.findByUid(id).orElseThrow(CUserNotFoundException::new));
    }

    // 유효한 JWT 토큰 설정 후 User 리소스 사용할 수 있음
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "X-AUTH-TOKEN",
                    value = "로그인 성공 후 access_token",
                    required = true,
                    dataType = "String",
                    paramType = "header"
            )
    })
    @ApiOperation(value = "회원 수정", notes = "회원 정보를 수정한다.")
    @PutMapping(value = "/user")
    public SingleResult<User> modify(
            @ApiParam(value = "회원번호", required = true) @RequestParam long msrl,
            @ApiParam(value = "회원이름", required = true) @RequestParam String name){
        User user = User.builder()
                .msrl(msrl)
                .name(name)
                .build();
        return responseService.getSingleResult(userJpaRepo.save(user));
    }

    // 유효한 JWT 토큰 설정 후 User 리소스 사용할 수 있음
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "X-AUTH-TOKEN",
                    value = "로그인 성공 후 access_token",
                    required = true,
                    dataType = "String",
                    paramType = "header"
            )
    })
    @ApiOperation(value = "회원 삭제", notes = "회원번호(msrl)로 회원정보를 삭제한다")
    @DeleteMapping(value = "/user/{msrl}")
    public CommonResult delete(
            @ApiParam(value = "회원번호", required = true) @PathVariable long msrl) {
        userJpaRepo.deleteById(msrl);
        // 성공 결과 정보만 필요한경우 getSuccessResult()를 이용하여 결과를 출력한다.
        return responseService.getSuccessResult();
    }
}
//@RequiredArgsConstructor
//class상단에 선언하면 class내부에 final로 선언된 객체에 대해서 Constructor Injection을 수행합니다. 해당 어노테이션을 사용하지 않고 선언된 객체에 @Autowired를 사용해도 됩니다.
//
//@RestController
//결과 데이터를 JSON으로 내보냅니다.
//
//@RequestMapping(value = “/v1”)
//api resource를 버전별로 관리하기 위해 /v1 을 모든 리소스 주소에 적용되도록 처리합니다.
//
//@GetMapping(value = “/user”)
//user테이블에 있는 데이터를 모두 읽어옵니다.
//        데이터가 한개 이상일 수 있으므로 리턴 타입은 List<User>로 선언합니다.
//
//        userJpaRepo.findAll()
//        JPA를 사용하면 기본으로 CRUD에 대해서는 별다른 설정없이 쿼리를 질의할 수 있도록 메소드를 지원합니다. findAll()은 select msrl, name, uid from user; 쿼리를 내부적으로 실행시켜 줍니다.
//
//@PostMapping(value = “/user”)
//user테이블에 데이터를 1건 입력합니다. userJpaRepo.save(user); 역시 JPA에서 기본으로 제공하는 메소드입니다. user객체를 전달하면 다음과 같이 내부적으로 insert문을 실행시켜 줍니다.
//        insert into user (msrl, name, uid) values (null, ?, ?)


