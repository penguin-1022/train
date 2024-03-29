package com.jl.train.member.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;
@Data
public class PassengerSaveReq {
    private Long id;
    @NotNull(message = "【会员ID】不能为空")
    private Long memberId;
    @NotBlank(message = "【姓名】不能为空")
    private String name;
    @NotBlank(message = "【身份证号】不能为空")
    private String idCard;
    @NotBlank(message = "【旅客类型】不能为空")
    private String type;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;
}
