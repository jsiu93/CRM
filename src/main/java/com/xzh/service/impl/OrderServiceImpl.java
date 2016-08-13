package com.xzh.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xzh.dao.OrderDao;
import com.xzh.entity.Order;
import com.xzh.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{

	@Resource
	private OrderDao orderDao;
	
	public List<Order> find(Map<String, Object> map) {
		return orderDao.find(map);
	}

	public Long getTotal(Map<String, Object> map) {
		return orderDao.getTotal(map);
	}

	public Order findById(Integer id) {
		return orderDao.findById(id);
	}

}
