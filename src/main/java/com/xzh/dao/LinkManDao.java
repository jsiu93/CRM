package com.xzh.dao;

import java.util.List;
import java.util.Map;

import com.xzh.entity.LinkMan;

public interface LinkManDao {

	List<LinkMan> find(Map<String, Object> map);

	Long getTotal(Map<String, Object> map);

	int update(LinkMan linkMan);

	int add(LinkMan linkMan);

	int delete(Integer id);

}
