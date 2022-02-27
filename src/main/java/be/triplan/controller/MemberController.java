package be.triplan.controller;

import be.triplan.dto.common.CommonResult;
import be.triplan.dto.common.ListResult;
import be.triplan.dto.common.SingleResult;
import be.triplan.dto.member.MemberDto;
import be.triplan.dto.member.MemberUpdateRequestDto;
import be.triplan.entity.Member;
import be.triplan.service.MemberService;
import be.triplan.service.common.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final ResponseService responseService;

    /**
     * 회원 목록 조회
     * 1. 친구 초대를 위해 회원 전체 목록 조회하기
     */
    @GetMapping("/members")
    public ListResult<MemberDto> findAllMembers() {
        return responseService.getListResult(memberService.findAllMembers());
    }

    /**
     * 회원 단건 조회
     */
    @GetMapping("/members/{id}")
    public SingleResult<MemberDto> findMemberById(@PathVariable Long id) {
        return responseService.getSingleResult(memberService.findOne(id));
    }

    /**
     * 회원 수정
     * 1. 간단설정 할 때 "닉네임, 자기소개" UPDATE
     * 2. 로그인 후 마이페이지에서 프로필 수정할 때 "닉네임, 자기소개, 이미지" UPDATE
     */
    @PutMapping("/members")
    public SingleResult<Long> updateMember(@AuthenticationPrincipal Member member, @RequestBody MemberUpdateRequestDto requestDto) {

        MemberDto memberDto = MemberDto.builder()
                .nickname(requestDto.getNickname())
                .aboutMe(requestDto.getAboutMe())
                .memberImage(requestDto.getMemberImage())
                .build();

        return responseService.getSingleResult(memberService.update(member.getId(), memberDto));
    }

    /**
     * 회원 삭제
     */
    @DeleteMapping("/members/{id}")
    public CommonResult deleteMember(@PathVariable Long id) {
        memberService.delete(id);
        return responseService.getSuccessResult();
    }
}
