package RestWebService.springrestweb.dto;

import java.time.LocalDateTime;

public class ResponseMember {
    private final Long memberId;
    private final String memberName;
    private final String email;
    private final LocalDateTime localDateTime;

    public ResponseMember(Long memberId, String memberName, String email, LocalDateTime localDateTime) {
        this.memberId = memberId;
        this.memberName = memberName;
        this.email = email;
        this.localDateTime = localDateTime;
    }

    public Long getMemberId() {
        return memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }
}