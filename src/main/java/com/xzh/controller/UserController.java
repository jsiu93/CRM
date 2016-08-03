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
import com.xzh.entity.User;
import com.xzh.service.UserService;
import com.xzh.utils.ResponseUtil;
import com.xzh.utils.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/user")
public class UserController {

	@Resource
	private UserService userService;
	
	/**
	 * 用户登录
	 * @param user
	 * @param request
	 * @return
	 */
	@RequestMapping("/login")
	public String login(User user, HttpServletRequest request){
		User resultUser =  userService.login(user);
		if(resultUser == null){
			request.setAttribute("user", user);
			request.setAttribute("errorMsg", "用户名或密码错误");
			return "login";
		} else {
			HttpSession session =  request.getSession();
			session.setAttribute("currentUser", resultUser);
			return "redirect:/main.jsp";
		}
	}
	
	/**
	 * 分页查询用户
	 * @param page
	 * @param rows
	 * @param s_user
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/list")
	public String list (@RequestParam(value="page", required=false) String page, @RequestParam(value="rows", required=false)String rows, User s_user, HttpServletResponse response) throws Exception{
		PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
		Map<String , Object> map = Maps.newHashMap();
		map.put("userName", StringUtil.formatLike(s_user.getUserName()));
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		
		List<User> userList = userService.find(map);
		Long total = userService.getTotal(map);
		
		JSONObject result = new JSONObject();
		JSONArray jsonArray = JSONArray.fromObject(userList);
		result.put("rows", jsonArray);
		result.put("total", total);
		ResponseUtil.write(response, result);
		
		return null;
	}
	
	@RequestMapping("/save")
	public String save(User user, HttpServletResponse response) throws Exception{
		int resultTotal = 0;
		if(user.getId() == null){
			resultTotal = userService.add(user);
		} else {
			resultTotal = userService.update(user);
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
			userService.delete(Integer.parseInt(idsStr[i]));
		}
		JSONObject result = new JSONObject();
		result.put("success", true);
		ResponseUtil.write(response, result);
		
		return null;
	}
	
	/**
	 * 获取客户经理信息 下拉框用
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/customerManagerComboList")
	public String customerManagerComboList(HttpServletResponse response) throws Exception{
		Map<String, Object> map = Maps.newHashMap();
		map.put("roleName", "客户经理");
		List<User> userList = userService.find(map);
		JSONArray row = JSONArray.fromObject(userList);
		ResponseUtil.write(response, row);
		
		
		return null;
	}
	
}
