package be.triplan.dto.member;

import be.triplan.entity.Member;
import lombok.Getter;

@Getter
public class MemberAutoLoginResponseDto {

    private final Long id;
    private final String email;
    private final String nickname;
    private final String nameTag;
    private final String aboutMe;

    public MemberAutoLoginResponseDto(Member member) {
        this.id = member.getId();
        this.email = member.getEmail();
        this.nickname = member.getNickname();
        this.nameTag = member.getNameTag();
        this.aboutMe = member.getAboutMe();
    }
}
