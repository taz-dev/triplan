package be.triplan.api.entity.member;

import be.triplan.api.entity.BaseEntity;
import be.triplan.api.entity.plan.PlanJoin;
import be.triplan.oauth.entity.ProviderType;
import lombok.*;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(nullable = false)
    private String nameTag;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String password;

    private String aboutMe;

    @Enumerated(EnumType.STRING)
    private ProviderType providerType;

    @OneToOne(fetch = LAZY, cascade = ALL)
    @JoinColumn(name = "member_img_id")
    private MemberImg memberImg;

    @OneToMany(mappedBy = "member", cascade = ALL)
    private List<PlanJoin> planJoins = new ArrayList<>();

    //연관관계 메서드
    public void setPlanJoin(PlanJoin planJoin) {
        planJoins.add(planJoin);
        planJoin.setMember(this);
    }

    public void setMemberImg(MemberImg memberImg) {
        this.memberImg = memberImg;
        memberImg.setMember(this);
    }
}
