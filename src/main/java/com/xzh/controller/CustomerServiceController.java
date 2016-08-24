package com.xzh.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.common.collect.Maps;
import com.xzh.entity.Contact;
import com.xzh.entity.Customer;
import com.xzh.entity.CustomerService;
import com.xzh.entity.PageBean;
import com.xzh.service.CustomerServiceService;
import com.xzh.utils.DateJsonValueProcessor;
import com.xzh.utils.ResponseUtil;
import com.xzh.utils.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

@Controller
@RequestMapping("/customerService")
public class CustomerServiceController {
	
	 @InitBinder
	 public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));   //true:允许输入空值，false:不能为空值
	}

	@Resource
	private CustomerServiceService customerServiceService;
	
	
	
	
	@RequestMapping("/save")
	public String save(CustomerService customerService, HttpServletResponse response) throws Exception{
		int resultTotal = 0;
		if(customerService.getId() == null){
			resultTotal = customerServiceService.add(customerService);
		} else {
			resultTotal = customerServiceService.update(customerService);
		}
		
		JSONObject result = new JSONObject();
		if(resultTotal > 0 ){
			result.put("success", true);
		} else {
			result.put("success", false);
		}
		ResponseUtil.write(response, result);
		return null;
	}
	
	@RequestMapping("/list")
	public String list (@RequestParam(value="page", required=false) String page, String createTimefrom,String createTimeto, @RequestParam(value="rows", required=false)String rows, CustomerService s_customerService, HttpServletResponse response) throws Exception{
		PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
		Map<String , Object> map = Maps.newHashMap();
		map.put("customer", StringUtil.formatLike(s_customerService.getCustomer()));
		map.put("overview", StringUtil.formatLike(s_customerService.getOverview()));
		map.put("serveType", s_customerService.getServeType());
		map.put("createTimefrom", createTimefrom);
		map.put("createTimeto", createTimeto);
		map.put("state", s_customerService.getState());
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		
		List<CustomerService> customerServiceList = customerServiceService.find(map);
		Long total = customerServiceService.getTotal(map);
		
		JSONObject result = new JSONObject();
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));
		JSONArray jsonArray = JSONArray.fromObject(customerServiceList, jsonConfig);
		result.put("rows", jsonArray);
		result.put("total", total);
		ResponseUtil.write(response, result);
		
		return null;
	}
	
}

