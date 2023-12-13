package com.aftas.aftasbackend.controller;

import com.aftas.aftasbackend.model.dto.MemberDTO;
import com.aftas.aftasbackend.model.entities.Member;
import com.aftas.aftasbackend.service.IMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    private final IMemberService memberService;

    @Autowired
    public MemberController(IMemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("")
    public ResponseEntity<String> createMember(@RequestBody @Valid MemberDTO memberDTO) {
        memberService.createMember(memberDTO);
        return new ResponseEntity<>("Member created successfully", HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<MemberDTO>> getAllMembers() {
        List<MemberDTO> members = memberService.getAllMembers();
        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<Member> getMemberById(@PathVariable Long memberId) {
        Member member = memberService.getMemberById(memberId);
        return new ResponseEntity<>(member, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<MemberDTO>> searchMembers(
            @RequestParam(required = false) Integer num,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String familyName) {
        if (num != null) {
            return new ResponseEntity<>(memberService.searchByNum(num), HttpStatus.OK);
        } else if (name != null) {
            return new ResponseEntity<>(memberService.searchByName(name), HttpStatus.OK);
        } else if (familyName != null) {
            return new ResponseEntity<>(memberService.searchByFamilyName(familyName), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{memberId}")
    public ResponseEntity<String> updateMember(@PathVariable Long memberId, @RequestBody @Valid MemberDTO memberDTO) {
        Member updatedMember = memberService.updateMember(memberId, memberDTO);
        return new ResponseEntity<>("The member has been updated.", HttpStatus.OK);
    }

    @DeleteMapping("/{memberId}")
    public ResponseEntity<String> deleteMember(@PathVariable Long memberId) {
        memberService.deleteMember(memberId);
        return new ResponseEntity<>("The Member has been deleted.", HttpStatus.NO_CONTENT);
    }
}
