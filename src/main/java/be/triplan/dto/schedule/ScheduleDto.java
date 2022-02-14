package be.triplan.dto.schedule;

import be.triplan.entity.Map;
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
public class ScheduleDto {
    private Long id;
    private String scheduleTitle;
    private int price;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private String memo;
    private Map map;

    public ScheduleDto(Schedule schedule) {
        this.id = schedule.getId();
        this.scheduleTitle = schedule.getScheduleTitle();
        this.price = schedule.getPrice();
        this.startDateTime = schedule.getStartDateTime();
        this.endDateTime = schedule.getEndDateTime();
        this.memo = schedule.getMemo();
        this.map = schedule.getMap();
    }
}
