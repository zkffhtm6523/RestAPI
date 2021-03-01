package com.miniWeb.restAPI.repo;

import com.miniWeb.restAPI.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
class UserJpaRepoTest {
    
    @Autowired
    private UserJpaRepo userJpaRepo;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Test
    public void whenFindByUid_thenReturnUser(){
        String uid = "zkffhtm6523@gmail.com";
        String name = "David";
        // given
        userJpaRepo.save(User.builder()
                    .uid(uid)
                    .password(passwordEncoder.encode("1234"))
                    .name(name)
                    .roles(Collections.singletonList("ROLE_USER"))
                    .build());
        // when
        Optional<User> user = userJpaRepo.findByUid(uid);
        
        // then
        // user 객체가 null이 아닌지 체크
        assertNotNull(user);
        // user 객체 존재 여부 체크(boolean)
        assertTrue(user.isPresent());
        // user 객체의 name과 name 변수 값이 같은지 체크
        assertEquals(user.get().getName(), name);
        assertThat(user.get().getName(), is(name));
    }
}