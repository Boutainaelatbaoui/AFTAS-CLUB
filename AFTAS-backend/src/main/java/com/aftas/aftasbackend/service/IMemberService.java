package com.aftas.aftasbackend.service;

import com.aftas.aftasbackend.model.dto.MemberDTO;
import com.aftas.aftasbackend.model.entities.Member;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IMemberService {
    Member createMember(MemberDTO memberDTO);
    List<MemberDTO> getAllMembers();

}

