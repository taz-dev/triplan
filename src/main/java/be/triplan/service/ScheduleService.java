package be.triplan.service;

import be.triplan.dto.schedule.ScheduleDto;
import be.triplan.entity.Map;
import be.triplan.entity.Plan;
import be.triplan.entity.Schedule;
import be.triplan.repository.MapRepository;
import be.triplan.repository.PlanRepository;
import be.triplan.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final PlanRepository planRepository;
    private final MapRepository mapRepository;

    //일정 저장
    @Transactional
    public Long save(Long plan_id, ScheduleDto scheduleDto) {
        Plan plan = planRepository.findById(plan_id).orElseThrow();
        Schedule schedule = Schedule.createSchedule(plan, scheduleDto);
        scheduleRepository.save(schedule);
        return schedule.getId();
    }

    //계획 별로 일정 전체목록 조회
    public List<ScheduleDto> findAllSchedules(Long planId) {
        return scheduleRepository.findAllSchedules(planId)
                .stream()
                .map(ScheduleDto::new)
                .collect(Collectors.toList());
    }

    //일정 단건 조회
    public ScheduleDto findOne(Long id) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow();
        return new ScheduleDto(schedule);
    }

    //일정 수정
    public Long update(Long id, ScheduleDto scheduleDto) {


        return id;
    }

    //일정 삭제
    @Transactional
    public void delete(Long id) {
        scheduleRepository.deleteById(id);
    }
}
