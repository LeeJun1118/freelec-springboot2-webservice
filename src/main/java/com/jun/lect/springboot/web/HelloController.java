package com.jun.lect.springboot.web;

import com.jun.lect.springboot.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//JSON 을 반환하는 컨트롤러로 만들어줌

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(@RequestParam("name")String name,@RequestParam("amount")int amount){
        return new HelloResponseDto(name,amount);
    }
}
