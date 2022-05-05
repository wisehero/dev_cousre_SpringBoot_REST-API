package RestWebService.springrestweb.repository;

import RestWebService.springrestweb.exception.todoInsertFailed;
import RestWebService.springrestweb.model.Todo;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class TodoRepository {

    private static final RowMapper<Todo> todoRowMapper = (resultSet, i) -> {
        var todoId = Long.valueOf(resultSet.getInt("todo_id"));
        var memberId = Long.valueOf(resultSet.getInt("member_id"));
        var title = resultSet.getString("title");
        var done = resultSet.getBoolean("done");
        return new Todo(todoId, memberId, title, done);
    };

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public TodoRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private Map<String, Object> toParamMap(Todo todo) {
        return new HashMap<>() {{
            put("todoId", todo.getTodoId());
            put("memberId", todo.getMemberId());
            put("title", todo.getTitle());
            put("done", todo.isDone());
        }};
    }

    public Todo insert(Todo todo) {
        int update = jdbcTemplate.update(
                "INSERT INTO todos(todo_id, title, done, member_id) VALUES(:todoId, :title, :done, :memberId)",
                toParamMap(todo));
        if (update != 1) {
            throw new todoInsertFailed("Todo 등록이 정상적으로 되지 않았습니다.");
        }
        return null;
    }

    public List<Todo> findAll() {
        return jdbcTemplate.query("SELECT * FROM todos", todoRowMapper);
    }

    public List<Todo> findByMemberId(Long memberId) {
        return jdbcTemplate.query(
                "SELECT * FROM todos WHERE member_id = :memberId",
                Collections.singletonMap("memberId", memberId), todoRowMapper);
    }

    public void updateTodoTitle(Todo todo) {
        jdbcTemplate.update("UPDATE todos SET title = :title WHERE todo_id = :todoId", toParamMap(todo));
    }

    public void delete(Todo todo) {
        jdbcTemplate.update("DELETE FROM todos WHERE todo_id = :todoId", toParamMap(todo));
    }

    public void deleteAll() {
        jdbcTemplate.update("DELETE FROM todos", Collections.emptyMap());
    }
}