package com.xzh.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xzh.dao.CustomerReprieveDao;
import com.xzh.entity.CustomerReprieve;
import com.xzh.service.CustomerReprieveService;

@Service
public class CustomerReprieveServiceImpl implements CustomerReprieveService{

	@Resource
	private CustomerReprieveDao customerReprieveDao;
	
	public List<CustomerReprieve> find(Map<String, Object> map) {
		return customerReprieveDao.find(map);
	}

	public Long getTotal(Map<String, Object> map) {
		return customerReprieveDao.getTotal(map);
	}

	public int update(CustomerReprieve customerReprieve) {
		return customerReprieveDao.update(customerReprieve);
	}

	public int add(CustomerReprieve customerReprieve) {
		return customerReprieveDao.add(customerReprieve);
	}

	public int delete(Integer id) {
		return customerReprieveDao.delete(id);
	}

}
