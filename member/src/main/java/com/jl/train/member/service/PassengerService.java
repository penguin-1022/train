package com.jl.train.member.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.IdUtil;
import com.jl.train.common.context.LoginMemberContext;
import com.jl.train.member.domain.Passenger;
import com.jl.train.member.mapper.PassengerMapper;
import com.jl.train.member.req.PassengerSaveReq;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

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
}
