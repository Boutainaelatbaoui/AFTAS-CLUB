package com.aftas.aftasbackend.service.implementations;

import com.aftas.aftasbackend.enums.IdentityDocumentType;
import com.aftas.aftasbackend.model.dto.CompetitionDTO;
import com.aftas.aftasbackend.model.dto.MemberDTO;
import com.aftas.aftasbackend.model.dto.Response.MemberDTOResponse;
import com.aftas.aftasbackend.model.entities.Competition;
import com.aftas.aftasbackend.model.entities.Member;
import com.aftas.aftasbackend.repository.MemberRepository;
import com.aftas.aftasbackend.service.IMemberService;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MemberServiceImpl implements IMemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public List<MemberDTOResponse> getAllMembers() {
        List<Member> members = memberRepository.findAll();
        return members.stream()
                .map(this::mapEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Member createMember(MemberDTO memberDTO) {
        if (memberRepository.existsByName(memberDTO.getName())
                && memberRepository.existsByFamilyName(memberDTO.getFamilyName())) {
            throw new ValidationException("Member already exists.");
        }
        if (memberRepository.existsByIdentityNumber(memberDTO.getIdentityNumber())){
            throw new ValidationException("The Identity Number is already exists.");
        }
        Member member = mapDTORToEntity(memberDTO);
        member.setNum(getMemberNum());

        return memberRepository.save(member);
    }


    @Override
    public MemberDTOResponse getMemberById(Long memberId) {
        Optional<Member> memberOptional = memberRepository.findById(memberId);
        MemberDTOResponse competitionDTO = memberOptional
                .map(this::mapEntityToDTO)
                .orElseThrow(() -> new ValidationException("Member not found with id: " + memberId));

        return competitionDTO;
    }

    @Override
    public void deleteMember(Long memberId) {
        Member existingMember = mapDTOToEntity(getMemberById(memberId));
        memberRepository.delete(existingMember);
    }

    @Override
    public Member updateMember(Long memberId, MemberDTO memberDTO) {
        Member existingMember = mapDTOToEntity(getMemberById(memberId));

        existingMember.setName(memberDTO.getName());
        existingMember.setFamilyName(memberDTO.getFamilyName());
        existingMember.setAccessionDate(memberDTO.getAccessionDate());
        existingMember.setNationality(memberDTO.getNationality());
        existingMember.setIdentityDocument(memberDTO.getIdentityDocument());
        existingMember.setIdentityNumber(memberDTO.getIdentityNumber());

        return memberRepository.save(existingMember);
    }

    private Member mapDTOToEntity(MemberDTOResponse memberDTO) {
        return Member.builder()
                .name(memberDTO.getName())
                .familyName(memberDTO.getFamilyName())
                .accessionDate(memberDTO.getAccessionDate())
                .nationality(memberDTO.getNationality())
                .identityDocument(memberDTO.getIdentityDocument())
                .identityNumber(memberDTO.getIdentityNumber())
                .build();
    }

    private Member mapDTORToEntity(MemberDTO memberDTO) {
        return Member.builder()
                .name(memberDTO.getName())
                .familyName(memberDTO.getFamilyName())
                .accessionDate(memberDTO.getAccessionDate())
                .nationality(memberDTO.getNationality())
                .identityDocument(memberDTO.getIdentityDocument())
                .identityNumber(memberDTO.getIdentityNumber())
                .build();
    }

    private Integer getMemberNum() {
        Optional<Integer> maxNum = memberRepository.findMaxNum();
        return maxNum.orElse(0) + 1;
    }

    private MemberDTOResponse mapEntityToDTO(Member member) {
        return MemberDTOResponse.builder()
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
