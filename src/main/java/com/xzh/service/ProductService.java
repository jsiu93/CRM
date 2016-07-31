package com.xzh.service;

import java.util.List;
import java.util.Map;

import com.xzh.entity.Product;

public interface ProductService {

	List<Product> find(Map<String, Object> map);

	Long getTotal(Map<String, Object> map);
}
