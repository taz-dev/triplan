package be.triplan.config.auth.dto;

import be.triplan.entity.Member;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {

    private String nickname;
    private String email;
    private String imageUrl;

    public SessionUser(Member member) {
        this.nickname = member.getNickname();
        this.email = member.getEmail();
        this.imageUrl = member.getImageUrl();
    }
}
