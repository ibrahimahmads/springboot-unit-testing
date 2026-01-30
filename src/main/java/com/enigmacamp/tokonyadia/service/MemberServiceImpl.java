package com.enigmacamp.tokonyadia.service;

import com.enigmacamp.tokonyadia.entity.Member;
import com.enigmacamp.tokonyadia.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MemberServiceImpl implements MemberService{

    MemberRepository memberRepository;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public Member saveMember(Member member) {
        return memberRepository.save(member);
    }

    @Override
    public Member getMemberById(UUID id) {
        return memberRepository.findById(id).get();
    }
}
