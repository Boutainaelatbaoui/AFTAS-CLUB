package com.aftas.aftasbackend.service.implementations;

import com.aftas.aftasbackend.enums.IdentityDocumentType;
import com.aftas.aftasbackend.model.dto.CompetitionDTO;
import com.aftas.aftasbackend.model.dto.MemberDTO;
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
    public List<MemberDTO> getAllMembers() {
        List<Member> members = memberRepository.findAll();
        return members.stream()
                .map(this::mapEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<MemberDTO> searchByNum(Integer num) {
        List<Member> members = memberRepository.searchByNum(num);
        return members.stream()
                .map(this::mapEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<MemberDTO> searchByName(String name) {
        List<Member> members = memberRepository.searchByName(name);
        return members.stream()
                .map(this::mapEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<MemberDTO> searchByFamilyName(String familyName) {
        List<Member> members = memberRepository.searchByFamilyName(familyName);
        return members.stream()
                .map(this::mapEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Member createMember(MemberDTO memberDTO) {
        if (memberRepository.existsByIdentityNumber(memberDTO.getIdentityNumber())){
            throw new ValidationException("The Identity Number is already exists.");
        }
        Member member = mapDTOToEntity(memberDTO);
        member.setNum(getMemberNum());

        return memberRepository.save(member);
    }

    private Integer getMemberNum() {
        Optional<Integer> maxNum = memberRepository.findMaxNum();
        return maxNum.orElse(0) + 1;
    }


    @Override
    public Member getMemberById(Long memberId) {

        Optional<Member> memberOptional = memberRepository.findById(memberId);
        Member member = memberOptional.orElseThrow(() -> new ValidationException("Member not found with id: " + memberId));

        return member;
    }

    @Override
    public void deleteMember(Long memberId) {
        Member existingMember = getMemberById(memberId);
        memberRepository.delete(existingMember);
    }

    @Override
    public Member updateMember(Long memberId, MemberDTO memberDTO) {
        Member existingMember = getMemberById(memberId);

        if (!existingMember.getIdentityNumber().equals(memberDTO.getIdentityNumber())) {
            if (memberRepository.existsByIdentityNumber(memberDTO.getIdentityNumber())) {
                throw new ValidationException("The Identity Number is already exists.");
            }
        }

        existingMember.setName(memberDTO.getName());
        existingMember.setFamilyName(memberDTO.getFamilyName());
        existingMember.setAccessionDate(memberDTO.getAccessionDate());
        existingMember.setNationality(memberDTO.getNationality());
        existingMember.setIdentityDocument(memberDTO.getIdentityDocument());
        existingMember.setIdentityNumber(memberDTO.getIdentityNumber());
        existingMember.setNum(existingMember.getNum());

        return memberRepository.save(existingMember);
    }


    public Member mapDTOToEntity(MemberDTO memberDTO) {
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
