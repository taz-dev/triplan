package be.triplan.dto.member;

import lombok.Getter;
import org.hibernate.procedure.spi.ParameterRegistrationImplementor;

@Getter
public class UpdateMemberResponse {

    private Long id;
    private String nickname;

    public UpdateMemberResponse(Long id, String nickname) {
    }
}
