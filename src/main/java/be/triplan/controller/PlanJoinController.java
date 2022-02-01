package be.triplan.controller;

import be.triplan.entity.PlanJoin;
import be.triplan.service.PlanJoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PlanJoinController {

    private final PlanJoinService planJoinService;
}
