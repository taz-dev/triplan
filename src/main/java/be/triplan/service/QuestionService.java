package be.triplan.service;

import be.triplan.dto.question.QuestionDto;
import be.triplan.entity.Member;
import be.triplan.entity.Question;
import be.triplan.exception.UserNotFoundException;
import be.triplan.repository.MemberRepository;
import be.triplan.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QuestionService {

    private final MemberRepository memberRepository;
    private final QuestionRepository questionRepository;

    //문의 저장
    @Transactional
    public Long save(Long member_id, QuestionDto questionDto) {

        Member member = memberRepository.findById(member_id).orElseThrow(UserNotFoundException::new);
        Question question = Question.createQuestion(member, questionDto);
        questionRepository.save(question);

        return question.getId();
    }
}
