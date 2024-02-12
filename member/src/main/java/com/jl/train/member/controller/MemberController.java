package com.jl.train.member.controller;

import com.jl.train.common.resp.CommonResp;
import com.jl.train.member.req.MemberRegisterReq;
import com.jl.train.member.req.MemberSendCodeReq;
import com.jl.train.member.service.MemberService;
import jakarta.validation.Valid;
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
    public CommonResp<Integer> count() {
        int count = memberService.count();
        CommonResp<Integer> integerCommonResp = new CommonResp<>();
        integerCommonResp.setData(count);
        return integerCommonResp;
    }

    @PostMapping("/register")
//    这个MemberRegisterReq，实际上是一个dto对象
//    用dto是因为，对这样的一个注册功能，要用到表单
//    如果我们每一项都逐个写在参数列表里面，太麻烦了，而且，可能有的参数，用户没填写
    public CommonResp<Long> register(@Valid MemberRegisterReq memberRegisterReq) {
        CommonResp<Long> longCommonResp = new CommonResp<Long>();
        longCommonResp.setData(memberService.register(memberRegisterReq));
        return longCommonResp;
    }

//    请求验证码的接口
    @PostMapping("/send-code")
    public CommonResp sendCode(@Valid MemberSendCodeReq req) {
        memberService.sendCode(req);
        return new CommonResp();
    }
}
