package be.triplan.controller;

import be.triplan.dto.member.CreateMemberRequest;
import be.triplan.dto.member.CreateMemberResponse;
import be.triplan.dto.member.UpdateMemberRequest;
import be.triplan.dto.member.UpdateMemberResponse;
import be.triplan.entity.Member;
import be.triplan.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    //회원 전체 조회 API
    @CrossOrigin(origins = "*")
    @GetMapping("/members")
    public List<Member> findAll() { //반환값 바꿔주기
        return memberService.findMembers();
    }

    //회원 조회 API
    @CrossOrigin(origins = "*")
    @GetMapping("/members/{id}")
    public Member findOne(@PathVariable Long id) { //반환값 바꿔주기
        return memberService.findOne(id);
    }

    //회원 등록 API
    @PostMapping("/members")
    public CreateMemberResponse saveMember(@RequestBody @Valid CreateMemberRequest request) {

        Member member = new Member(request.getNickname());

        Long id = memberService.join(member);
        return new CreateMemberResponse(id);
    }

    //회원 수정 API
    @PutMapping("/members/{id}")
    public UpdateMemberResponse updateMember(
            @PathVariable Long id,
            @RequestBody @Valid UpdateMemberRequest request) {

        memberService.update(id, request.getNickname());
        Member findMember = memberService.findOne(id);
        return new UpdateMemberResponse(findMember.getId(), findMember.getNickname());

    }

    //회원 삭제 API
    //@DeleteMapping("/members/{id}")

}
