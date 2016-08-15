package com.xzh.dao;

import java.util.List;
import java.util.Map;

import com.xzh.entity.CustomerLoss;

public interface CustomerLossDao {

	List<CustomerLoss> find(Map<String, Object> map);

	Long getTotal(Map<String, Object> map);

	int add(CustomerLoss customerLoss);
	
	CustomerLoss findById(Integer id);
	
	int update(CustomerLoss customerLoss);
	
}
