package com.jl.train.member.controller;

import com.jl.train.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
public class MemberController {
    @Autowired
    private MemberService memberService;

    @RequestMapping(method = RequestMethod.GET, value = "/count")
    public Integer count() {
        return memberService.count();
    }

    @PostMapping("/register")
    public Long register(String mobile) {
        return memberService.register(mobile);
    }
}
