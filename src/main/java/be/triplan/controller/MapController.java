package be.triplan.controller;

import be.triplan.dto.common.SingleResult;
import be.triplan.dto.map.MapInsertRequestDto;
import be.triplan.service.MapService;
import be.triplan.service.common.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MapController {

    private final MapService mapService;
    private final ResponseService responseService;

    /**
     * 지도에 위치 저장
     */
    @PostMapping("/maps")
    public SingleResult<Long> saveMap(@RequestBody MapInsertRequestDto requestDto) {
        Long map_id = mapService.save(requestDto);
        return responseService.getSingleResult(map_id);
    }
}
