package be.triplan.dto.plan;

import be.triplan.entity.Map;
import be.triplan.entity.Plan;
import be.triplan.entity.PlanJoin;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlanInsertRequestDto {
    private String accessToken;
    private Long memberId;
    private String planTitle;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private Long mapId;
    private List<PlanJoin> friends;

    public Plan toEntity() {
        return Plan.builder()
                .planTitle(planTitle)
                .startDateTime(startDateTime)
                .endDateTime(endDateTime)
                .planJoins(friends)
                .build();
    }
}
