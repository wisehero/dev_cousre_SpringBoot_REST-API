package RestWebService.springrestweb.model;

import java.util.Objects;

public class MemberName {
    private final String memberName;

    public MemberName(String memberName) {
        validateName(memberName);
        this.memberName = memberName;
    }

    private void validateName(String memberName) {
        if (memberName.isBlank()) {
            throw new IllegalArgumentException("회원의 이름은 공백이 올 수 없습니다.");
        }
    }

    @Override
    public String toString() {
        return memberName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MemberName)) return false;
        MemberName that = (MemberName) o;
        return Objects.equals(memberName, that.memberName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberName);
    }
}