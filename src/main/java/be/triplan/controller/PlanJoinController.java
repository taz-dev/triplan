package be.triplan.controller;

import be.triplan.repository.PlanJoinRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PlanJoinController {

    private final PlanJoinRepository planJoinRepository;

}
