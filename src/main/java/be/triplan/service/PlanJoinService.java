package be.triplan.service;

import be.triplan.entity.PlanJoin;
import be.triplan.repository.PlanJoinRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
