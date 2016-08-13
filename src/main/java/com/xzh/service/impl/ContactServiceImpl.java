package com.xzh.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xzh.dao.ContactDao;
import com.xzh.entity.Contact;
import com.xzh.service.ContactService;

@Service
public class ContactServiceImpl implements ContactService{

	@Resource
	private ContactDao contactDao;
	
	public List<Contact> find(Map<String, Object> map) {
		return contactDao.find(map);
	}

	public Long getTotal(Map<String, Object> map) {
		return contactDao.getTotal(map);
	}

	public int update(Contact contact) {
		return contactDao.update(contact);
	}

	public int add(Contact contact) {
		return contactDao.add(contact);
	}

	public int delete(Integer id) {
		return contactDao.delete(id);
	}

}
