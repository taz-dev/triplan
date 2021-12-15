package be.triplan.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Plan {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "plan_id")
    private Long id;

    private String planTitle;

    private LocalDateTime startDateTime;

    private LocalDateTime endDateTime;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "plan_img_id")
    private PlanImg planImg;

    @Enumerated(EnumType.STRING)
    private PlanStatus planStatus;

    @OneToMany(mappedBy = "plan")
    private List<Checklist> checklists = new ArrayList<>();

    @OneToMany(mappedBy = "plan")
    private List<Schedule> schedules = new ArrayList<>();

    //연관관계 메서드
    public void setPlanImg(PlanImg planImg) {
        this.planImg = planImg;
        planImg.setPlan(this);
    }
}
