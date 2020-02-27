package com.jun.lect.springboot.service;


import com.jun.lect.springboot.domain.posts.Posts;
import com.jun.lect.springboot.domain.posts.PostsRepository;
import com.jun.lect.springboot.web.dto.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

//lombok
//final 이 선언된 모든 필드를 인자값으로 하는 생성자를 대신 생성
//해당 클래스의 의존성 관계가 변경될 때마다 생성자 코드를 계속해서 수정하는 번거로움을 해결
@RequiredArgsConstructor

@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }
}
