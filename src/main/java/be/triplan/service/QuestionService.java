package be.triplan.service;

import be.triplan.dto.question.QuestionRequestDto;
import be.triplan.entity.Question;
import be.triplan.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QuestionService {

    private final QuestionRepository questionRepository;

    @Transactional
    public Long save(QuestionRequestDto questionRequestDto) {
        Question question = questionRepository.save(questionRequestDto.toEntity());
        return question.getId();
    }
}
