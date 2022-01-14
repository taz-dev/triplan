package be.triplan.dto.question;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuestionRequestDto {

    private String accessToken;
    private Long member_id;
    private String title;
    private String content;
    private String image;
}
