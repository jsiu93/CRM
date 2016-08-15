package com.xzh.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.common.collect.Maps;
import com.xzh.entity.CustomerLoss;
import com.xzh.entity.PageBean;
import com.xzh.service.CustomerLossService;
import com.xzh.utils.DateJsonValueProcessor;
import com.xzh.utils.ResponseUtil;
import com.xzh.utils.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

@Controller
@RequestMapping("/customerLoss")
public class CustomerLossController {
	

	@Resource
	private CustomerLossService customerLossService;
	
	
	
	/**
	 * 客户流失用户
	 * @param page
	 * @param rows
	 * @param s_customerLoss
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/list")
	public String list (@RequestParam(value="page", required=false) String page, @RequestParam(value="rows", required=false) String rows, CustomerLoss s_customerLoss,  HttpServletResponse response) throws Exception{
		Map<String , Object> map = Maps.newHashMap();
		PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
		map.put("cusName", StringUtil.formatLike(s_customerLoss.getCusName()));
		map.put("cusManager", StringUtil.formatLike(s_customerLoss.getCusManager()));
		map.put("state", s_customerLoss.getState());
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		
		List<CustomerLoss> customerLossList = customerLossService.find(map);
		Long total = customerLossService.getTotal(map);
		JSONObject result = new JSONObject();
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));
		JSONArray jsonArray = JSONArray.fromObject(customerLossList, jsonConfig);
		result.put("rows", jsonArray);
		result.put("total", total);
		ResponseUtil.write(response, result);
		
		return null;
	}
	
	@RequestMapping("/findById")
	public String findById(@RequestParam(value="id")String id, HttpServletResponse response) throws Exception{
		CustomerLoss customerLoss = customerLossService.findById(Integer.parseInt(id));
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));
		JSONObject jsonObject = JSONObject.fromObject(customerLoss, jsonConfig);
		ResponseUtil.write(response, jsonObject);
		return null;
	}
	
	/**
	 * 确认客户流失
	 * @param id
	 * @param lossReason
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/confirmLoss")
	public String confirmLoss(int id, String lossReason, HttpServletResponse response) throws Exception{
		CustomerLoss customerLoss = new CustomerLoss();
		customerLoss.setId(id);
		customerLoss.setLossReason(lossReason);
		customerLoss.setConfirmLossTime(new Date());
		customerLoss.setState(1);
		customerLossService.update(customerLoss);
		JSONObject result = new JSONObject();
		result.put("success", true);
		ResponseUtil.write(response, result);
		
		return null;
	}
	
}

