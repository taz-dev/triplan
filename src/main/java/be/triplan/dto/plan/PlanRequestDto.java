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
public class PlanRequestDto {

    private String planTitle;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    public Plan toEntity() {
        return Plan.builder()
                .planTitle(planTitle)
                .startDateTime(startDateTime)
                .endDateTime(endDateTime)
                .build();
    }
}
