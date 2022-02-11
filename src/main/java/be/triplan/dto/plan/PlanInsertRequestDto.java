package be.triplan.dto.plan;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlanInsertRequestDto {
    private String email;
    private String planTitle;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String planImage;
    private String locationX;
    private String locationY;
    private String address;
    private String addressDetail;
    //private List<PlanJoin> friends;
}
