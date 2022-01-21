package be.triplan.service;

import be.triplan.dto.map.MapInsertRequestDto;
import be.triplan.entity.Map;
import be.triplan.repository.MapRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MapService {

    private final MapRepository mapRepository;

    //지도에 위치 저장
    @Transactional
    public Long save(MapInsertRequestDto requestDto) {
        Map map = mapRepository.save(requestDto.toEntity());
        return map.getId();
    }

}
