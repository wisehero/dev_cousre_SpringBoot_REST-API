package RestWebService.springrestweb.service;

import RestWebService.springrestweb.dto.RequestCreateMember;
import RestWebService.springrestweb.model.Member;
import RestWebService.springrestweb.repository.MemberRepository;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member create(RequestCreateMember requestCreateMember) {
        Member newMember = new Member(requestCreateMember.getMemberName(), requestCreateMember.getEmail());

        if (memberRepository.existByEmail(requestCreateMember.getEmail())) {
            throw new IllegalArgumentException("이미 가입된 회원입니다.");
        }

        return memberRepository.insert(newMember);
    }
}