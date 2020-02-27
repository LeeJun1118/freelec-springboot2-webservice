package com.jun.lect.springboot.web.dto;

import com.jun.lect.springboot.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor

//화면 변경은 사소한 변경 기능인데, 이를 위해 테이블과 연결된 Entity 클래스를 변경하는 것은 너무 큰 변경이다.
//Entity 클래스가 변경되면 여러 클래스에 영향을 끼친다.
//Entity 변경을 하지 않게 역할 분리를 하여 view 를 위한 클래스를 만들어야한다.
//자주 변경이 요구되는 view 를 위한 클래스 Request 와 Response 용 Dto(Data Transfer Object) 를 사용한다.
//Entity 클래스와 Controller 에서 쓸 Dto 는 분리해서 사용한다. (Dto = 계층간 데이터 교환을 위한 객체)
public class PostsSaveRequestDto {

    private String title;
    private String content;
    private String author;

    @Builder
    public PostsSaveRequestDto(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Posts toEntity() {
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}


