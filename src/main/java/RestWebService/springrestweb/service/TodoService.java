package RestWebService.springrestweb.service;

import RestWebService.springrestweb.model.Todo;
import RestWebService.springrestweb.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> create(Todo todo) {
        Todo newTodo = new Todo(todo.getTodoId(), todo.getTitle());
        todoRepository.insert(newTodo);
        return todoRepository.findByMemberId(todo.getMemberId());
    }

    public List<Todo> getTodos() {
        return todoRepository.findAll();
    }

    public List<Todo> getTodosByMemberId(Long memberId) {
        return todoRepository.findByMemberId(memberId);
    }

    public List<Todo> updateTitle(Todo todo) {
        todoRepository.updateTodoTitle(todo);
        return todoRepository.findByMemberId(todo.getMemberId());
    }

    public List<Todo> updateDone(Todo todo) {
        todoRepository.updateDone(todo);
        return todoRepository.findByMemberId(todo.getMemberId());
    }

    public List<Todo> deleteOne(Todo todo) {
        todoRepository.delete(todo);
        return getTodosByMemberId(todo.getMemberId());
    }
}