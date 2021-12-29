package be.triplan.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PlanImg {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "plan_img_Id")
    private Long id;

    private String imgName;

    private String imgPath;

    @OneToOne(mappedBy = "planImg", fetch = LAZY)
    private Plan plan;

    public void setPlan(Plan plan) {
        this.plan = plan;
    }
}
