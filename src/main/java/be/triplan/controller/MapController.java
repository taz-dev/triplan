package be.triplan.controller;

import be.triplan.dto.common.CommonResult;
import be.triplan.dto.common.ListResult;
import be.triplan.dto.common.SingleResult;
import be.triplan.dto.map.MapDto;
import be.triplan.dto.map.MapInsertRequestDto;
import be.triplan.dto.map.MapUpdateRequestDto;
import be.triplan.service.MapService;
import be.triplan.service.common.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MapController {

    private final MapService mapService;
    private final ResponseService responseService;

    /**
     * 지도에 핀(위치) 저장
     */
    @PostMapping("/maps")
    public SingleResult<Long> saveMap(@RequestBody MapInsertRequestDto requestDto) {
        Long map_id = mapService.save(requestDto);
        return responseService.getSingleResult(map_id);
    }

    /**
     * 핀 전체 조회
     */
    @GetMapping("/maps")
    public ListResult<MapDto> findAllMaps() {
        return responseService.getListResult(mapService.findAllMaps());
    }
    
    /**
     * 핀 한개 조회
     */
    @GetMapping("/maps/{id}")
    public SingleResult<MapDto> findMapById(@PathVariable Long id) {
        return responseService.getSingleResult(mapService.findOne(id));
    }

    /**
     * 핀 수정
     */
    @PutMapping("/maps")
    public SingleResult<Long> updateMap(@RequestBody MapUpdateRequestDto requestDto) {

        Long map_id = requestDto.getMapId();

        MapDto responseDto = MapDto.builder()
                .locationX(requestDto.getLocationX())
                .locationY(requestDto.getLocationY())
                .sampleA(requestDto.getSampleA())
                .sampleB(requestDto.getSampleB())
                .build();

        return responseService.getSingleResult(mapService.update(map_id, responseDto));
    }

    /**
     * 핀 삭제
     */
    @DeleteMapping("/maps/{id}")
    public CommonResult deleteMap(@PathVariable Long id) {
        mapService.delete(id);
        return responseService.getSuccessResult();
    }
}
