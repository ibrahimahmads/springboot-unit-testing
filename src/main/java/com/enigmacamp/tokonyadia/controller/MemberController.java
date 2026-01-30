package com.enigmacamp.tokonyadia.controller;


import com.enigmacamp.tokonyadia.entity.Member;
import com.enigmacamp.tokonyadia.service.MemberService;
import com.enigmacamp.tokonyadia.utils.constant.ApiUrlConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(ApiUrlConstant.MEMBER)
public class MemberController {
    MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping()
    public Member saveMember(@RequestBody Member member){
       return memberService.saveMember(member);
    }

    @GetMapping("/{id}")
    public Member getMemberById(@PathVariable UUID id){
        return memberService.getMemberById(id);
    }
}
