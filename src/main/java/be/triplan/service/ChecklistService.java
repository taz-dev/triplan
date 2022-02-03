package be.triplan.service;

import be.triplan.dto.checklist.ChecklistDto;
import be.triplan.entity.Checklist;
import be.triplan.entity.Plan;
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

    //체크리스트 저장
    @Transactional
    public Long save(Long plan_id, ChecklistDto checkListDto) {

        Plan plan = planRepository.findById(plan_id).orElseThrow();
        Checklist checklist = Checklist.createCheckList(plan, checkListDto);
        checklistRepository.save(checklist);

        return checklist.getId();
    }

    //체크리스트 삭제
    @Transactional
    public void delete(Long id) {
        checklistRepository.deleteById(id);
    }
}
