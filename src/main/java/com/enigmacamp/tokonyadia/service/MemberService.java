package com.enigmacamp.tokonyadia.service;

import com.enigmacamp.tokonyadia.entity.Member;

import java.util.UUID;

public interface MemberService {
    Member saveMember(Member member);
    Member getMemberById(UUID id);
    Boolean findMemberByUsername(String username);
}
