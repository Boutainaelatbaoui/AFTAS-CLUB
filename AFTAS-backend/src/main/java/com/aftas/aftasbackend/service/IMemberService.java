package com.aftas.aftasbackend.service;

import com.aftas.aftasbackend.model.dto.MemberDTO;
import com.aftas.aftasbackend.model.dto.Response.MemberDTOResponse;
import com.aftas.aftasbackend.model.entities.Member;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IMemberService {
    Member createMember(MemberDTO memberDTO);
    List<MemberDTOResponse> getAllMembers();
    MemberDTOResponse getMemberById(Long memberId);
    void deleteMember(Long memberId);
    Member updateMember(Long memberId, MemberDTO memberDTO);

}

