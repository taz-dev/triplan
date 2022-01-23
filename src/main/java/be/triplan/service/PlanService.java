package be.triplan.service;

import be.triplan.dto.checklist.CheckListDto;
import be.triplan.dto.plan.PlanUpdateRequestDto;
import be.triplan.dto.plan.PlanDto;
import be.triplan.entity.*;
import be.triplan.exception.PlanNotFoundException;
import be.triplan.exception.UserNotFoundException;
import be.triplan.repository.MapRepository;
import be.triplan.repository.MemberRepository;
import be.triplan.repository.PlanRepository;
import be.triplan.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PlanService {

    private final PlanRepository planRepository;
    private final MemberRepository memberRepository;
    private final ScheduleRepository scheduleRepository;
    private final MapRepository mapRepository;

    //계획 저장
    @Transactional
    public Long save(Long member_id, PlanDto planDto) {
        Member member = memberRepository.findById(member_id).get();
        Plan plan  = Plan.createPlan(member, planDto);
        planRepository.save(plan);

        return plan.getId();
    }

    //계획 전체 조회
    public List<PlanDto> findAllPlans() {
        return planRepository.findAll()
                .stream()
                .map(PlanDto::new)
                .collect(Collectors.toList());
    }

    //계획 단건 조회
    public PlanDto findOne(Long id) {
        Plan plan = planRepository.findById(id).orElseThrow(PlanNotFoundException::new);
        return new PlanDto(plan);
    }

    //계획 수정
    @Transactional
    public Long update(Long id, PlanUpdateRequestDto planRequestDto) {
        Plan plan = planRepository.findById(id).orElseThrow(PlanNotFoundException::new);
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
    
    //계획 status 변경
    @Transactional
    public void changeStatus() {

    }
}
