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
    /*private String startLocationX;
    private String startLocationY;
    private String startAddress;
    private String startAddressDetail;
    private String endLocationX;
    private String endLocationY;
    private String endAddress;
    private String endAddressDetail;*/

    public PlanDto(Plan plan) {
        this.planId = plan.getId();
        this.planTitle = plan.getPlanTitle();
        this.startDateTime = plan.getStartDate();
        this.endDateTime = plan.getEndDate();
        this.planImage = plan.getPlanImage();
        /*this.startLocationX = plan.getStartLocationX();
        this.startLocationY = plan.getStartLocationY();
        this.startAddress = plan.getStartAddress();
        this.startAddressDetail = plan.getStartAddressDetail();
        this.endLocationX = plan.getEndLocationX();
        this.endLocationY = plan.getEndLocationY();
        this.endAddress = plan.getEndAddress();
        this.endAddressDetail = plan.getEndAddressDetail();*/
    }
}
