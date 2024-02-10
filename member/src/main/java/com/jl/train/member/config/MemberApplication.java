package com.jl.train.member.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

@SpringBootApplication
//不能只扫描com。因为只扫描com的话，会把第三方jar包也扫描上，会出问题
@ComponentScan("com.jl")
public class MemberApplication {
    private static final Logger LOG = LoggerFactory.getLogger(MemberApplication.class);
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(MemberApplication.class);
        Environment env = app.run(args).getEnvironment();
        LOG.info("启动成功！");
        LOG.info("地址：\thttp://localhost:{}{}", env.getProperty("server.port"), env.getProperty("server.servlet.context-path"));
    }
}
