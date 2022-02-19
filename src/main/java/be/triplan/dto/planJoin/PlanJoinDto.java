package be.triplan.dto.planJoin;

import be.triplan.entity.Plan;
import be.triplan.entity.PlanJoin;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlanJoinDto {

    private Long memberId;
    private Long planId;
    private String planTitle;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private String planImage;

    public PlanJoinDto(PlanJoin planJoin) {
        this.memberId = planJoin.getMember().getId();
        this.planId = planJoin.getPlan().getId();
        this.planTitle = planJoin.getPlan().getPlanTitle();
        this.startDateTime = planJoin.getPlan().getStartDate();
        this.endDateTime = planJoin.getPlan().getEndDate();
        this.planImage = planJoin.getPlan().getPlanImage();
    }
}
