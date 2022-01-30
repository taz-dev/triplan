package be.triplan.entity;

import be.triplan.dto.question.QuestionDto;
import lombok.*;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Question extends BaseTimeEntity {

    @Id @GeneratedValue
    @Column(name = "question_id")
    private Long id;

    @Column(name = "question_title")
    private String questionTitle;

    @Column(name = "question_content")
    private String questionContent;

    @Column(name = "question_image")
    private String questionImage;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    //연관관계 메서드
    public void addMember(Member member) {
        this.member = member;
        member.getQuestions().add(this);
    }

    public void addQuestion(String questionTitle, String questionContent, String questionImage) {
        this.questionTitle = questionTitle;
        this.questionContent = questionContent;
        this.questionImage = questionImage;
    }

    //생성 메서드
    public static Question createQuestion(Member member, QuestionDto questionDto) {
        Question question = new Question();
        question.addMember(member);
        question.addQuestion(questionDto.getQuestionTitle(), questionDto.getQuestionContent(), questionDto.getQuestionImage());

        return question;
    }
}
