package be.triplan.dto.checklist;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CheckListDto {
    private String checkItem;
    private Boolean isSelected;
}
