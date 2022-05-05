package RestWebService.springrestweb.dto;

public class RequestCreateMember {
    private final String memberName;
    private final String email;

    public RequestCreateMember(String memberName, String email) {
        this.memberName = memberName;
        this.email = email;
    }

    public String getMemberName() {
        return memberName;
    }

    public String getEmail() {
        return email;
    }
}