package be.triplan.api.repository.schedule;

import be.triplan.api.entity.schedule.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
