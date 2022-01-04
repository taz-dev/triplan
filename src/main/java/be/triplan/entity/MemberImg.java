package be.triplan.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberImg {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "member_img_Id")
    private Long id;

    private String imgName;

    private String imgPath;

    @OneToOne(mappedBy = "memberImg", fetch = LAZY)
    private Member member;

    public void setMember(Member member) {
        this.member = member;
    }
}
