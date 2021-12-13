package be.triplan.api.repository.plan;

import be.triplan.api.entity.plan.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlanRepository extends JpaRepository<Plan, Long> {

    List<Plan> findByPlanTitle(String planTitle);

}
