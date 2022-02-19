package be.triplan.dto;

import java.time.LocalDateTime;

public interface PlanJoinInterface {
    Long getPlanId();
    Long getMemberId();
    String getPlanTitle();
    String getAddress();
    String getAddressDetail();
    LocalDateTime getStartDateTime();
    LocalDateTime getEndDateTime();
    String getLocationX();
    String getLocationY();
    String getPlanImage();
}
