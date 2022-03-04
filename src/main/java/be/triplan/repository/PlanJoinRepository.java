package be.triplan.repository;

import be.triplan.entity.PlanJoin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlanJoinRepository extends JpaRepository<PlanJoin, Long> {
    //로그인 된 유저의 전체 여행 계획 조회할 때 필요
    List<PlanJoin> findByMember_Id(Long memberId);

    @Query("select p from PlanJoin p where p.plan.id = :plan_id")
    List<PlanJoin> findAllInvitedFriends(@Param("plan_id") Long plan_id);
}
