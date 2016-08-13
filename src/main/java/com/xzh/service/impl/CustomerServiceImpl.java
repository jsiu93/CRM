package com.xzh.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xzh.dao.CustomerDao;
import com.xzh.entity.Customer;
import com.xzh.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService{

	@Resource
	private CustomerDao customerDao;

	public List<Customer> find(Map<String, Object> map) {
		return customerDao.find(map);
	}

	public Long getTotal(Map<String, Object> map) {
		return customerDao.getTotal(map);
	}

	public int add(Customer customer) {
		return customerDao.add(customer);
	}

	public int update(Customer customer) {
		return customerDao.update(customer);
	}

	public int delete(Integer id) {
		return customerDao.delete(id);
	}

	public Customer findById(Integer id) {
		return customerDao.findById(id);
	}
}
