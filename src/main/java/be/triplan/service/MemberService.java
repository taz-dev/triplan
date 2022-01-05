package be.triplan.service;

import be.triplan.dto.member.MemberRequestDto;
import be.triplan.dto.member.MemberResponseDto;
import be.triplan.entity.Member;
import be.triplan.exception.TUserNotFoundException;
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

/*    @Transactional
    public Long save(MemberRequestDto memberRequestDto) {
        Member member = memberRepository.save(memberRequestDto.toEntity());
        return member.getId();
    }*/
    
    //회원 전체 조회
    public List<MemberResponseDto> findAllMembers() {
        return memberRepository.findAll()
                .stream()
                .map(MemberResponseDto::new)
                .collect(Collectors.toList());
    }

    //회원 한명 조회
    public MemberResponseDto findOne(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(TUserNotFoundException::new);
        return new MemberResponseDto(member);
    }

    //회원 수정
    @Transactional
    public Long update(Long id, MemberRequestDto memberRequestDto) {
        Member member = memberRepository.findById(id).orElseThrow(TUserNotFoundException::new);
        member.updateNickname(memberRequestDto.getNickname());
        member.updateAboutMe(memberRequestDto.getAboutMe());
        return id;
    }

    //회원 삭제
    @Transactional
    public void delete(Long id) {
        memberRepository.deleteById(id);
    }

    /*
    *  public void sendCode(String email) {
        String code = createCode();
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Triplan : 이메일 인증코드 안내");
        message.setText("인증번호 : " + code);
        sendEmail(message);
    }

    //8자리 인증코드 만들기
    public String createCode() {
        StringBuffer code = new StringBuffer();
        Random random = new Random();

        for (int i = 0; i < 8; i++) {
            int index = random.nextInt(3);

            switch (index) {
                case 0: //a~z
                    code.append((char) (random.nextInt(26) + 97));
                    break;
                case 1: //A~Z
                    code.append((char) (random.nextInt(26) + 65));
                    break;
                case 2: //0~9
                    code.append(random.nextInt(10));
                    break;
            }
        }
        return code.toString();
    }
    *
    * */
}
