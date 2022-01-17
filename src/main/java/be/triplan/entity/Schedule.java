package be.triplan.entity;

import be.triplan.dto.schedule.ScheduleResponseDto;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Schedule extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "schedule_id")
    private Long id;

    @Column(name = "schedule_title")
    private String scheduleTitle;

    private int price;

    @Column(name = "start_date_time")
    private LocalDateTime startDateTime;

    @Column(name = "end_date_time")
    private LocalDateTime endDateTime;

    private String memo;

    @OneToOne(fetch = LAZY, cascade = ALL)
    @JoinColumn(name = "map_id")
    private Map map;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "plan_id")
    private Plan plan;

    //연관관계 메서드
    public void setPlan(Plan plan) {
        this.plan = plan;
        plan.getSchedules().add(this);
    }

    public void setMap(Map map) {
        this.map = map;
        map.setSchedule(this);
    }
}
