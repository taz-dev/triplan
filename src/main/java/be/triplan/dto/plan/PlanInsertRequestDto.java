package be.triplan.dto.plan;

import be.triplan.dto.map.MapDto;
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
    private String planTitle;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String planImage;
    private List<MapDto> map;
}
