package be.triplan.service;

import be.triplan.dto.member.MemberRequestDto;
import be.triplan.dto.member.MemberResponseDto;
import be.triplan.entity.Member;
import be.triplan.exception.UserNotFoundException;
import be.triplan.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    
    //회원 전체 조회
    public List<MemberResponseDto> findAllMembers() {
        return memberRepository.findAll()
                .stream()
                .map(MemberResponseDto::new)
                .collect(Collectors.toList());
    }

    //회원 한명 조회
    public MemberResponseDto findOne(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(UserNotFoundException::new);
        return new MemberResponseDto(member);
    }

    //회원 수정
    @Transactional
    public Long update(Long id, MemberRequestDto memberRequestDto) {
        Member member = memberRepository.findById(id).orElseThrow(UserNotFoundException::new);
        member.updateNickname(memberRequestDto.getNickname());
        member.updateAboutMe(memberRequestDto.getAboutMe());
        return id;
    }

    //회원 삭제
    @Transactional
    public void delete(Long id) {
        memberRepository.deleteById(id);
    }

    //숫자 4자리 nameTag 만들기
    public String createNameTag() {
        StringBuffer tag = new StringBuffer();
        Random random = new Random();

        for (int i = 0; i < 4; i++) {
            String num = "" + tag.append(random.nextInt(10)); //0~9 까지 랜덤으로
        }
        return tag.toString();
    }
}
