package be.triplan.controller;

import be.triplan.dto.common.CommonResult;
import be.triplan.dto.common.ListResult;
import be.triplan.dto.common.SingleResult;
import be.triplan.dto.schedule.ScheduleInsertRequestDto;
import be.triplan.dto.schedule.ScheduleDto;
import be.triplan.service.ScheduleService;
import be.triplan.service.common.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;
    private final ResponseService responseService;

    /**
     * 일정 저장
     */
    @PostMapping("/schedules")
    public SingleResult<Long> saveSchedules(@RequestBody ScheduleInsertRequestDto requestDto) {
        return responseService.getSingleResult(scheduleService.save(requestDto));
    }
    
    /**
     * 일정 전체목록 조회
     */
    @GetMapping("/schedules")
    public ListResult<ScheduleDto> findAllSchedules() {
        return responseService.getListResult(scheduleService.findAllSchedules());
    }

    /**
     * 일정 단건 조회
     */
    @GetMapping("/schedules/{id}")
    public SingleResult<ScheduleDto> findScheduleById(@PathVariable Long id) {
        return responseService.getSingleResult(scheduleService.findOne(id));
    }

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
