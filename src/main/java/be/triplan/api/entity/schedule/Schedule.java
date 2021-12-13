package be.triplan.api.entity.schedule;

import be.triplan.api.entity.map.Map;
import be.triplan.api.entity.plan.Plan;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Schedule {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "schedule_id")
    private Long id;

    private String scheduleTitle;

    private int price;

    private LocalDateTime startDateTime;

    private LocalDateTime endDateTime;

    private String memo;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "map_id")
    private Map map;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "plan_id")
    private Plan plan;
}
