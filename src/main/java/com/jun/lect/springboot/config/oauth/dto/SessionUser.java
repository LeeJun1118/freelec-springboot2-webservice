package com.jun.lect.springboot.config.oauth.dto;

import com.jun.lect.springboot.domain.user.User;
import lombok.Getter;

import java.io.Serializable;


//인증된 사용자 정보만 필요
@Getter
public class SessionUser implements Serializable {
    private String name;
    private String emil;
    private String picture;

    public SessionUser(User user) {
        this.name = user.getName();
        this.emil = user.getEmail();
        this.picture = user.getPicture();
    }
}
