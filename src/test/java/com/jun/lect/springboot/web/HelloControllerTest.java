package com.jun.lect.springboot.web;

import com.jun.lect.springboot.config.oauth.SecurityConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//테스트를 진행할 때 JUnit 에 내장된 실행자 외에 다른 실행자를 실행
//SpringRunner 라는 스프링 실행자를 사용
//스프링부트와 JUnit 사이에 연결자 역할
@RunWith(SpringRunner.class)

//여러 스프링 테스트 어노테이션 중, Web(Spring MVC)에 집중할 수 있는 어노테이션
//@Controller, @ControllerAdvice 등을 사용 가능
//@Service, @Component, @Repository 등 사용 불가
@WebMvcTest(controllers = HelloController.class,
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)
        }
)
public class HelloControllerTest {

    //스프링이 관리하는 빈을 주입 받음
    @Autowired
    //웹 API 를 테스트할 때 사용
    //스프링 MVC 테스트의 시작점
    //이 클래스를 통해 HTTP GET,POST 등에 대한 API 테스트를 할 수 있음
    private MockMvc mvc;

    @WithMockUser(roles = "USER")
    @Test
    public void hello_return() throws Exception {
        String hello = "hello";

        //MockMVC 를 통해 /hello 주소로 HTTP GET 요청을 함
        //체이닝이 지원되어 아래와 같이 여러 검증 기능 이어서 선언 가능
        mvc.perform(get("/hello"))
                .andExpect(status().isOk()) //mvc.perform 의 결과 검증, HTTP Header 의 Status(200,404,500 등)를 검증
                .andExpect(content().string(hello));//mvc.perform 의 결과 검증, 응답 본문의 내용 검증, 리턴 값이 "hello" 인지 검증
    }

    @WithMockUser(roles = "USER")
    @Test
    public void helloDto_return() throws Exception{
        String name = "hello";
        int amount = 1000;

        mvc.perform(
                get("/hello/dto")
                        .param("name", name)
                        .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));
    }
}