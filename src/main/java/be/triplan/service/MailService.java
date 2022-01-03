package be.triplan.service;

import be.triplan.config.security.JwtProvider;
import be.triplan.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender javaMailSender;
    private final JwtProvider jwtProvider;
    private final MemberRepository memberRepository;
}
