package be.triplan.repository;

import be.triplan.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);
    Optional<Member> findByProvider(String provider);
    Optional<Member> findByEmailAndProvider(String email, String provider);
}
