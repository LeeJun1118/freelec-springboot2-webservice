package com.jun.lect.springboot.web;


import com.jun.lect.springboot.service.PostsService;
import com.jun.lect.springboot.web.dto.PostsResponseDto;
import com.jun.lect.springboot.web.dto.PostsSaveRequestDto;
import com.jun.lect.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto){
        return postsService.save(requestDto);
    }


    //파라미터를 url 경로에 포함 시킨다 {변수}
    @PutMapping("/api/v1/posts/{id}")
    //{ } 를 이용해 매핑되는 url 경로에 작성해주면 해당 @PathVariable 어노테이션이 작성된 파라미터에 자동으로 매핑된다.
    //위의{id}와 밑의 id 가 다를 경우 추가적으로 @PathVariable("id") Long 다른변수 이렇게 작성해야함
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto){
        return postsService.update(id,requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id){
        return postsService.findById(id);
    }
}
