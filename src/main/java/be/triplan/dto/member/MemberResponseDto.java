package be.triplan.dto.member;

import be.triplan.entity.Member;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Getter
public class MemberResponseDto {

    private final Long id;
    private final String email;
    private final String nickname;
    private final String nameTag;
    private final String aboutMe;
    private List<String> roles;
    private Collection<? extends GrantedAuthority> authorities;
    private final LocalDateTime modifiedDate;

    public MemberResponseDto(Member member) {
        this.id = member.getId();
        this.email = member.getEmail();
        this.nickname = member.getNickname();
        this.nameTag = member.getNameTag();
        this.aboutMe = member.getAboutMe();
        this.roles = member.getRoles();
        this.authorities = member.getAuthorities();
        this.modifiedDate = member.getLastModifiedDate();
    }
}
