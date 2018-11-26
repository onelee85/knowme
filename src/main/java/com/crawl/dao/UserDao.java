package com.crawl.dao;

import com.crawl.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author: jiao.li@ttpod.com
 * Date: 2018/11/14 15:23
 */
public interface UserDao extends JpaRepository<User, Long> {
}