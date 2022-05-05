package RestWebService.springrestweb.repository;

import RestWebService.springrestweb.exception.CustomerInsertFailed;
import RestWebService.springrestweb.model.Member;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Statement;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Transactional
public class MemberRepository {

    private static final RowMapper<Member> memberRowMapper = (resultSet, i) -> {
        var memberId = Long.valueOf(resultSet.getInt("member_id"));
        var memberName = resultSet.getString("member_name");
        var email = resultSet.getString("email");
        var createdAt = resultSet.getTimestamp("created_at").toLocalDateTime();
        return new Member(memberId, memberName, email, createdAt);
    };

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public MemberRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private Map<String, Object> toParamMap(Member member) {
        return new HashMap<>() {{
            put("memberId", member.getMemberId());
            put("memberName", member.getMemberName().toString());
            put("email", member.getEmail().toString());
            put("createdAt", member.getCreatedAt());
        }};
    }

    public Member insert(Member member) {
        var update = jdbcTemplate.update(
                "INSERT INTO member(member_id, member_name, email, created_at) VALUES(:memberId, :memberName, :email, :createdAt)",
                toParamMap(member));
        if (update != 1) {
            throw new CustomerInsertFailed("회원 등록이 정상적으로 되지 않았습니다.");
        }
        return member;
    }

    public List<Member> findAll() {
        return jdbcTemplate.query("SELECT * FROM member", memberRowMapper);
    }

    public Member findById(Long memberId) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM member WHERE member_id = :memberId",
                Collections.singletonMap("memberId", memberId), memberRowMapper);
    }

    public Member findByName(String memberName) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM member WHERE member_name = :memberName",
                Collections.singletonMap("memberName", memberName), memberRowMapper);
    }

    public Member findByEmail(String email) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM member WHERE email = :email",
                Collections.singletonMap("email", email), memberRowMapper);
    }

    public boolean existByEmail(String email) {
        var find = jdbcTemplate.query("SELECT * FROM member WHERE email = :email",
                Collections.singletonMap("email", email), memberRowMapper);
        return !find.isEmpty();
    }

    public void delete(Member member) {
        jdbcTemplate.update(
                "DELETE FROM member WHERE member_id = :memberId",
                toParamMap(member));
    }

    public void deleteAll() {
        jdbcTemplate.update("DELETE FROM member", Collections.emptyMap());
    }
}