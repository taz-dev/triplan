package be.triplan.dto.plan;

import be.triplan.entity.Plan;
import be.triplan.entity.PlanStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlanDto {
    private Long planId;
    private String planTitle;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private String planImage;
    private PlanStatus planStatus;

    public PlanDto(Plan plan) {
        this.planId = plan.getId();
        this.planTitle = plan.getPlanTitle();
        this.startDateTime = plan.getStartDateTime();
        this.endDateTime = plan.getEndDateTime();
        this.planImage = plan.getPlanImage();
        this.planStatus = plan.getPlanStatus();
    }
}
