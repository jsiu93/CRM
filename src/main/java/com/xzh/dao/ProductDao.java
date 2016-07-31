package com.xzh.dao;

import java.util.List;
import java.util.Map;

import com.xzh.entity.Product;

public interface ProductDao {
	
	List<Product> find(Map<String, Object> map);
	
	Long getTotal(Map<String, Object> map);
	

}
