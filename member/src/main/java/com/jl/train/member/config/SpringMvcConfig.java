package com.jl.train.member.config;

import com.jl.train.common.interceptor.LogInterceptor;
import com.jl.train.common.interceptor.MemberInterceptor;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SpringMvcConfig implements WebMvcConfigurer {
    @Resource
//    引入拦截器
    private MemberInterceptor memberInterceptor;

    @Resource
    private LogInterceptor logInterceptor;

    @Override
//    启用拦截器（注册到拦截器注册表中）
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(logInterceptor);

        registry.addInterceptor(memberInterceptor)
                .addPathPatterns("/**") // 添加拦截规则
                .excludePathPatterns( // 排除部分路径
                        "/member/member/send-code",
                        "/member/member/login"
                        );
    }
}
