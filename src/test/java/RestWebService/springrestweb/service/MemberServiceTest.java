package RestWebService.springrestweb.service;

import RestWebService.springrestweb.dto.RequestCreateMember;
import RestWebService.springrestweb.model.Member;
import RestWebService.springrestweb.repository.MemberRepository;
import com.zaxxer.hikari.HikariDataSource;
import org.assertj.core.api.Assertions;
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

@SpringJUnitConfig
public class MemberServiceTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MemberService memberService;

    @Test
    @DisplayName("유저 생성 테스트")
    void createTest() {
        RequestCreateMember requestCreateMember = new RequestCreateMember("김지웅", "iop1996@naver.com");
        memberService.create(requestCreateMember);

        List<Member> all = memberRepository.findAll();
        Assertions.assertThat(all.size()).isEqualTo(1);
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