package be.triplan.dto.question;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuestionInsertRequestDto {
    private String accessToken;
    private Long memberId;
    private String questionTitle;
    private String questionContent;
    private String questionImage;
}
