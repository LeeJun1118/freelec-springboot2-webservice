package com.jun.lect.springboot.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

//선언된 모든 필드의 Get 메소드를 생성
@Getter

//선언된 모든 final 필드가 포함된 생성자를 생성
//final 이 없는 필드는 생성자에 포함X
@RequiredArgsConstructor
public class HelloResponseDto {
    private final String name;
    private final int amount;
}
