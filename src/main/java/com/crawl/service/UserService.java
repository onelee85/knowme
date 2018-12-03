package com.crawl.service;

import com.crawl.dao.UserDao;
import com.crawl.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: jiao.li@ttpod.com
 * Date: 2018/11/5 10:40
 */
@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserDao userDao;

    public User getById(long id){
        return userDao.findById(id).get();
    }

    public void Update(User user){
        userDao.saveAndFlush(user);
    }
}
