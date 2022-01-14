package be.triplan.entity;

import be.triplan.dto.question.QuestionDto;
import lombok.*;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Question extends BaseTimeEntity {

    @Id @GeneratedValue
    @Column(name = "question_id")
    private Long id;

    private String title;
    private String content;
    private String image;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    //연관관계 메서드
    public void setMember(Member member) {
        this.member = member;
        member.getQuestions().add(this);
    }

    //생성 메서드
    public static Question addQuestion(Member member, QuestionDto questionDto) {
        Question question = new Question();
        question.setMember(member);
        question.setTitle(questionDto.getTitle());
        question.setContent(questionDto.getContent());
        question.setImage(questionDto.getImage());

        return question;
    }
}
