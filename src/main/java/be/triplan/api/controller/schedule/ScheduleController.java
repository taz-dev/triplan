package be.triplan.api.controller.schedule;

import be.triplan.api.entity.schedule.Schedule;
import be.triplan.api.repository.schedule.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleRepository scheduleRepository;

    @GetMapping("/schedules")
    public List<Schedule> list() {
        return scheduleRepository.findAll();
    }
}
