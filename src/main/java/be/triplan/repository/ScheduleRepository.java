package be.triplan.repository;

import be.triplan.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    //시작시간 빠른 순서대로 오름차순 정렬
    @Query("select s from Schedule s order by s.startDateTime")
    List<Schedule> findAllSchedules();
}
