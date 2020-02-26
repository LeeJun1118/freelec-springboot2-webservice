package com.jun.lect.springboot;


import com.jun.lect.springboot.web.HelloController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//테스트를 진행할 때 JUnit 에 내장된 실행자 외에 다른 실행자를 실행
//여기서는 SpringRunner 라는 스프링 실행자 사용
//스프링 부트와 JUnit 사이에 연결자 역할
@RunWith(SpringRunner.class)

//Web 에 집중할 수 있는 어노테이션
//@Controller , @ControllerAdvice 등 사용 가능
@WebMvcTest(controllers = HelloController.class)

public class HelloControllerTest {

    //스프링이 관리하는 Bean 을 주입 받음
    @Autowired

    //웹 API 를 테스트할 때 사용
    //스프링 MVC 테스트의 시작점
    //HTTP GET,POST 등에 대한 API 테스트 가능
    private MockMvc mvc;

    @Test
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";

        //MockMVC 를 통해 /hello 주소로 HTTP GET 요청
        mvc.perform(get("/hello"))
                .andExpect(status().isOk()) //mvc.perform 의 결과 검증, HTTP header 의 status 를 검증
                .andExpect(content().string(hello)); //mvc.perform 의 결과 검증, 응답 본문의 내용 검증
    }
}
