package com.xzh.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.common.collect.Maps;
import com.xzh.entity.OrderDetails;
import com.xzh.entity.PageBean;
import com.xzh.service.OrderDetailsService;
import com.xzh.utils.DateJsonValueProcessor;
import com.xzh.utils.ResponseUtil;
import com.xzh.utils.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

@Controller
@RequestMapping("/orderDetails")
public class OrderDetailsController {
	

	@Resource
	private OrderDetailsService orderDetailsService;
	
	
	
	/**
	 * 分页查询用户
	 * @param page
	 * @param rows
	 * @param s_orderDetails
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/list")
	public String list (@RequestParam(value="page", required=false) String page, @RequestParam(value="rows", required=false)String rows,@RequestParam(value="orderId", required=false) String orderId,  HttpServletResponse response) throws Exception{
		if(StringUtil.isEmpty(orderId)){
			return null;
		}
		Map<String , Object> map = Maps.newHashMap();
		PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		map.put("orderId", orderId);
		
		List<OrderDetails> orderDetailsList = orderDetailsService.find(map);
		Long total = orderDetailsService.getTotal(map);
		JSONObject result = new JSONObject();
		JSONArray jsonArray = JSONArray.fromObject(orderDetailsList);
		result.put("rows", jsonArray);
		result.put("total", total);
		ResponseUtil.write(response, result);
		
		return null;
	}
	
	@RequestMapping("/getTotalPrice")
	public String getTotalPrice(String orderId, HttpServletResponse response) throws Exception{
		float totalMoney = orderDetailsService.getTotalPriceByOrderId(Integer.parseInt(orderId));
		JSONObject result = new JSONObject();
		result.put("totalMoney", totalMoney);
		ResponseUtil.write(response, result);
		return null;
	}
	
}

