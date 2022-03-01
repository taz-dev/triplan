package be.triplan.service;

import be.triplan.dto.map.MapDto;
import be.triplan.dto.map.MapInsertRequestDto;
import be.triplan.dto.plan.PlanInsertRequestDto;
import be.triplan.entity.Map;
import be.triplan.entity.MapStatus;
import be.triplan.repository.MapRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MapService {

    private final MapRepository mapRepository;

    //계획 저장할 때 출발위치 도착위치 저장
    @Transactional
    public void saveMapStatus(PlanInsertRequestDto requestDto) {

        for (int i = 0; i < requestDto.getMap().size(); i++) {

            if (i == 0) {
                Map map = Map.builder()
                        .mapImage(requestDto.getMap().get(i).getPlanImage())
                        .locationX(requestDto.getMap().get(i).getLocationX())
                        .locationY(requestDto.getMap().get(i).getLocationY())
                        .address(requestDto.getMap().get(i).getAddress())
                        .addressDetail(requestDto.getMap().get(i).getAddressDetail())
                        .mapStatus(MapStatus.START)
                        .build();

                mapRepository.save(map);

            } else {
                Map map = Map.builder()
                        .mapImage(requestDto.getMap().get(i).getPlanImage())
                        .locationX(requestDto.getMap().get(i).getLocationX())
                        .locationY(requestDto.getMap().get(i).getLocationY())
                        .address(requestDto.getMap().get(i).getAddress())
                        .addressDetail(requestDto.getMap().get(i).getAddressDetail())
                        .mapStatus(MapStatus.END)
                        .build();

                mapRepository.save(map);
            }
        }
    }

    //지도에 핀(위치) 저장
    @Transactional
    public Long save(MapInsertRequestDto requestDto) {
        Map map = mapRepository.save(requestDto.toEntity());
        return map.getId();
    }

    //핀 전체 조회
    public List<MapDto> findAllMaps() {
        return mapRepository.findAll()
                .stream()
                .map(MapDto::new)
                .collect(Collectors.toList());
    }

    //핀 한개 조회
    public MapDto findOne(Long id) {
        Map map = mapRepository.findById(id).orElseThrow();
        return new MapDto(map);
    }

    //핀 수정
    @Transactional
    public Long update(Long id, MapDto responseDto) {
        Map map = mapRepository.findById(id).orElseThrow();
        map.updateMap(responseDto.getLocationX(), responseDto.getLocationY(), responseDto.getAddress(), responseDto.getAddressDetail());
        return id;
    }

    //핀 삭제
    @Transactional
    public void delete(Long id) {
        mapRepository.deleteById(id);
    }
}
