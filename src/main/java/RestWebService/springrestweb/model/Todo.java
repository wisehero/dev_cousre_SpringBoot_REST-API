package RestWebService.springrestweb.model;

public class Todo {
    private static Long sequence = 0L;

    private final Long memberId;
    private String title;
    private Long todoId;
    private boolean done;

    public Todo(Long memberId, String title) {
        setTodoId(++sequence);
        this.memberId = memberId;
        this.title = title;
        this.done = false;
    }

    public Todo(Long todoId, Long memberId, String title, boolean done) {
        this.todoId = todoId;
        this.memberId = memberId;
        this.title = title;
        this.done = done;
    }

    public Long getTodoId() {
        return todoId;
    }

    public void setTodoId(Long todoId) {
        this.todoId = todoId;
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

    public void changeTitle(String title) {
        this.title = title;
    }

    public void changeDone(boolean done) {
        this.done = !done;
    }
}