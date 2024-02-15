package com.jl.train.member.req;

import com.jl.train.common.req.PageReq;
import lombok.Data;

@Data
public class PassengerQueryReq extends PageReq {
    private Long memberId;
}