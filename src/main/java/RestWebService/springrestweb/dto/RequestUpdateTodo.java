package RestWebService.springrestweb.dto;

public class RequestUpdateTodo {
    private Long todoId;
    private Long memberId;
    private String title;
    private boolean done;

    public RequestUpdateTodo(Long todoId, Long memberId, String title, boolean done) {
        this.todoId = todoId;
        this.memberId = memberId;
        this.title = title;
        this.done = done;
    }

    public Long getTodoId() {
        return todoId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public String getTitle() {
        return title;
    }

    public boolean isDone() {
        return done;
    }

}