package com.xzh.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xzh.dao.CusDevPlanDao;
import com.xzh.entity.CusDevPlan;
import com.xzh.service.CusDevPlanService;

@Service
public class CusDevPlanServiceImpl  implements CusDevPlanService{
	
	@Resource
	private CusDevPlanDao cusDevPlanDao;

	public List<CusDevPlan> find(Map<String, Object> map) {
		return cusDevPlanDao.find(map);
	}

	public Long getTotal(Map<String, Object> map) {
		return cusDevPlanDao.getTotal(map);
	}

	public int update(CusDevPlan cusDevPlan) {
		return cusDevPlanDao.update(cusDevPlan);
	}

	public int add(CusDevPlan cusDevPlan) {
		return cusDevPlanDao.add(cusDevPlan);
	}

	public int delete(Integer id) {
		return cusDevPlanDao.delete(id);
	}

	
}
