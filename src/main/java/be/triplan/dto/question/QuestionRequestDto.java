package be.triplan.dto.question;

import be.triplan.entity.Question;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuestionRequestDto {

    private String title;
    private String content;
    private String image;

    public Question toEntity() {
        return Question.builder()
                .title(title)
                .content(content)
                .image(image)
                .build();
    }
}
