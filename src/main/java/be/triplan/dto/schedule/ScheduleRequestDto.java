package be.triplan.dto.schedule;

import be.triplan.entity.Map;
import be.triplan.entity.Member;
import be.triplan.entity.Plan;
import be.triplan.entity.Schedule;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleRequestDto {

    private String accessToken;
    private Long plan_id;
    private String scheduleTitle;
    private int price;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private String memo;
    //private List<Map> maps;

    public Schedule toEntity() {
        return Schedule.builder()
                .scheduleTitle(scheduleTitle)
                .price(price)
                .startDateTime(startDateTime)
                .endDateTime(endDateTime)
                .memo(memo)
                .build();
    }
}
