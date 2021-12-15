package be.triplan.entity;

import be.triplan.entity.Member;
import be.triplan.entity.Plan;
import lombok.AccessLevel;
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

    //@JsonIgnore
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "plan_id")
    private Plan plan;

    public void setMember(Member member) {
        this.member = member;
    }
}
