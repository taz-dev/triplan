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

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "name_tag")
    private String nameTag;

    private String password;

    @Column(name = "about_me")
    private String aboutMe;

    //Google, Kakao, Naver 구분하기 위한 provider
    private String provider;

    public void updateNickname(String nickname) {
        this.nickname = nickname;
    }

/*    @Builder
    public Member(String nickname, String email, String imageUrl, Role role) {
        this.nickname = nickname;
        this.email = email;
        this.imageUrl = imageUrl;
        this.role = role;
    }

    public Member update(String nickname, String imageUrl) {
        this.nickname = nickname;
        this.imageUrl = imageUrl;

        return this;
    }

    public Member(String nickname) {
        this.nickname = nickname;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }*/

    @OneToOne(fetch = LAZY, cascade = ALL)
    @JoinColumn(name = "member_img_id")
    private MemberImg memberImg;

    @OneToMany(mappedBy = "member", cascade = ALL)
    private List<PlanJoin> planJoins = new ArrayList<>();

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    //연관관계 메서드
    public void setPlanJoin(PlanJoin planJoin) {
        planJoins.add(planJoin);
        planJoin.setMember(this);
    }

    public void setMemberImg(MemberImg memberImg) {
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

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
