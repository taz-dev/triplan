package be.triplan.dto.schedule;

import be.triplan.entity.Map;
import be.triplan.entity.Schedule;
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
public class ScheduleInsertRequestDto {
    private String accessToken;
    private Long planId;
    private String scheduleTitle;
    private int price;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private String memo;
    private Map maps;

    public Schedule toEntity() {
        return Schedule.builder()
                .scheduleTitle(scheduleTitle)
                .price(price)
                .startDateTime(startDateTime)
                .endDateTime(endDateTime)
                .memo(memo)
                .map(maps)
                .build();
    }
}
