package RestWebService.springrestweb.dto;

public class RequestCreateTodo {
    private final Long memberId;
    private final String title;

    public RequestCreateTodo(Long memberId, String title) {
        this.memberId = memberId;
        this.title = title;
    }

    public Long getMemberId() {
        return memberId;
    }

    public String getTitle() {
        return title;
    }
}