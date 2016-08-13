package com.xzh.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.common.collect.Maps;
import com.xzh.entity.Order;
import com.xzh.entity.PageBean;
import com.xzh.service.OrderService;
import com.xzh.utils.DateJsonValueProcessor;
import com.xzh.utils.ResponseUtil;
import com.xzh.utils.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

@Controller
@RequestMapping("/order")
public class OrderController {
	

	@Resource
	private OrderService orderService;
	
	
	
	/**
	 * 分页查询用户
	 * @param page
	 * @param rows
	 * @param s_order
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/list")
	public String list (@RequestParam(value="page", required=false) String page, @RequestParam(value="rows", required=false)String rows, String cusId,  HttpServletResponse response) throws Exception{
		Map<String , Object> map = Maps.newHashMap();
		PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
		map.put("cusId", cusId);
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		
		List<Order> orderList = orderService.find(map);
		Long total = orderService.getTotal(map);
		JSONObject result = new JSONObject();
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String[]{"customer"});
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));
		JSONArray jsonArray = JSONArray.fromObject(orderList, jsonConfig);
		result.put("rows", jsonArray);
		result.put("total", total);
		ResponseUtil.write(response, result);
		
		return null;
	}
	
	@RequestMapping("/findById")
	public String findById(@RequestParam(value="id")String id, HttpServletResponse response) throws Exception{
		Order order = orderService.findById(Integer.parseInt(id));
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String[]{"order"});
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));
		JSONObject jsonObject = JSONObject.fromObject(order, jsonConfig);
		ResponseUtil.write(response, jsonObject);
		return null;
		
	}
	
	
}

