package com.jun.lect.springboot.web;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//json을 반환하는 컨트롤러를 만들어줌
@RestController
public class HelloController {

    //Http 메소드인 Get의 요청을 받을 수 있는 API를 만들어줌
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }
}
