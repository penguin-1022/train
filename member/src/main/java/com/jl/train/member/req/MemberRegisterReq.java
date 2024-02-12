package com.jl.train.member.req;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class MemberRegisterReq {
    @NotBlank(message = "手机号不为空！")
    private String mobile;
}
