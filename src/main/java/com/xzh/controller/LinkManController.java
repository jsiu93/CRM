package com.xzh.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.common.collect.Maps;
import com.xzh.entity.LinkMan;
import com.xzh.service.LinkManService;
import com.xzh.utils.DateJsonValueProcessor;
import com.xzh.utils.ResponseUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

@Controller
@RequestMapping("/linkMan")
public class LinkManController {
	

	@Resource
	private LinkManService linkManService;
	
	
	
	/**
	 * 分页查询用户
	 * @param page
	 * @param rows
	 * @param s_linkMan
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/list")
	public String list (@RequestParam(value="cusId", required=false) String cusId,  HttpServletResponse response) throws Exception{
		Map<String , Object> map = Maps.newHashMap();
		map.put("cusId", cusId);
		
		List<LinkMan> linkManList = linkManService.find(map);
		
		JSONObject result = new JSONObject();
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String[]{"customer"});
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));
		JSONArray jsonArray = JSONArray.fromObject(linkManList, jsonConfig);
		result.put("rows", jsonArray);
		ResponseUtil.write(response, result);
		
		return null;
	}
	
	@RequestMapping("/save")
	public String save(LinkMan linkMan, HttpServletResponse response) throws Exception{
		int resultTotal = 0;
		if(linkMan.getId() == null){
			resultTotal = linkManService.add(linkMan);
		} else {
			resultTotal = linkManService.update(linkMan);
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
			linkManService.delete(Integer.parseInt(id));
		JSONObject result = new JSONObject();
		result.put("success", true);
		ResponseUtil.write(response, result);
		
		return null;
	}
	
	
}

