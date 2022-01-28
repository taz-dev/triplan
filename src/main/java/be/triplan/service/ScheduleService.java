package be.triplan.service;

import be.triplan.dto.schedule.ScheduleInsertRequestDto;
import be.triplan.dto.schedule.ScheduleDto;
import be.triplan.entity.Schedule;
import be.triplan.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    //일정 저장
    @Transactional
    public Long save(ScheduleInsertRequestDto requestDto) {
        Schedule schedule = scheduleRepository.save(requestDto.toEntity());
        return schedule.getId();
    }

    //일정 전체 조회
    public List<ScheduleDto> findAllSchedules() {
        return scheduleRepository.findAll()
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

    //일정 삭제
    @Transactional
    public void delete(Long id) {
        scheduleRepository.deleteById(id);
    }
}
