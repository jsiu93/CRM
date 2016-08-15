package com.xzh.service;

import java.util.List;
import java.util.Map;

import com.xzh.entity.CustomerLoss;

public interface CustomerLossService {

	List<CustomerLoss> find(Map<String, Object> map);

	Long getTotal(Map<String, Object> map);

	CustomerLoss findById(Integer id);

	int update(CustomerLoss customerLoss);
}
