package be.triplan.dto.plan;

import be.triplan.entity.Plan;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlanUpdateRequestDto {
    private Long planId;
    private String planTitle;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
