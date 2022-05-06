package RestWebService.springrestweb.controller;

import RestWebService.springrestweb.dto.RequestCreateTodo;
import RestWebService.springrestweb.dto.RequestUpdateTodo;
import RestWebService.springrestweb.dto.ResponseTodo;
import RestWebService.springrestweb.model.Todo;
import RestWebService.springrestweb.service.MemberService;
import RestWebService.springrestweb.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth/todos")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public ResponseEntity<List<ResponseTodo>> getAllTodoList() {
        List<ResponseTodo> todos = todoService.getTodos()
                .stream().map(ResponseTodo::new).toList();
        return ResponseEntity.ok(todos);
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<List<ResponseTodo>> getTodoByMember(
            @PathVariable("memberId") Long memberId) {
        List<Todo> todosByMemberId = todoService.getTodosByMemberId(memberId);
        List<ResponseTodo> response =
                todosByMemberId.stream().map(ResponseTodo::new).toList();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/{memberId}")
    public ResponseEntity<?> createTodo(@RequestBody RequestCreateTodo requestCreateTodo) {
        try {
            Todo newTodo = new Todo(requestCreateTodo.getMemberId(), requestCreateTodo.getTitle());
            List<Todo> todos = todoService.create(newTodo);
            List<ResponseTodo> responseTodos = todos.stream().map(ResponseTodo::new).toList();
            return ResponseEntity.status(HttpStatus.CREATED).body(responseTodos);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{memberId}/{todoId}/title")
    public ResponseEntity<List<ResponseTodo>> updateTitle(
            @RequestBody RequestUpdateTodo requestUpdateTodo) {
        Todo updatedTodo = new Todo(
                requestUpdateTodo.getTodoId(),
                requestUpdateTodo.getMemberId(),
                requestUpdateTodo.getTitle(),
                requestUpdateTodo.isDone());

        List<Todo> updatedTodos = todoService.updateTitle(updatedTodo);
        List<ResponseTodo> response = updatedTodos
                .stream().map(ResponseTodo::new).toList();

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{memberId}/{todoId}/done")
    public ResponseEntity<List<ResponseTodo>> updateDone(
            @RequestBody RequestUpdateTodo requestUpdateTodo) {
        Todo doneTodo = new Todo(
                requestUpdateTodo.getTodoId(),
                requestUpdateTodo.getMemberId(),
                requestUpdateTodo.getTitle(),
                requestUpdateTodo.isDone());

        List<Todo> todos = todoService.updateDone(doneTodo);
        List<ResponseTodo> response = todos.stream().map(ResponseTodo::new).toList();
        return ResponseEntity.ok(response);
    }
}