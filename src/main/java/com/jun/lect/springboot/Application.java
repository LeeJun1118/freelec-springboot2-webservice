package com.jun.lect.springboot;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


//JPA Auditing 활성화
@EnableJpaAuditing

//스프링 부트의 자동 설정, 스프링 Bean 읽기와 생성 모두 자동으로 설정
//항상 프로젝트 상단에 위치해야함
@SpringBootApplication
public class Application {
    public static void main(String[] args){
        //내장 WAS(웹애플리케이션서버)실행 -> 톰캣 설치 필요 없음
        SpringApplication.run(Application.class, args);
    }
}
