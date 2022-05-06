package RestWebService.springrestweb.dto;

public class ResponseUpdatedTodo {

    private final Long todoId;
    private final String title;
    private final boolean done;

    public ResponseUpdatedTodo(Long todoId, String title, boolean done) {
        this.todoId = todoId;
        this.title = title;
        this.done = done;
    }

    public Long getTodoId() {
        return todoId;
    }

    public String getTitle() {
        return title;
    }

    public boolean isDone() {
        return done;
    }
}