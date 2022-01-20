package be.triplan.entity;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Member extends BaseTimeEntity implements UserDetails {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String nickname;

    private String email;

    @Column(name = "name_tag")
    private String nameTag;

    private String password; //UserDetails 나중에 Principal로 분리

    @Column(name = "about_me")
    private String aboutMe;

    private String provider; //Google, Kakao, Naver 구분

    @Column(name = "refresh_token")
    private String refreshToken;

    @Column(name = "member_image")
    private String memberImage;

    @OneToOne(fetch = LAZY, cascade = ALL)
    @JoinColumn(name = "member_img_id")
    private MemberImg memberImg;

    @OneToMany(mappedBy = "member", cascade = ALL)
    private List<PlanJoin> planJoins = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = ALL)
    private List<Question> questions = new ArrayList<>();

    //비즈니스 로직(닉네임, 자기소개 / 닉네임, 자기소개, 이미지 두개로?)
    public void updateNickname(String nickname) {
        this.nickname = nickname;
    }

    public void updateAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    public void updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public void updateImage(String memberImage) {
        this.memberImage = memberImage;
    }

    //연관관계 메서드
    public void changeMemberImg(MemberImg memberImg) {
        this.memberImg = memberImg;
        memberImg.setMember(this);
    }

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles
                .stream().map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return String.valueOf(this.id);
    }

    //계정이 만료되었는지 여부
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //계정이 잠겼는지 여부
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //계졍 패스워드가 만료되었는지 여부
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //계정이 사용가능한지 여부
    @Override
    public boolean isEnabled() {
        return true;
    }
}
