package be.triplan.repository;

import be.triplan.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    String findByNameTag(String nameTag);
    Optional<Member> findByRefreshToken(String refreshToken);
    Optional<Member> findByEmailAndProvider(String email, String provider);
}
