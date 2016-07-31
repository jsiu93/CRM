package com.xzh.service;

import java.util.List;
import java.util.Map;

import com.xzh.entity.User;

public interface UserService {

	User login(User user);

	List<User> find(Map<String, Object> map);

	Long getTotal(Map<String, Object> map);

	int update(User user);

	int add(User user);

	int delete(Integer id);

}
