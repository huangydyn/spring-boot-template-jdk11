package com.template.service;

import com.alibaba.druid.pool.DruidDataSource;
import com.template.representation.repo.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class UserService {

    private UserRepo userRepo;

    @Autowired
    private DruidDataSource druidDataSource;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void find() throws Exception{
        log.info("User Service start, druid active count :{}", druidDataSource.getActiveCount());
        userRepo.findById(1L);
        log.info("User Service start, druid active count :{}", druidDataSource.getActiveCount());
    }
}
