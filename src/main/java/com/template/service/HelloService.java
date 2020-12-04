package com.template.service;

import com.alibaba.druid.pool.DruidDataSource;
import com.template.representation.repo.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class HelloService {

    private UserRepo userRepo;

    private UserService userService;

    @Autowired
    private DruidDataSource druidDataSource;

    public HelloService(UserRepo userRepo, UserService userService) {
        this.userRepo = userRepo;
        this.userService = userService;
    }

    @Transactional
    public void hello() throws Exception{
        log.info("HelloService start, druid active count :{}", druidDataSource.getActiveCount());
        userRepo.findAll();
        Thread.currentThread().sleep(3000);
        userService.find();
        log.info("HelloService end, druid active count :{}", druidDataSource.getActiveCount());
    }
}
