package be.triplan.config.security;

import be.triplan.exception.UserNotFoundException;
import be.triplan.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PrincipalDetailService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        return memberRepository.findById(Long.parseLong(userId))
                .orElseThrow(UserNotFoundException::new);
    }

    //기본적인 반환 타입은 UserDetails이지만 UserDetails를 상속받은 Member로 반환 타입 지정(자동으로 다운 캐스팅됨)
/*    @Override
    public Member loadUserByUsername(String email) throws UsernameNotFoundException {
        return memberRepository.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);
    }*/
}
