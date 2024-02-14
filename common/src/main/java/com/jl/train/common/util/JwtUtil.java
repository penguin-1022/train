package com.jl.train.common.util;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.json.JSONObject;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTPayload;
import cn.hutool.jwt.JWTUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

//本项目中用于生成JWT的统一工具类
public class JwtUtil {
    // 配置Logger
    private static final Logger LOG = LoggerFactory.getLogger(JwtUtil.class);

//    盐值（密钥），每个项目的都应该不一样
    private static final String key = "Jia Liang12306";

//    创建token
    public static String createToken(Long id, String mobile) {
        DateTime now = DateTime.now();
//        多长时间后过期。这里，表示24小时后过期
        DateTime expTime = now.offsetNew(DateField.HOUR, 24);
        Map<String, Object> payload = new HashMap<>();
//        签发时间
        payload.put(JWTPayload.ISSUED_AT, now);
//        过期时间
        payload.put(JWTPayload.EXPIRES_AT, expTime);
//        生效时间
        payload.put(JWTPayload.NOT_BEFORE, now);
//        加入私有数据
        payload.put("id", id);
        payload.put("mobile", mobile);
//        调用静态方法，生成token
        String token = JWTUtil.createToken(payload, key.getBytes());
        LOG.info("生成JWT: {}", token);
        return token;
    }

    public static boolean validate(String token) {
        JWT jwt = JWTUtil.parseToken(token).setKey(key.getBytes());
        boolean validate = jwt.validate(0);
        LOG.info("JWT校验结果：{}", validate);
        return validate;
    }

    public static JSONObject getJSONObject(String token) {
        JWT jwt = JWTUtil.parseToken(token).setKey(key.getBytes());
        JSONObject payloads = jwt.getPayloads();
        payloads.remove(JWTPayload.ISSUED_AT);
        payloads.remove(JWTPayload.EXPIRES_AT);
        payloads.remove(JWTPayload.NOT_BEFORE);
        return payloads;
    }

    public static void main(String[] args) {
        createToken(1L, "188");
        String token = "xxxx.xxxx.xxx";
        validate(token);
        getJSONObject(token);
    }
}
