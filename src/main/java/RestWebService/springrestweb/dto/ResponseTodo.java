package RestWebService.springrestweb.dto;

import RestWebService.springrestweb.model.Todo;

public class ResponseTodo {
    private final Long todoId;
    private final String title;
    private final boolean done;

    public ResponseTodo(Todo todo) {
        this.todoId = todo.getTodoId();
        this.title = todo.getTitle();
        this.done = todo.isDone();
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