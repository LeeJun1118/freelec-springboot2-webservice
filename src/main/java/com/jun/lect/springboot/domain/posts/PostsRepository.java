package com.jun.lect.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


//JpaRepository<Entity 클래스, PK 타입> 을 하면 기본적인 CRUD 매소드가 자동으로 생성
public interface PostsRepository extends JpaRepository<Posts,Long> {

    //SpringDataJpa 에서 제공하지 않는 메소드는 이렇게 쿼리를 작성하면 됨
    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();
}
