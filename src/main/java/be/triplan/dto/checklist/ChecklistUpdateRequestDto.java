package be.triplan.dto.checklist;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChecklistUpdateRequestDto {
    private Long checklistId;
    private String checkItem;
    private Boolean isSelected;
}
