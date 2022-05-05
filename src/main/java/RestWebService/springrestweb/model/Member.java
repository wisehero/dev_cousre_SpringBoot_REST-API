package RestWebService.springrestweb.model;

import java.time.LocalDateTime;

public class Member {

    private static Long sequence = 0L;

    private final MemberName memberName;
    private final Email email;
    private final LocalDateTime createdAt;
    private Long memberId;

    public Member(String memberName, String email, LocalDateTime createdAt) {
        setMemberId(++sequence);
        this.memberName = new MemberName(memberName);
        this.email = new Email(email);
        this.createdAt = createdAt;
    }

    public Member(Long memberId, String memberName, String email, LocalDateTime createdAt) {
        this.memberId = memberId;
        this.memberName = new MemberName(memberName);
        this.email = new Email(email);
        this.createdAt = createdAt;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public MemberName getMemberName() {
        return memberName;
    }

    public Email getEmail() {
        return email;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}