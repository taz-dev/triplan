package be.triplan.api.controller.planjoin;

import be.triplan.api.repository.plan.PlanJoinRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PlanJoinController {

    private final PlanJoinRepository planJoinRepository;

}
