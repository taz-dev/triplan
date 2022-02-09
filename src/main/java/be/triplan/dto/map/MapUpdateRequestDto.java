package be.triplan.dto.map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MapUpdateRequestDto {
    private Long mapId;
    private String locationX;
    private String locationY;
    private String sampleA;
    private String sampleB;
}
