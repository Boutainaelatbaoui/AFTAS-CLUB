package com.aftas.aftasbackend.service;

import com.aftas.aftasbackend.model.dto.MemberDTO;
import com.aftas.aftasbackend.model.entities.Member;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IMemberService {
    Member createMember(MemberDTO memberDTO);
    public Member getMemberById(Long memberId);
    List<MemberDTO> getAllMembers();
    void deleteMember(Long memberId);
    Member updateMember(Long memberId, MemberDTO memberDTO);
    List<MemberDTO> searchByNum(Integer num);
    List<MemberDTO> searchByName(String name);
    List<MemberDTO> searchByFamilyName(String familyName);

}

