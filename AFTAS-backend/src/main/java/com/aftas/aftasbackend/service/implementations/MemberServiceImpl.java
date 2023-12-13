package com.aftas.aftasbackend.service.implementations;

import com.aftas.aftasbackend.enums.IdentityDocumentType;
import com.aftas.aftasbackend.model.dto.MemberDTO;
import com.aftas.aftasbackend.model.entities.Member;
import com.aftas.aftasbackend.repository.MemberRepository;
import com.aftas.aftasbackend.service.IMemberService;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemberServiceImpl implements IMemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public List<MemberDTO> getAllMembers() {
        List<Member> members = memberRepository.findAll();
        return members.stream()
                .map(this::mapEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Member createMember(MemberDTO memberDTO) {
        if (memberRepository.existsByNumAndNameAndFamilyName(
                memberDTO.getNum(), memberDTO.getName(), memberDTO.getFamilyName())) {
            throw new ValidationException("Member already exists.");
        }
        Member member = mapDTOToEntity(memberDTO);

        return memberRepository.save(member);
    }

    private Member mapDTOToEntity(MemberDTO memberDTO) {
        return Member.builder()
                .num(memberDTO.getNum())
                .name(memberDTO.getName())
                .familyName(memberDTO.getFamilyName())
                .accessionDate(memberDTO.getAccessionDate())
                .nationality(memberDTO.getNationality())
                .identityDocument(memberDTO.getIdentityDocument())
                .identityNumber(memberDTO.getIdentityNumber())
                .build();
    }

    private MemberDTO mapEntityToDTO(Member member) {
        return MemberDTO.builder()
                .id(member.getId())
                .num(member.getNum())
                .name(member.getName())
                .familyName(member.getFamilyName())
                .accessionDate(member.getAccessionDate())
                .nationality(member.getNationality())
                .identityDocument(member.getIdentityDocument())
                .identityNumber(member.getIdentityNumber())
                .build();
    }

}
