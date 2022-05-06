package RestWebService.springrestweb.repository;

import RestWebService.springrestweb.model.Member;
import RestWebService.springrestweb.model.Todo;
import com.zaxxer.hikari.HikariDataSource;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import javax.sql.DataSource;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig
class TodoRepositoryTest {

    @Autowired
    TodoRepository todoRepository;

    @Autowired
    MemberRepository memberRepository;

    @AfterEach
    void clear() {
        todoRepository.deleteAll();
        memberRepository.deleteAll();
    }

    @Test
    @DisplayName("Todo 생성 테스트")
    void insertTodo() {
        Member memberA = new Member("kim", "iop1996@naver.com");
        memberRepository.insert(memberA);
        Todo todoA = new Todo(memberA.getMemberId(), "코딩하기");
        Todo todoB = new Todo(memberA.getMemberId(), "운동하기");
        Todo todoC = new Todo(memberA.getMemberId(), "치킨먹기");

        todoRepository.insert(todoA);
        todoRepository.insert(todoB);
        todoRepository.insert(todoC);

        assertThat(todoRepository.findAll().size()).isEqualTo(3);
    }

    @Test
    @DisplayName("멤버 ID로 Todo 찾기")
    void findAllTodoByMemberIDTest() {
        Member memberA = new Member("kim", "iop1996@naver.com");
        memberRepository.insert(memberA);
        Todo todoA = new Todo(memberA.getMemberId(), "코딩하기");
        Todo todoB = new Todo(memberA.getMemberId(), "운동하기");
        Todo todoC = new Todo(memberA.getMemberId(), "치킨먹기");
        todoRepository.insert(todoA);
        todoRepository.insert(todoB);
        todoRepository.insert(todoC);

        List<Todo> byMemberId = todoRepository.findByMemberId(memberA.getMemberId());

        assertThat(byMemberId.size()).isEqualTo(3);
    }

    @Test
    @DisplayName("Todo 제목 업데이트")
    void updateTodoTitleTest() {
        Member memberA = new Member("kim", "iop1996@naver.com");
        memberRepository.insert(memberA);
        Todo todoA = new Todo(memberA.getMemberId(), "코딩하기");
        todoRepository.insert(todoA);

        todoA.changeTitle("치킨먹기");

        todoRepository.updateTodoTitle(todoA);

        assertThat(todoA.getTitle()).isEqualTo("치킨먹기");
    }

    @Test
    @DisplayName("Todo 완료 테스트")
    void updateDoneTest() {
        Member memberA = new Member("kim", "iop1996@naver.com");
        memberRepository.insert(memberA);
        Todo todoA = new Todo(memberA.getMemberId(), "코딩하기");
        todoRepository.insert(todoA);

        assertThat(todoA.isDone()).isFalse();

        todoA.changeDone(todoA.isDone());
        todoRepository.updateDone(todoA);
        assertThat(todoA.isDone()).isTrue();
    }

    @Test
    @DisplayName("Todo 삭제 테스트")
    void deleteTest() {
        Member memberA = new Member("kim", "iop1996@naver.com");
        memberRepository.insert(memberA);
        Todo todoA = new Todo(memberA.getMemberId(), "코딩하기");
        todoRepository.insert(todoA);

        todoRepository.delete(todoA);

        assertThat(todoRepository.findAll().isEmpty()).isTrue();
    }

    @Configuration
    @ComponentScan(basePackages = {"RestWebService.springrestweb"})
    static class Config {
        @Bean
        public DataSource dataSource() {
            var dataSource = DataSourceBuilder
                    .create()
                    .url("jdbc:mysql://localhost/todoDB")
                    .username("root")
                    .password("xngosem258!")
                    .type(HikariDataSource.class).build();

            return dataSource;
        }
    }
}