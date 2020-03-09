package com.jun.lect.springboot.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class IndexControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void 메인페이지_로딩(){
        //실제로 URL 호출 시 페이지의 내용이 제대로 호출되는지에 대한 테스트
        //when
        String body = this.restTemplate.getForObject("/",String.class);

        //then
        //TestRestTemplate 를 통해 "/"로 호출했을 때 index.mustache 에 포함된 코드들이 있는지 확인한다.
        //여기선 전체 코드를 검증할 필요가 없으니 "스프링 부트로 시작하는 웹서비스"문자열이 있는지 비교했다.
        assertThat(body).contains("스프링 부트로 시작하는 웹서비스");
    }

}