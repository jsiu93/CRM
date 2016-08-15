package com.xzh.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.common.collect.Maps;
import com.xzh.entity.CustomerReprieve;
import com.xzh.service.CustomerReprieveService;
import com.xzh.utils.DateJsonValueProcessor;
import com.xzh.utils.ResponseUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

@Controller
@RequestMapping("/customerReprieve")
public class CustomerReprieveController {
	

	@Resource
	private CustomerReprieveService customerReprieveService;
	
	
	
	/**
	 * 分页查询用户
	 * @param page
	 * @param rows
	 * @param s_customerReprieve
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/list")
	public String list (@RequestParam(value="lossId", required=false) String lossId,  HttpServletResponse response) throws Exception{
		Map<String , Object> map = Maps.newHashMap();
		map.put("lossId", lossId);
		
		List<CustomerReprieve> customerReprieveList = customerReprieveService.find(map);
		
		JSONObject result = new JSONObject();
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String[]{"customerLoss"});
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));
		JSONArray jsonArray = JSONArray.fromObject(customerReprieveList, jsonConfig);
		result.put("rows", jsonArray);
		ResponseUtil.write(response, result);
		
		return null;
	}
	
	@RequestMapping("/save")
	public String save(CustomerReprieve customerReprieve, HttpServletResponse response) throws Exception{
		int resultTotal = 0;
		if(customerReprieve.getId() == null){
			resultTotal = customerReprieveService.add(customerReprieve);
		} else {
			resultTotal = customerReprieveService.update(customerReprieve);
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
	
	@RequestMapping("/delete")
	public String delete(@RequestParam(value="id")String id, HttpServletResponse response) throws Exception{
			customerReprieveService.delete(Integer.parseInt(id));
		JSONObject result = new JSONObject();
		result.put("success", true);
		ResponseUtil.write(response, result);
		
		return null;
	}
	
	
}

