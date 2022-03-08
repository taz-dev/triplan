package be.triplan.dto.question;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuestionInsertRequestDto {
    private String questionTitle;
    private String questionContent;
    private String questionImage;
}
