package be.triplan.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Map {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "map_id")
    private Long id;

    private String location;

    @OneToOne(mappedBy = "map", fetch = LAZY)
    private Schedule schedule;
}
