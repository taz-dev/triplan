package be.triplan.dto.schedule;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleInsertRequestDto {
    //private String accessToken;
    private Long planId;
    private String scheduleTitle;
    private int price;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private String memo;
    private Long mapId; //List?? private Map map
}
