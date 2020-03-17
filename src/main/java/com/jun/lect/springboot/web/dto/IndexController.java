package com.jun.lect.springboot.web.dto;

import com.jun.lect.springboot.config.oauth.dto.SessionUser;
import com.jun.lect.springboot.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

//머스테치 스타터로 인해 컨트롤러에서 문자열을 반환할 때 앞의 경로와 뒤의 파일 확장자는 자동으로 지정됨
//앞의 경로는 src/main/resource/templates 로, 뒤의 확장자는 .mustache 가 붇는다

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;


    //여기선 "index"를 반환하므로 src/main/resource/templates/index.mustache 로 전환되어 View Resolver 가 처리한다.
    @GetMapping("/")
    //Model 은 서버 템플릿 엔진에서 사용할 수 있는 객체를 저장할 수 있음
    //여기서는 postsService.findAllDesc()로 가져온 결과를 posts 로 index.mustache 에 전달달
   public String index(Model model){
        model.addAttribute("posts",postsService.findAllDecs());

        //CustomOAuth2UserService 에서 로그인 성공 시 세션에 SessionUser 를 저장
        SessionUser user = (SessionUser)httpSession.getAttribute("user");

        //세션에 저장된 값이 있을 때만 model 에 userName 으로 등록
        //세션에 저장된 값이 없으면 model 엔 아무런 값이 없는 상태이니 로그인 버튼이 보이게 됨
        if (user != null){
            model.addAttribute("userName",user.getName());
        }
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model){
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("posts",dto);

        return "posts-update";
    }
}
