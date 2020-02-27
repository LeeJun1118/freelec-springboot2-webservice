package com.jun.lect.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;


//JpaRepository<Entity 클래스, PK 타입> 을 하면 기본적인 CRUD 매소드가 자동으로 생성
public interface PostsRepository extends JpaRepository<Posts,Long> {
}
