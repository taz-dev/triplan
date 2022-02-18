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
    private String address;
    private String addressDetail;
    private String planImage;

    public MapDto(Map map) {
        this.mapId = map.getId();
        this.locationX = map.getLocationX();
        this.locationY = map.getLocationY();
        this.address = map.getAddress();
        this.addressDetail = map.getAddressDetail();
        this.planImage = map.getMapImage();
    }
}
