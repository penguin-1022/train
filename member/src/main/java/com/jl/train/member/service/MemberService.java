package com.jl.train.member.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.IdUtil;
import com.jl.train.common.exception.BusinessException;
import com.jl.train.common.exception.BusinessExceptionEnum;
import com.jl.train.member.domain.Member;
import com.jl.train.member.domain.MemberExample;
import com.jl.train.member.mapper.MemberMapper;
import com.jl.train.member.req.MemberRegisterReq;
import com.jl.train.member.req.MemberSendCodeReq;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    private static final Logger LOG = LoggerFactory.getLogger(MemberService.class);
    @Resource
    private MemberMapper memberMapper;
    public int count() {
        return Math.toIntExact(memberMapper.countByExample(null));
    }

//    根据用户填写的手机号，实现新用户注册
    public long register(MemberRegisterReq memberRegisterReq) {
        String mobile = memberRegisterReq.getMobile();
//        先进行一下条件查询，判断是否已经注册过了
        MemberExample memberExample = new MemberExample();
        memberExample.createCriteria().andMobileEqualTo(mobile);
//        根据创建好的条件，进行条件查询
        List<Member> list = memberMapper.selectByExample(memberExample);

//        返回的结果不是null，说明已经注册过了，那么报个错就结束了
        if (CollUtil.isNotEmpty(list)) {
//            return list.get(0).getId();
            throw new BusinessException(BusinessExceptionEnum.MEMBER_MOBILE_EXIST);
        }
//        开始进行新用户创建，并写入到数据库中
        Member member = new Member();
        member.setId(IdUtil.getSnowflake(1, 1).nextId());
        member.setMobile(mobile);
        memberMapper.insert(member);
        return member.getId();
    }

    public void sendCode(MemberSendCodeReq req) {
        String mobile = req.getMobile();
        MemberExample example = new MemberExample();
        example.createCriteria().andMobileEqualTo(mobile);
        List<Member> members = memberMapper.selectByExample(example);

        if (CollUtil.isEmpty(members)) {
            LOG.info("手机号不存在，插入一条记录");
            Member member = new Member();
            member.setId(IdUtil.getSnowflake(1, 1).nextId());
            member.setMobile(mobile);
            memberMapper.insert(member);
        } else {
            LOG.info("手机号存在，不插入记录");
        }
//        生成验证码
//        String code = RandomUtil.randomString(4);
        String code = "8888";
        LOG.info("生成短信验证码：{}", code);
//        保存短信记录表：手机号，短信验证码，有效期，是否已经使用，业务类型，使用时间，发送时间
        LOG.info("保存短信记录表");
//        对接短信通道
        LOG.info("对接短信通道");
    }
}
