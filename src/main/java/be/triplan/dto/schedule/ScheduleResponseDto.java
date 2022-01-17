package be.triplan.dto.schedule;

import be.triplan.entity.Map;
import be.triplan.entity.Schedule;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleResponseDto {

    private Long id;
    private String scheduleTitle;
    private int price;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private String memo;
    private Map map;

    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.scheduleTitle = schedule.getScheduleTitle();
        this.price = schedule.getPrice();
        this.startDateTime = schedule.getStartDateTime();
        this.endDateTime = schedule.getEndDateTime();
        this.memo = schedule.getMemo();
        this.map = schedule.getMap();
    }
}
