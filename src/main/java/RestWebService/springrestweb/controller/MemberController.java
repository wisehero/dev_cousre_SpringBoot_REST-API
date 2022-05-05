package RestWebService.springrestweb.controller;

import RestWebService.springrestweb.dto.RequestCreateMember;
import RestWebService.springrestweb.dto.ResponseMember;
import RestWebService.springrestweb.model.Member;
import RestWebService.springrestweb.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping
    public ResponseEntity<?> registerMember(@RequestBody RequestCreateMember requestCreateMember) {
        try {
            Member registeredMember = memberService.create(requestCreateMember);
            ResponseMember responseMember = new ResponseMember(
                    registeredMember.getMemberId(),
                    registeredMember.getMemberName().toString(),
                    registeredMember.getEmail().toString(),
                    registeredMember.getCreatedAt()
            );
            return ResponseEntity.ok().body(responseMember);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    @DeleteMapping
    public void clearMembers() {
        
    }
}