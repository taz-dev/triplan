package be.triplan.service.security;

import be.triplan.config.security.PrincipalDetail;
import be.triplan.entity.Member;
import be.triplan.exception.UserNotFoundException;
import be.triplan.repository.MemberRepository;
import be.triplan.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PrincipalDetailsService implements UserDetailsService {

    //private final MemberRepository memberRepository;
    private final MemberService memberService;
/*    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        return memberRepository.findById(Long.parseLong(userId))
                .orElseThrow(UserNotFoundException::new);
    }*/
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member principal = memberService.findMemberByEmail(email);
        return new PrincipalDetail(principal);
    }
}
