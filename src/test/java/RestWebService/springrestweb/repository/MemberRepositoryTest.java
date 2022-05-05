package RestWebService.springrestweb.repository;

import RestWebService.springrestweb.model.Member;
import com.zaxxer.hikari.HikariDataSource;
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
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringJUnitConfig
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @AfterEach
    void clear() {
        memberRepository.deleteAll();
    }

    @Test
    @DisplayName("회원 저장 테스트")
    void insertTest() {
        Member memberA = new Member("kim", "iop1996@naver.com", LocalDateTime.now());
        Member memberB = new Member("kim", "iop1996@naver.com", LocalDateTime.now());
        Member memberC = new Member("kim", "iop1996@naver.com", LocalDateTime.now());

        memberRepository.insert(memberA);
        memberRepository.insert(memberB);
        memberRepository.insert(memberC);

        List<Member> all = memberRepository.findAll();
        assertThat(all.size()).isEqualTo(3);
    }

    @Test
    @DisplayName("회원을 이름으로 조회")
    void findByNameTest() {
        Member member = new Member("kim", "iop1996@naver.com", LocalDateTime.now());
        memberRepository.insert(member);

        Member findMember = memberRepository.findByName("kim");
        assertThat(findMember.getMemberName().toString()).isEqualTo("kim");
    }

    @Test
    @DisplayName("회원을 이메일로 조회")
    void findByEmailTest() {
        Member member = new Member("kim", "iop1996@naver.com", LocalDateTime.now());
        memberRepository.insert(member);

        Member findMember = memberRepository.findByEmail(member.getEmail().toString());
        assertThat(findMember.getEmail().toString()).isEqualTo("iop1996@naver.com");
    }

    @Test
    @DisplayName("회원 삭제")
    void deleteTest() {
        Member member = new Member("kim", "iop1996@naver.com", LocalDateTime.now());
        Member insertedMember = memberRepository.insert(member);

        memberRepository.delete(insertedMember);

        assertThat(memberRepository.findAll().size()).isEqualTo(0);
    }

    @Test
    void deleteAll() {
        memberRepository.deleteAll();
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