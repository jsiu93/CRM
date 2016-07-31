package com.xzh.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xzh.dao.ProductDao;
import com.xzh.entity.Product;
import com.xzh.service.ProductService;

@Service
public class ProdcutServiceImpl implements ProductService{

	@Resource
	private ProductDao productDao;
	
	public List<Product> find(Map<String, Object> map) {
		return productDao.find(map);
	}

	public Long getTotal(Map<String, Object> map) {
		return productDao.getTotal(map);
	}

}
