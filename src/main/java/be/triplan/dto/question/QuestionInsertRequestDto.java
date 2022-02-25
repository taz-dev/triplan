package be.triplan.dto.question;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuestionInsertRequestDto {
    private String complainName; //questionTitle
    private String complain; //questionContent
    private String source; //questionImage
}
