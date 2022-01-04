package be.triplan.service;

import be.triplan.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender mailSender;
    private final MemberRepository memberRepository;

    @Async
    public void sendEmail(SimpleMailMessage message) {
        mailSender.send(message);
    }

    public void sendCode(String email) {
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
}
