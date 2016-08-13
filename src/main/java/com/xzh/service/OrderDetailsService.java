package com.xzh.service;

import java.util.List;
import java.util.Map;

import com.xzh.entity.OrderDetails;

public interface OrderDetailsService {

	List<OrderDetails> find(Map<String, Object> map);

	Long getTotal(Map<String, Object> map);

	float getTotalPriceByOrderId(Integer orderId);

}
