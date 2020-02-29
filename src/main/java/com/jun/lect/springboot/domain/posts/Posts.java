package com.jun.lect.springboot.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

//**lombok 어노테이션
//클래스 내 모든 필드의 Getter 매소드 자동 생성
@Getter

//기본 생성자 자동 추가
//public Posts(){} 와 같은 효과
@NoArgsConstructor

//**JPA 어노테이션
//테이블과 연결될 클래스임을 나타냄
//기본값으로 클래스의 카멜케이스 이름을 언더스코어 네이밍(_)으로 테이블 이름을 매칭함 (ex SalesManager.java => sales_manager table)
@Entity
public class Posts extends BaseTimeEntity {

    //해당 테이블의 PK 필드를 나타냄
    @Id
    //PK 의 생성 규칙을 나타냄 GenerationType.IdENTITY 를 써야만 auto increment 가 됨
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //테이블의 컬럼을 나타내며 굳이 선언하지 않아도 해당 클래스의 필드는 모두 컬럼이 됨
    //기본 값 외에 추가 변경 옵션이 있을 때 사용
    //문자열의 경우 기본값은 varchar(255)인데 500으로 바꿈
    @Column(length = 500,nullable = false)
    private String title;

    //타입을 TEXT 로 바꿈
    @Column(columnDefinition = "TEXT",nullable = false)
    private String content;

    private String author;

    //해당 클래스의 빌더 패턴 클래스 생성
    //생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함
    @Builder
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title,String content){
        this.title = title;
        this.content = content;
    }
}
