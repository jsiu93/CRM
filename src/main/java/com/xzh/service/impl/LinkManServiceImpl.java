package com.xzh.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xzh.dao.LinkManDao;
import com.xzh.entity.LinkMan;
import com.xzh.service.LinkManService;

@Service
public class LinkManServiceImpl implements LinkManService{

	@Resource
	private LinkManDao linkManDao;
	
	public List<LinkMan> find(Map<String, Object> map) {
		return linkManDao.find(map);
	}

	public Long getTotal(Map<String, Object> map) {
		return linkManDao.getTotal(map);
	}

	public int update(LinkMan linkMan) {
		return linkManDao.update(linkMan);
	}

	public int add(LinkMan linkMan) {
		return linkManDao.add(linkMan);
	}

	public int delete(Integer id) {
		return linkManDao.delete(id);
	}

}
