package com.xzh.dao;

import java.util.List;
import java.util.Map;

import com.xzh.entity.Customer;

public interface CustomerDao {
	
	List<Customer> find(Map<String, Object> map);
	
	Long getTotal(Map<String, Object> map);
	
	int add(Customer customer);
	
	int update(Customer customer);
	
	int delete(Integer id);
	
	Customer findById(Integer id);
	
}
