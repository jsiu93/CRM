package com.xzh.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.common.collect.Maps;
import com.xzh.entity.PageBean;
import com.xzh.entity.SaleChance;
import com.xzh.entity.CusDevPlan;
import com.xzh.entity.CusDevPlan;
import com.xzh.service.CusDevPlanService;
import com.xzh.service.SaleChanceService;
import com.xzh.utils.DateJsonValueProcessor;
import com.xzh.utils.ResponseUtil;
import com.xzh.utils.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

@Controller
@RequestMapping("/cusDevPlan")
public class CusDevPlanController {
	
	 @InitBinder
	 public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));   //true:允许输入空值，false:不能为空值
	}

	@Resource
	private CusDevPlanService cusDevPlanService;
	
	@Resource
	private SaleChanceService saleChanceService;
	
	
	/**
	 * 分页查询用户
	 * @param page
	 * @param rows
	 * @param s_cusDevPlan
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/list")
	public String list (@RequestParam(value="saleChanceId", required=false) String saleChanceId,  HttpServletResponse response) throws Exception{
		Map<String , Object> map = Maps.newHashMap();
		map.put("saleChanceId", saleChanceId);
		
		List<CusDevPlan> cusDevPlanList = cusDevPlanService.find(map);
		
		JSONObject result = new JSONObject();
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String[]{"saleChance"});
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));
		JSONArray jsonArray = JSONArray.fromObject(cusDevPlanList, jsonConfig);
		result.put("rows", jsonArray);
		ResponseUtil.write(response, result);
		
		return null;
	}
	
	@RequestMapping("/save")
	public String save(CusDevPlan cusDevPlan, HttpServletResponse response) throws Exception{
		int resultTotal = 0;
		if(cusDevPlan.getId() == null){
			SaleChance saleChance = new SaleChance();
			saleChance.setId(cusDevPlan.getSaleChance().getId());
			saleChance.setDevResult(1); //状态修改成“开发中”
			saleChanceService.update(saleChance);
			resultTotal = cusDevPlanService.add(cusDevPlan);
		} else {
			resultTotal = cusDevPlanService.update(cusDevPlan);
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
			cusDevPlanService.delete(Integer.parseInt(id));
		JSONObject result = new JSONObject();
		result.put("success", true);
		ResponseUtil.write(response, result);
		
		return null;
	}
	
	@RequestMapping("/updateSaleChanceDevResult")
	public String updateSaleChanceDevResult(@RequestParam(value = "id") String id,
			@RequestParam(value = "devResult") String devResult, HttpServletResponse response) throws Exception{
		SaleChance saleChance = new SaleChance();
		saleChance.setId(Integer.parseInt(id));
		saleChance.setDevResult(Integer.parseInt(devResult));
		int resultTotal = saleChanceService.update(saleChance);
		JSONObject result = new JSONObject();
		if(resultTotal > 0 ){
			result.put("success", true);
		} else {
			result.put("success", false);
		}
		ResponseUtil.write(response, result);
		return null;
	}
	
	
}

