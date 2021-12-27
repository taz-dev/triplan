package be.triplan.api.repository;

import be.triplan.api.entity.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlanRepository extends JpaRepository<Plan, Long> {

    List<Plan> findByPlanTitle(String planTitle);

}
