package be.triplan.service;

import be.triplan.dto.map.MapDto;
import be.triplan.dto.map.MapInsertRequestDto;
import be.triplan.entity.Map;
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
        map.updateMap(responseDto.getLocationX(), responseDto.getLocationY(), responseDto.getSampleA(), responseDto.getSampleB());
        return id;
    }

    //핀 삭제
    @Transactional
    public void delete(Long id) {
        mapRepository.deleteById(id);
    }
}
