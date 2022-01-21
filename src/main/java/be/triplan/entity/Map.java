package be.triplan.entity;

import lombok.*;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Map {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "map_id")
    private Long id;

    private String locationX;
    private String locationY;
    private String sampleA;
    private String sampleB;

    @OneToOne(mappedBy = "map", fetch = LAZY)
    private Schedule schedule;

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }
}
