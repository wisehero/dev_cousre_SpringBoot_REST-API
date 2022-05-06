package RestWebService.springrestweb.service;

import RestWebService.springrestweb.model.Member;
import RestWebService.springrestweb.model.Todo;
import RestWebService.springrestweb.repository.MemberRepository;
import RestWebService.springrestweb.repository.TodoRepository;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import javax.sql.DataSource;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringJUnitConfig
class TodoServiceTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    TodoRepository todoRepository;

    @Autowired
    TodoService todoService;

    Member member;

    Todo todoA;

    @BeforeEach
    void setUp() {
        member = new Member("kim", "iop1996@naver.com");
        memberRepository.insert(member);

        todoA = new Todo(member.getMemberId(), "과제하기");
        Todo todoB = new Todo(member.getMemberId(), "코딩하기");
        Todo todoC = new Todo(member.getMemberId(), "잠자기");

        todoRepository.insert(todoA);
        todoRepository.insert(todoB);
        todoRepository.insert(todoC);
    }

    @AfterEach
    void clear() {
        todoRepository.deleteAll();
        memberRepository.deleteAll();
    }

    @Test
    @DisplayName("전체 Todo 조회")
    void testGetTodos() {
        List<Todo> todos = todoService.getTodos();

        assertThat(todos.size()).isEqualTo(3);
    }

    @Test
    @DisplayName("같은 멤버 ID를 가지는 Todo 조회")
    void testGetTodosByMemberId() {
        List<Todo> todosByMemberId = todoService.getTodosByMemberId(member.getMemberId());

        assertThat(todosByMemberId.size()).isEqualTo(3);
    }

    @Test
    @DisplayName("제목 변경 테스트")
    void updateTitle() {
        todoA.changeTitle("노래듣기");
        todoService.updateTitle(todoA);

        assertThat(todoA.getTitle()).isEqualTo("노래듣기");
    }

    @Test
    @DisplayName("Todo 완료 테스트")
    void testUpdateDone() {
        todoA.changeDone();
        todoService.updateDone(todoA);

        assertThat(todoA.isDone()).isTrue();
    }

    @Test
    @DisplayName("Todo 한 개 삭제 테스트")
    void testDeleteOne() {
        List<Todo> todos = todoService.deleteOne(todoA);

        assertThat(todos.size()).isEqualTo(2);
    }

    @Configuration
    @ComponentScan(basePackages = {"RestWebService.springrestweb"})
    static class Config {
        @Bean
        public DataSource dataSource() {
            var dataSource = DataSourceBuilder.create().url("jdbc:mysql://localhost/todoDB").username("root").password("xngosem258!").type(HikariDataSource.class).build();

            return dataSource;
        }
    }
}