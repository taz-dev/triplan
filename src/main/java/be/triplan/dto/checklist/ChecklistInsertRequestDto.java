package be.triplan.dto.checklist;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChecklistInsertRequestDto {
    private Long planId;
    private String checkItem;
    private Boolean isSelected;
}
