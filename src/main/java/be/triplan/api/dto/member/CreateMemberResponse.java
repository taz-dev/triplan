package be.triplan.api.dto.member;

import lombok.Getter;

@Getter
public class CreateMemberResponse {
    private Long id;

    public CreateMemberResponse(Long id) {
        this.id = id;
    }
}
