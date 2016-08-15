package com.xzh.service;

import java.util.List;
import java.util.Map;

import com.xzh.entity.CustomerReprieve;

public interface CustomerReprieveService {
	
	List<CustomerReprieve> find(Map<String, Object> map);

	Long getTotal(Map<String, Object> map);

	int update(CustomerReprieve customerReprieve);

	int add(CustomerReprieve customerReprieve);

	int delete(Integer id);

}
