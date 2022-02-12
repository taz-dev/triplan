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
public class MapInsertRequestDto {
    private String locationX;
    private String locationY;
    private String address;
    private String addressDetail;
    private String mapImage;

    public Map toEntity() {
        return Map.builder()
                .locationX(locationX)
                .locationY(locationY)
                .address(address)
                .addressDetail(addressDetail)
                .mapImage(mapImage)
                .build();
    }
}
