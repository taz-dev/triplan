package be.triplan.dto.member;

import lombok.Getter;

@Getter
public class CreateMemberResponse {
    private Long id;

    public CreateMemberResponse(Long id) {
        this.id = id;
    }
}
