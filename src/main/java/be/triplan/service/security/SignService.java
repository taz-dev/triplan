package be.triplan.service.security;

import be.triplan.dto.member.MemberSignUpRequestDto;
import be.triplan.exception.CUserExistException;
import be.triplan.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SignService {

    private final MemberRepository memberRepository;

    @Transactional
    public Long socialSignup(MemberSignUpRequestDto memberSignRequestDto) {
        if (memberRepository
                .findByEmailAndProvider(memberSignRequestDto.getEmail(), memberSignRequestDto.getProvider())
                .isPresent()
        ) throw new CUserExistException();

        return memberRepository.save(memberSignRequestDto.toEntity()).getId();
    }
}
