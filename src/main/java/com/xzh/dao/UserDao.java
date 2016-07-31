package com.xzh.dao;

import java.util.List;
import java.util.Map;

import com.xzh.entity.User;

public interface UserDao {
	
	User login(User user);
	
	List<User> find(Map<String, Object> map);
	
	Long getTotal(Map<String, Object> map);
	
	int update(User user);
	
	int add(User user);
	
	int delete(Integer id);

}
