package com.jl.train.member.service;

import cn.hutool.core.collection.CollUtil;
import com.jl.train.member.domain.Member;
import com.jl.train.member.domain.MemberExample;
import com.jl.train.member.mapper.MemberMapper;
import com.jl.train.member.req.MemberRegisterReq;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
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
            throw new RuntimeException("手机号已经注册！");
        }
//        开始进行新用户创建，并写入到数据库中
        Member member = new Member();
        member.setId(System.currentTimeMillis());
        member.setMobile(mobile);
        memberMapper.insert(member);
        return member.getId();
    }
}
