package be.triplan.api.dto;

import be.triplan.api.entity.Plan;
import be.triplan.api.entity.PlanStatus;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PlanDto {

    private Long planId;
    private String planTitle;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private PlanStatus planStatus;
    //private List<ChecklistDto> checklists;
    //private List<ScheduleDto> schedules;

    public PlanDto(Plan plan) {
        planId = plan.getId();
        planTitle = plan.getPlanTitle();
        startDateTime = plan.getStartDateTime();
        endDateTime = plan.getEndDateTime();
        planStatus = plan.getPlanStatus();
    }
}
