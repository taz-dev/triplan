package be.triplan.service;

import be.triplan.dto.plan.PlanRequestDto;
import be.triplan.dto.plan.PlanResponseDto;
import be.triplan.entity.Plan;
import be.triplan.exception.TUserNotFoundException;
import be.triplan.repository.PlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PlanService {

    private final PlanRepository planRepository;

    @Transactional
    public Long save(PlanRequestDto planRequestDto) {
        Plan plan = planRepository.save(planRequestDto.toEntity());
        return plan.getId();
    }

    //계획 전체 조회
    public List<PlanResponseDto> findAllPlans() {
        return planRepository.findAll()
                .stream()
                .map(PlanResponseDto::new)
                .collect(Collectors.toList());
    }

    //계획 단건 조회
    public PlanResponseDto findOne(Long id) {
        Plan plan = planRepository.findById(id).orElseThrow(TUserNotFoundException::new);
        return new PlanResponseDto(plan);
    }

    //계획 수정
    @Transactional
    public Long update(Long id, PlanRequestDto planRequestDto) {
        Plan plan = planRepository.findById(id).orElseThrow(TUserNotFoundException::new);
        plan.updateTitle(planRequestDto.getPlanTitle());
        plan.updateStartDate(planRequestDto.getStartDateTime());
        plan.updateEndDate(planRequestDto.getEndDateTime());

        return id;
    }

    //계획 삭제
    @Transactional
    public void delete(Long id) {
        planRepository.deleteById(id);
    }
}
