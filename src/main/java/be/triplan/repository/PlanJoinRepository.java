package be.triplan.repository;

import be.triplan.entity.PlanJoin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlanJoinRepository extends JpaRepository<PlanJoin, Long> {
    List<PlanJoin> findByMember_Id(Long memberId);

    @Query("select p from PlanJoin p where p.plan.id = :plan_id")
    List<PlanJoin> findAllInvitedFriends(@Param("plan_id") Long plan_id);
}
