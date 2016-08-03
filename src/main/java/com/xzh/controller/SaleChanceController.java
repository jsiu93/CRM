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
import com.xzh.entity.SaleChance;
import com.xzh.service.SaleChanceService;
import com.xzh.utils.DateJsonValueProcessor;
import com.xzh.utils.ResponseUtil;
import com.xzh.utils.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

@Controller
@RequestMapping("/saleChance")
public class SaleChanceController {
	
	 @InitBinder
	 public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));   //true:允许输入空值，false:不能为空值
	}

	@Resource
	private SaleChanceService saleChanceService;
	
	
	/**
	 * 分页查询用户
	 * @param page
	 * @param rows
	 * @param s_saleChance
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/list")
	public String list (@RequestParam(value="page", required=false) String page, @RequestParam(value="rows", required=false)String rows, SaleChance s_saleChance, HttpServletResponse response) throws Exception{
		PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
		Map<String , Object> map = Maps.newHashMap();
		map.put("customerName", StringUtil.formatLike(s_saleChance.getCustomerName()));
		map.put("overView", StringUtil.formatLike(s_saleChance.getOverView()));
		map.put("createMan", StringUtil.formatLike(s_saleChance.getCreateMan()));
		map.put("state", s_saleChance.getState());
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		
		List<SaleChance> saleChanceList = saleChanceService.find(map);
		Long total = saleChanceService.getTotal(map);
		
		JSONObject result = new JSONObject();
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd HH:mm"));
		JSONArray jsonArray = JSONArray.fromObject(saleChanceList, jsonConfig);
		result.put("rows", jsonArray);
		result.put("total", total);
		ResponseUtil.write(response, result);
		
		return null;
	}
	
	@RequestMapping("/save")
	public String save(SaleChance saleChance, HttpServletResponse response) throws Exception{
		int resultTotal = 0;
		if(StringUtil.isNotEmpty(saleChance.getAssignMan())){
			saleChance.setState(1);
		} else {
			saleChance.setState(0);
		}
		if(saleChance.getId() == null){
			saleChance.setDevResult(0); //添加时，默认是未开发状态
			resultTotal = saleChanceService.add(saleChance);
		} else {
			resultTotal = saleChanceService.update(saleChance);
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
	public String delete(@RequestParam(value="ids")String ids, HttpServletResponse response) throws Exception{
		String idsStr[] = ids.split(",");
		for (int i = 0; i < idsStr.length; i++) {
			saleChanceService.delete(Integer.parseInt(idsStr[i]));
		}
		JSONObject result = new JSONObject();
		result.put("success", true);
		ResponseUtil.write(response, result);
		
		return null;
	}
	
	
}
