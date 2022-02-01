package be.triplan.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PlanJoin {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "plan_join_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "plan_id")
    private Plan plan;

    //연관관계 메서드
    public void InviteFriend(Member member, Plan plan) {
        this.member = member;
        member.getPlanJoins().add(this);
        this.plan = plan;
        plan.getPlanJoins().add(this);
    }

    @Builder
    public PlanJoin(Member member, Plan plan) {
        this.member = member;
        this.plan = plan;
    }
}
