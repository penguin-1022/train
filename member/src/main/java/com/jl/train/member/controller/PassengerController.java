package com.jl.train.member.controller;

import com.jl.train.common.context.LoginMemberContext;
import com.jl.train.common.resp.CommonResp;
import com.jl.train.common.resp.PageResp;
import com.jl.train.member.req.PassengerQueryReq;
import com.jl.train.member.req.PassengerSaveReq;
import com.jl.train.member.resp.PassengerQueryResp;
import com.jl.train.member.service.PassengerService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/passenger")
public class PassengerController {
    @Resource
    private PassengerService passengerService;

    @PostMapping("/save")
    public CommonResp<Long> save(@Valid @RequestBody PassengerSaveReq req) {
        passengerService.save(req);
        return new CommonResp<>();
    }

    @GetMapping("/query-list")
    public CommonResp<PageResp<PassengerQueryResp>> queryList(@Valid PassengerQueryReq req) {
        req.setMemberId(LoginMemberContext.getId());
        PageResp<PassengerQueryResp> list = passengerService.queryList(req);
        return new CommonResp<>(list);
    }
}
