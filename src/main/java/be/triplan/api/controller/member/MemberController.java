package be.triplan.api.controller.member;

import be.triplan.api.service.member.MemberService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    //회원 가입
/*    @PostMapping("/members")
    public JoinMemberResponse joinMember(@RequestBody @Valid MemberDto memberDto) {
        Long id = memberService.join(memberDto);
        return new JoinMemberResponse(id);
    }*/

    @Data
    static class JoinMemberResponse {
        private Long id;

        public JoinMemberResponse(Long id) {
            this.id = id;
        }
    }

    //회원 조회

    //회원 단건 조회

    //회원 수정

    //회원 탈퇴
}
