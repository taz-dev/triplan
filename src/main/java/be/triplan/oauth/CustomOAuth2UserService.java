package be.triplan.oauth;

import be.triplan.oauth.dto.SessionUser;
import be.triplan.oauth.dto.OAuthAttributes;
import be.triplan.api.entity.member.Member;
import be.triplan.api.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final MemberRepository memberRepository;
    private final HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest); //OAuth 서비스(google, naver, kakao)에서 가져온 유저 정보 담고 있음

        //OAuth 서비스 이름(google, naver, kakao)
        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        //OAuth 로그인 시 키가 되는 값(PK)
        String userNameAttributeName = userRequest.getClientRegistration()
                                          .getProviderDetails()
                                          .getUserInfoEndpoint()
                                          .getUserNameAttributeName();

        //OAuth2UserService
        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());

        Member member = saveOrUpdate(attributes);
        httpSession.setAttribute("member", new SessionUser(member)); // SessionUser (직렬화된 dto 클래스 사용)

        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(member.getRoleKey())),
                attributes.getAttributes(),
                attributes.getNameAttributeKey());
    }

    //유저 생성 및 수정 서비스 로직
    private Member saveOrUpdate(OAuthAttributes attributes) {
        Member member = memberRepository.findByEmail(attributes.getEmail())
                .map(m -> m.update(attributes.getNickname(), attributes.getImageUrl())) // OAuth 서비스 사이트에서 유저 정보 변경이 있을 수 있기 때문에 우리 DB에도 update
                .orElse(attributes.toEntity());

        return memberRepository.save(member);
    }
}
