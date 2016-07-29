package com.xzh.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xzh.dao.UserDao;
import com.xzh.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService{

	@Resource
	private UserDao userDao;
}
