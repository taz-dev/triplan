package be.triplan.dto.checklist;

import be.triplan.entity.Checklist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChecklistDto {
    private Long checklistId;
    private String checkItem;
    private Boolean isSelected;

    public ChecklistDto(Checklist checklist) {
        this.checklistId = checklist.getId();
        this.checkItem = checklist.getCheckItem();
        this.isSelected = checklist.getIsSelected();
    }
}
