package be.triplan.controller;

import be.triplan.dto.member.MemberRequestDto;
import be.triplan.dto.member.MemberResponseDto;
import be.triplan.dto.common.CommonResult;
import be.triplan.dto.common.ListResult;
import be.triplan.dto.common.SingleResult;
import be.triplan.service.MailService;
import be.triplan.service.MemberService;
import be.triplan.service.common.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final MailService mailService;
    private final ResponseService responseService;

    //회원 목록 조회
    @GetMapping("/members")
    public ListResult<MemberResponseDto> findAllMember() {
        return responseService.getListResult(memberService.findAllMembers());
    }

    //회원 단건 조회
    @GetMapping("/members/{id}")
    public SingleResult<MemberResponseDto> findMemberById(@PathVariable Long id) {
        return responseService.getSingleResult(memberService.findOne(id));
    }

    //회원 수정
    @PutMapping("/members")
    public SingleResult<Long> updateMember(
            @RequestParam Long id,
            @RequestParam String nickname,
            @RequestParam String aboutMe) {

        MemberRequestDto memberRequestDto = MemberRequestDto.builder()
                .nickname(nickname)
                .aboutMe(aboutMe)
                .build();

        return responseService.getSingleResult(memberService.update(id, memberRequestDto));

    }

    //회원 삭제
    @DeleteMapping("/members/{id}")
    public CommonResult deleteMember(@PathVariable Long id) {
        memberService.delete(id);
        return responseService.getSuccessResult();
    }

    //이메일 인증코드 전송
    @PostMapping("/code")
    public void sendCode(@RequestBody String email) {
        mailService.sendCode(email);
    }
}
