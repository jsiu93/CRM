package com.xzh.service;

import java.util.List;
import java.util.Map;

import com.xzh.entity.CustomerService;

public interface CustomerServiceService {

	int add(CustomerService customerService);

	List<CustomerService> find(Map<String, Object> map);

	Long getTotal(Map<String, Object> map);

	int update(CustomerService customerService);
}
