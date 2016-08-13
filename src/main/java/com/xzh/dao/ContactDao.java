package com.xzh.dao;

import java.util.List;
import java.util.Map;

import com.xzh.entity.Contact;

public interface ContactDao {

	List<Contact> find(Map<String, Object> map);

	Long getTotal(Map<String, Object> map);

	int update(Contact contact);

	int add(Contact contact);

	int delete(Integer id);

}
