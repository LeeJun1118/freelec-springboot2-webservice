package com.jun.lect.springboot.service;


import com.jun.lect.springboot.domain.posts.Posts;
import com.jun.lect.springboot.domain.posts.PostsRepository;
import com.jun.lect.springboot.web.dto.PostsListResponseDto;
import com.jun.lect.springboot.web.dto.PostsResponseDto;
import com.jun.lect.springboot.web.dto.PostsSaveRequestDto;
import com.jun.lect.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

//lombok
//final 이 선언된 모든 필드를 인자값으로 하는 생성자를 대신 생성
//해당 클래스의 의존성 관계가 변경될 때마다 생성자 코드를 계속해서 수정하는 번거로움을 해결
@RequiredArgsConstructor

@Service
public class PostsService {

    private final PostsRepository postsRepository;

    //원자성(Atomicity), 일관성(Consistency), 독립성(Isolation), 영구성(Durability)을 보장 - ACID
    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto){
        //데이터베이스에 쿼리를 날리는 부분이 없다 -> JPA 영속성 컨텍스트(엔티티를 영구적으로 저장하는 환경) 때문
        //JPA 의 엔티티 매니저가 활성화된 상태로(Spring Data Jpa 는 기본 옵션) 트랜잭션 안에서 데이터베이스에서
        //데이터를 가져오면 이 데이터는 영속성 컨텍스트가 유지된 상태이다.
        //이 상태에서 데이터의 값을 변경하면 트랜잭션이 끝나느 시점에 해당 테이블에 변경분을 반영한다.
        //즉, Entity 객체의 값만 변경하면 별도로 Update 쿼리를 날릴 필요가 없다 =>이걸 dirty checking 이라고 함.
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = "+ id));
        posts.update(requestDto.getTitle(),requestDto.getContent());
        return id;
    }

    public PostsResponseDto findById(Long id){
        Posts entity = postsRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id =" + id));
        return new PostsResponseDto(entity);
    }

    //readOnly 옵션을 주면 트랜잭션 범위는 유지하되, 조회 기능만 남겨두어 조회 속도 개선
    @Transactional(readOnly=true)
    public List<PostsListResponseDto> findAllDecs(){
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new) //map(posts -> new PostsListResponseDto(posts)) 와 같다
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id){
        Posts posts = postsRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));
        postsRepository.delete(posts);
    }



}
