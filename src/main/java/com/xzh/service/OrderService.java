package com.xzh.service;

import java.util.List;
import java.util.Map;

import com.xzh.entity.Order;

public interface OrderService {

	List<Order> find(Map<String, Object> map);

	Long getTotal(Map<String, Object> map);
	
	Order findById(Integer id);
}
