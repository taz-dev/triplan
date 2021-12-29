package be.triplan.controller;

import be.triplan.entity.Schedule;
import be.triplan.repository.ScheduleRepository;
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
