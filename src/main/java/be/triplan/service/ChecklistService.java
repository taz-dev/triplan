package be.triplan.service;

import be.triplan.dto.checklist.CheckListDto;
import be.triplan.dto.checklist.CheckListRequestDto;
import be.triplan.dto.question.QuestionDto;
import be.triplan.entity.Checklist;
import be.triplan.entity.Member;
import be.triplan.entity.Plan;
import be.triplan.entity.Question;
import be.triplan.exception.UserNotFoundException;
import be.triplan.repository.ChecklistRepository;
import be.triplan.repository.PlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ChecklistService {

    private final ChecklistRepository checklistRepository;
    private final PlanRepository planRepository;

    @Transactional
    public Long save(Long plan_id, CheckListDto checkListDto) {

        Plan plan = planRepository.findById(plan_id).orElseThrow();
        Checklist checklist = Checklist.createCheckList(plan, checkListDto);
        checklistRepository.save(checklist);

        return checklist.getId();
    }
}
