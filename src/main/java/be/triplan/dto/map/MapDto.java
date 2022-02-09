package be.triplan.dto.map;

import be.triplan.entity.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MapDto {
    private Long mapId;
    private String locationX;
    private String locationY;
    private String sampleA;
    private String sampleB;

    public MapDto(Map map) {
        this.mapId = map.getId();
        this.locationX = map.getLocationX();
        this.locationY = map.getLocationY();
        this.sampleA = map.getSampleA();
        this.sampleB = map.getSampleB();
    }
}
