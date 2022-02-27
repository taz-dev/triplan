package be.triplan.dto.member;

import be.triplan.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {
    private Long memberId;
    private String email;
    private String nickname;
    private String nameTag;
    private String aboutMe;
    private String memberImage;
    private List<String> roles;
    private Collection<? extends GrantedAuthority> authorities;
    private LocalDateTime modifiedDate;

    public MemberDto(Member member) {
        this.memberId = member.getId();
        this.email = member.getEmail();
        this.nickname = member.getNickname();
        this.nameTag = member.getNameTag();
        this.aboutMe = member.getAboutMe();
        this.memberImage = member.getMemberImage();
        this.roles = member.getRoles();
        this.authorities = member.getAuthorities();
        this.modifiedDate = member.getLastModifiedDate();
    }
}
