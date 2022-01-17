package be.triplan.controller;

import be.triplan.dto.common.CommonResult;
import be.triplan.dto.common.ListResult;
import be.triplan.dto.schedule.ScheduleResponseDto;
import be.triplan.entity.Schedule;
import be.triplan.repository.ScheduleRepository;
import be.triplan.service.ScheduleService;
import be.triplan.service.common.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;
    private final ResponseService responseService;

    /**
     * 일정 저장
     */
    
    /**
     * 일정 전체목록 조회
     */
    @GetMapping("/schedules")
    public ListResult<ScheduleResponseDto> findAllSchedules() {
        return responseService.getListResult(scheduleService.findAllSchedules());
    }

    /**
     * 일정 단건 조회
     */

    /**
     * 일정 수정
     */

    /**
     * 일정 삭제
     */
    @DeleteMapping("/schedules/{id}")
    public CommonResult deleteSchedule(@PathVariable Long id) {
        scheduleService.delete(id);
        return responseService.getSuccessResult();
    }
}
