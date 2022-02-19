package be.triplan.repository;

import be.triplan.entity.Member;
import be.triplan.entity.Plan;
import be.triplan.entity.PlanJoin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PlanJoinRepository extends JpaRepository<PlanJoin, Long> {
    @Query("select p from PlanJoin p where p.plan.id = :plan_id")
    List<PlanJoin> findAllInvitedFriends(@Param("plan_id") Long plan_id);

    Optional<PlanJoin> findByMemberAndPlan(Member member, Plan plan);
/*
    @Query("SELECT j.member.id, j.plan.id, p.planTitle, p.address, p.addressDetail, p.startDate, p.endDate, p.locationX, p.locationY, p.planImage\n" +
            "FROM PlanJoin AS j\n" +
            "LEFT JOIN Plan AS p\n" +
            "ON j.plan.id = p.id\n" +
            "LEFT JOIN Member AS m\n" +
            "ON j.member.id = m.id")
    List<PlanJoinInterface> findAllPlansByMember();*/
}
