package be.triplan.service;

import be.triplan.dto.checklist.ChecklistDto;
import be.triplan.entity.Checklist;
import be.triplan.entity.Plan;
import be.triplan.repository.ChecklistRepository;
import be.triplan.repository.PlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ChecklistService {

    private final ChecklistRepository checklistRepository;
    private final PlanRepository planRepository;

    //체크리스트 저장
    @Transactional
    public Long save(Long plan_id, ChecklistDto checklistDto) {
        Plan plan = planRepository.findById(plan_id).orElseThrow();
        Checklist checklist = Checklist.createChecklist(plan, checklistDto);
        checklistRepository.save(checklist);
        return checklist.getId();
    }

    //체크리스트 전체 조회
    public List<ChecklistDto> findAllChecklists() {
        return checklistRepository.findAll()
                .stream()
                .map(ChecklistDto::new)
                .collect(Collectors.toList());
    }
    
    //체크리스트 수정
    @Transactional
    public Long update(Long id, ChecklistDto responseDto) {
        Checklist checklist = checklistRepository.findById(id).orElseThrow();
        checklist.updateCheckItem(responseDto.getCheckItem());
        checklist.updateIsSelected(responseDto.getIsSelected());
        return id;
    }

    //체크리스트 삭제
    @Transactional
    public void delete(Long id) {
        checklistRepository.deleteById(id);
    }
}
