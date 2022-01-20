package be.triplan.dto.member;

import be.triplan.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberResponseDto {
    private Long memberId;
    private String email;
    private String nickname;
    private String nameTag;
    private String aboutMe;
    private String memberImage;
    //private List<String> roles;
    //private Collection<? extends GrantedAuthority> authorities;
    private LocalDateTime modifiedDate;

    public MemberResponseDto(Member member) {
        this.memberId = member.getId();
        this.email = member.getEmail();
        this.nickname = member.getNickname();
        this.nameTag = member.getNameTag();
        this.aboutMe = member.getAboutMe();
        this.memberImage = member.getMemberImage();
        //this.roles = member.getRoles();
        //this.authorities = member.getAuthorities();
        this.modifiedDate = member.getLastModifiedDate();
    }
}
