package com.xzh.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xzh.dao.CustomerServiceDao;
import com.xzh.entity.CustomerService;
import com.xzh.service.CustomerServiceService;

@Service
public class CustomerServiceServiceImpl implements CustomerServiceService{

	@Resource
	private CustomerServiceDao customerServiceDao;
	
	public int add(CustomerService customerService) {
		return customerServiceDao.add(customerService);
	}

	public List<CustomerService> find(Map<String, Object> map) {
		return customerServiceDao.find(map);
	}

	public Long getTotal(Map<String, Object> map) {
		return customerServiceDao.getTotal(map);
	}

	public int update(CustomerService customerService) {
		return customerServiceDao.update(customerService);
	}

}
