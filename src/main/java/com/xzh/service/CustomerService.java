package com.xzh.service;

import java.util.List;
import java.util.Map;

import com.xzh.entity.Customer;

public interface CustomerService {

	List<Customer> find(Map<String, Object> map);

	Long getTotal(Map<String, Object> map);

	int add(Customer customer);

	int update(Customer customer);

	int delete(Integer id);
	
	Customer findById(Integer id);
	
	/**
	 * 查找流失客户并添加到流失客户表
	 */
	void checkCustomerLoss();

}
