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
public class PlanDto {
    private Long planId;
    private String planTitle;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private String planImage;
    private String locationX;
    private String locationY;
    private String address;
    private String addressDetail;

    public PlanDto(Plan plan) {
        this.planId = plan.getId();
        this.planTitle = plan.getPlanTitle();
        this.startDateTime = plan.getStartDate();
        this.endDateTime = plan.getEndDate();
        this.planImage = plan.getPlanImage();
        this.locationX = plan.getLocationX();
        this.locationY = plan.getLocationY();
        this.address = plan.getAddress();
        this.addressDetail = plan.getAddressDetail();
    }
}
