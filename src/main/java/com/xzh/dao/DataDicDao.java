package com.xzh.dao;

import java.util.List;
import java.util.Map;

import com.xzh.entity.DataDic;

public interface DataDicDao {

	List<DataDic> find(Map<String, Object> map);

	List<DataDic> findAll();

	Long getTotal(Map<String, Object> map);

	int update(DataDic dataDic);

	int add(DataDic dataDic);

	int delete(Integer id);

}
