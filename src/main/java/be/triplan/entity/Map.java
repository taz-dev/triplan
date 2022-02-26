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
public class Map extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "map_id")
    private Long id;

    @Column(name = "location_x")
    private String locationX;

    @Column(name = "location_y")
    private String locationY;

    private String address;

    @Column(name = "address_detail")
    private String addressDetail;

    @Column(name = "map_image")
    private String mapImage;

    @Enumerated(EnumType.STRING)
    private MapStatus mapStatus;

    @OneToOne(mappedBy = "map", fetch = LAZY)
    private Schedule schedule;

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public void updateMap(String locationX, String locationY, String address, String addressDetail) {
        this.locationX = locationX;
        this.locationY = locationY;
        this.address = address;
        this.addressDetail = addressDetail;
    }
}
