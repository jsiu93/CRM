package com.xzh.dao;

import java.util.List;
import java.util.Map;

import com.xzh.entity.Order;

public interface OrderDao {

	List<Order> find(Map<String, Object> map);

	Long getTotal(Map<String, Object> map);

	Order findById(Integer id);
	
	/**
	 * 查找指定客户最近的订单
	 * @param cusId
	 * @return
	 */
	Order findLastOrderByCusId(int cusId);
	
}
