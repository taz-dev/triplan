package be.triplan.dto.plan;

import be.triplan.entity.Plan;
import be.triplan.entity.PlanStatus;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PlanResponseDto {

    private Long planId;
    private String planTitle;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private PlanStatus planStatus;
    //private List<ChecklistDto> checklists;
    //private List<ScheduleDto> schedules;

    public PlanResponseDto(Plan plan) {
        planId = plan.getId();
        planTitle = plan.getPlanTitle();
        startDateTime = plan.getStartDateTime();
        endDateTime = plan.getEndDateTime();
        planStatus = plan.getPlanStatus();
    }
}
