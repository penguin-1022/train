package com.jl.train.member.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageHelper;
import com.jl.train.common.context.LoginMemberContext;
import com.jl.train.member.domain.Passenger;
import com.jl.train.member.domain.PassengerExample;
import com.jl.train.member.mapper.PassengerMapper;
import com.jl.train.member.req.PassengerQueryReq;
import com.jl.train.member.req.PassengerSaveReq;
import com.jl.train.member.resp.PassengerQueryResp;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassengerService {
    @Resource
    private PassengerMapper passengerMapper;

    public void save(PassengerSaveReq req) {
        DateTime now = DateTime.now();
        Passenger passenger = BeanUtil.copyProperties(req, Passenger.class);
        passenger.setMemberId(LoginMemberContext.getId());
        passenger.setId(IdUtil.getSnowflake(1, 1).nextId());
        passenger.setCreateTime(now);
        passenger.setUpdateTime(now);
        passengerMapper.insert(passenger);
    }

    public List<PassengerQueryResp> queryList(PassengerQueryReq req) {
        PassengerExample passengerExample = new PassengerExample();
        PassengerExample.Criteria criteria = passengerExample.createCriteria();
        if (ObjectUtil.isNotEmpty(req.getMemberId())) {
            criteria.andMemberIdEqualTo(req.getMemberId());
        }
        PageHelper.startPage(req.getPageNum(),req.getPageSize());
        List<Passenger> passengers = passengerMapper.selectByExample(passengerExample);
        return BeanUtil.copyToList(passengers, PassengerQueryResp.class);
    }
}
