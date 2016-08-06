package com.xzh.dao;

import java.util.List;
import java.util.Map;

import com.xzh.entity.SaleChance;

public interface SaleChanceDao {
	
	List<SaleChance> find(Map<String, Object> map);
	
	Long getTotal(Map<String, Object> map);
	
	int add(SaleChance saleChance);
	
	int update(SaleChance saleChance);
	
	int delete(Integer id);
	
	SaleChance findById(Integer id);

}
