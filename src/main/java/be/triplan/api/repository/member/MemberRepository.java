package be.triplan.api.repository.member;

import be.triplan.api.entity.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    //Optional<Member> findByEmail(String email);
    public Member findByEmail(String email);
}
