package com.jl.train.common.resp;

import lombok.Data;

@Data
//返回给客户端的数据实体定义
public class MemberLoginResp {
    private Long id;
    private String mobile;
    private String token;
}
