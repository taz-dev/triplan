package be.triplan.service;

import be.triplan.dto.member.MemberRequestDto;
import be.triplan.dto.member.MemberResponseDto;
import be.triplan.entity.Member;
import be.triplan.exception.CUserNotFoundException;
import be.triplan.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Long save(MemberRequestDto memberRequestDto) {
        Member member = memberRepository.save(memberRequestDto.toEntity());
        return member.getId();
    }
    
    //회원 전체 조회
    public List<MemberResponseDto> findAllMembers() {
        return memberRepository.findAll()
                .stream()
                .map(MemberResponseDto::new)
                .collect(Collectors.toList());
    }

    //회원 한명 조회
    public MemberResponseDto findOne(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(CUserNotFoundException::new);
        return new MemberResponseDto(member);
    }

    //회원 수정
    @Transactional
    public Long update(Long id, MemberRequestDto memberRequestDto) {
        Member member = memberRepository.findById(id).orElseThrow(CUserNotFoundException::new);
        member.updateNickname(memberRequestDto.getNickname());
        member.updateAboutMe(memberRequestDto.getAboutMe());
        return id;
    }

    //회원 삭제
    @Transactional
    public void delete(Long id) {
        memberRepository.deleteById(id);
    }

}
