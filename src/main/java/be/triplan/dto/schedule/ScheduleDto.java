package be.triplan.dto.schedule;

import be.triplan.entity.MapStatus;
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
    private Long scheduleId;
    private String scheduleTitle;
    private int price;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private String memo;
    private Long mapId;
    private String address;
    private String addressDetail;
    private String locationX;
    private String locationY;
    private String mapImage;
    private MapStatus mapStatus;

    public ScheduleDto(Schedule schedule) {
        this.scheduleId = schedule.getId();
        this.scheduleTitle = schedule.getScheduleTitle();
        this.price = schedule.getPrice();
        this.startDateTime = schedule.getStartDateTime();
        this.endDateTime = schedule.getEndDateTime();
        this.memo = schedule.getMemo();
        this.mapId = schedule.getMap().getId();
        this.address = schedule.getMap().getAddress();
        this.addressDetail = schedule.getMap().getAddressDetail();
        this.locationX = schedule.getMap().getLocationX();
        this.locationY = schedule.getMap().getLocationY();
        this.mapImage = schedule.getMap().getMapImage();
        this.mapStatus = schedule.getMap().getMapStatus();
    }
}
