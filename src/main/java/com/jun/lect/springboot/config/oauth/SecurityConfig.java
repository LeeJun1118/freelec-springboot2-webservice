package com.jun.lect.springboot.config.oauth;

import com.jun.lect.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor

//Spring Security 설정들 활성화시켜줌
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomOauth2UserService customOauth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http    //h2-console 화면을 사용하기 위해 해당 옵션들을 disable
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                    //url 별 권한 관리를 설정하는 옵션의 시작점
                    //authorizeRequests 가 선언되어야만 antMatchers 옵션 사용 가능
                    .authorizeRequests()
                    //권한 관리 대상을 지정하는 옵션
                    //URL,HTTP 메소드 별로 관리 가능
                    // "/" 등 지정된 url 들은 permitAll()옵션을 통해 전체 열람 권한을 줌
                    // "/api/v1/**"주소를 가진 API는 USER 권한을 가진 사람만 가능하도록 함
                    .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll()
                    .antMatchers("/api/v1/**").hasRole(Role.USE.name())
                    //설정된 값들 이외에 나머지 url 들을 나타냄
                    // authenticated()을 추가하여 나머지 url 들은 모두 인증된 사용자들에게만 허용하게 함
                    //인증된 사용자 즉, 로그인한 사용자들을 이야기함
                    .anyRequest().authenticated()
                .and()
                    //로그아웃 기능에 대한 여러 설정의 진입점
                    .logout()
                        //로그아웃 성공시 / 주소로 이동
                        .logoutSuccessUrl("/")
                .and()
                    //oauth2 로그인 기능에 대한 여러 설정의 진입점
                    .oauth2Login()
                        //oauth2 로그인 성공 이후 사용자 정보를 가져올 때의 설정들을 담당
                        .userInfoEndpoint()
                        //소셜 로그인 성공시 후속 조치를 진행할 UserService 인터페이스의 구현체를 등록
                        //리소스 서버(소셜서비스)에서 사용자 정보를 가져온 상태에서 추가로 진행하고자 하는 기능 명시
                        .userService(customOauth2UserService);
    }
}
