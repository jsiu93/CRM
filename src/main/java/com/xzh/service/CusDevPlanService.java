package com.xzh.service;

import java.util.List;
import java.util.Map;

import com.xzh.entity.CusDevPlan;

public interface CusDevPlanService {
	
	List<CusDevPlan> find(Map<String, Object> map);

	Long getTotal(Map<String, Object> map);

	int update(CusDevPlan cusDevPlan);

	int add(CusDevPlan cusDevPlan);

	int delete(Integer id);

}
