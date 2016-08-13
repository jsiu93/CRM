package com.xzh.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.common.collect.Maps;
import com.xzh.entity.PageBean;
import com.xzh.entity.DataDic;
import com.xzh.service.DataDicService;
import com.xzh.utils.ResponseUtil;
import com.xzh.utils.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/dataDic")
public class DataDicController {

	@Resource
	private DataDicService dataDicService;
	
	
	/**
	 * 分页查询
	 * @param page
	 * @param rows
	 * @param s_dataDic
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/list")
	public String list (@RequestParam(value="page", required=false) String page, @RequestParam(value="rows", required=false)String rows, DataDic s_dataDic, HttpServletResponse response) throws Exception{
		PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
		Map<String , Object> map = Maps.newHashMap();
		map.put("dataDicName", s_dataDic.getDataDicName());
		map.put("dataDicValue", StringUtil.formatLike(s_dataDic.getDataDicValue()));
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		
		List<DataDic> dataDicList = dataDicService.find(map);
		Long total = dataDicService.getTotal(map);
		
		JSONObject result = new JSONObject();
		JSONArray jsonArray = JSONArray.fromObject(dataDicList);
		result.put("rows", jsonArray);
		result.put("total", total);
		ResponseUtil.write(response, result);
		
		return null;
	}
	
	/**
	 * 查询所有数据字典名称
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/findDataDicName")
	public String dataDicNameComboList(HttpServletResponse response) throws Exception{
		List<DataDic> dataDicList = dataDicService.findAll();
		JSONArray rows = JSONArray.fromObject(dataDicList);
		ResponseUtil.write(response, rows);
		
		return null;
	}
	
	@RequestMapping("/save")
	public String save(DataDic dataDic, HttpServletResponse response) throws Exception{
		int resultTotal = 0;
		if(dataDic.getId() == null){
			resultTotal = dataDicService.add(dataDic);
		} else {
			resultTotal = dataDicService.update(dataDic);
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
	
	/**
	 * 根据数据字典名称查找，用于下拉框
	 * @param dataDicName
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/findDataDic")
	public String dataDicComboList(String dataDicName, HttpServletResponse response) throws Exception{
		JSONArray jsonArray = new JSONArray();
		Map<String, Object> map = Maps.newHashMap();
		map.put("dataDicName", dataDicName);
		List<DataDic> dataDicList = dataDicService.find(map);
		JSONArray rows = JSONArray.fromObject(dataDicList);
		ResponseUtil.write(response, rows);
		return null;
		
	}

	@RequestMapping("/delete")
	public String delete(@RequestParam(value="ids")String ids, HttpServletResponse response) throws Exception{
		String idsStr[] = ids.split(",");
		for (int i = 0; i < idsStr.length; i++) {
			dataDicService.delete(Integer.parseInt(idsStr[i]));
		}
		JSONObject result = new JSONObject();
		result.put("success", true);
		ResponseUtil.write(response, result);
		
		return null;
	}
	
}
