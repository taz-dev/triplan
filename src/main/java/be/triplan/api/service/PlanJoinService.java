package be.triplan.api.service;

import be.triplan.api.entity.PlanJoin;
import be.triplan.api.repository.PlanJoinRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PlanJoinService {

    private final PlanJoinRepository planJoinRepository;

    public PlanJoin findMemberId(Long memberId) {
        planJoinRepository.findById(memberId);
        return null;
    }
}
