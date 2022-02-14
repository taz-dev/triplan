package be.triplan.controller;

import be.triplan.dto.common.CommonResult;
import be.triplan.dto.common.ListResult;
import be.triplan.dto.common.SingleResult;
import be.triplan.dto.schedule.ScheduleDto;
import be.triplan.dto.schedule.ScheduleInsertRequestDto;
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

        Long plan_id = requestDto.getPlanId();
        Long map_id = requestDto.getMapId();

        ScheduleDto responseDto = ScheduleDto.builder()
                .scheduleTitle(requestDto.getScheduleTitle())
                .price(requestDto.getPrice())
                .startDateTime(requestDto.getStartDateTime())
                .endDateTime(requestDto.getEndDateTime())
                .memo(requestDto.getMemo())
                .build();

        return responseService.getSingleResult(scheduleService.save(plan_id, map_id, responseDto));
    }
    
    /**
     * 일정 전체목록 조회(오름차순)
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
/*    @PutMapping("/schedules")
    public SingleResult<Long> updateSchedule(@RequestBody ScheduleUpdateRequestDto requestDto) {

        return responseService.getSingleResult();
    }*/

    /**
     * 일정 삭제
     */
    @DeleteMapping("/schedules/{id}")
    public CommonResult deleteSchedule(@PathVariable Long id) {
        scheduleService.delete(id);
        return responseService.getSuccessResult();
    }
}
