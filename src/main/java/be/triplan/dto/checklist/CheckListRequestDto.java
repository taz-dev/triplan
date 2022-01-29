package be.triplan.dto.checklist;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CheckListRequestDto {
    private String accessToken;
    private Long planId;
    private String checkItem;
    private Boolean isSelected;
}
