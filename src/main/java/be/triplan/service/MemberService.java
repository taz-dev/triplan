package be.triplan.service;

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
    public Long update(Long id, MemberResponseDto responseDto) {
        Member member = memberRepository.findById(id).orElseThrow(UserNotFoundException::new);
        member.updateNickname(responseDto.getNickname());
        member.updateAboutMe(responseDto.getAboutMe());
        member.updateImage(responseDto.getMemberImage());
        return id;
    }

    //회원 삭제
    @Transactional
    public void delete(Long id) {
        memberRepository.deleteById(id);
    }

    //숫자 4자리 nameTag 만들기
    public String createNameTag() {
        int length = 4;
        char[] chars = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

        Random random = new Random();
        StringBuffer tag = new StringBuffer();

        for (int i = 0; i < length; i++) {
            char ch = chars[random.nextInt(chars.length)];
            tag.append(ch);
            
            //이미 사용중인 nameTag 는 memberRepository 에서 찾아 중복제거해주는 로직 짜기
        }
        
        return tag.toString();
    }
}
