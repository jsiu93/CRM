package com.xzh.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xzh.dao.CustomerLossDao;
import com.xzh.entity.CustomerLoss;
import com.xzh.service.CustomerLossService;

@Service
public class CustomerLossServiceImpl implements CustomerLossService {

	@Resource
	private CustomerLossDao customerLossDao;
	
	public List<CustomerLoss> find(Map<String, Object> map) {
		return customerLossDao.find(map);
	}

	public Long getTotal(Map<String, Object> map) {
		return customerLossDao.getTotal(map);
	}

	public CustomerLoss findById(Integer id) {
		return customerLossDao.findById(id);
	}

	public int update(CustomerLoss customerLoss) {
		return customerLossDao.update(customerLoss);
	}

}
