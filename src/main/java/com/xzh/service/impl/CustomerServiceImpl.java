package com.xzh.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xzh.dao.CustomerDao;
import com.xzh.dao.CustomerLossDao;
import com.xzh.dao.OrderDao;
import com.xzh.entity.Customer;
import com.xzh.entity.CustomerLoss;
import com.xzh.entity.Order;
import com.xzh.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService{

	@Resource
	private CustomerDao customerDao;
	
	@Resource
	private CustomerLossDao customerLossDao;
	
	@Resource
	private OrderDao orderDao;

	public List<Customer> find(Map<String, Object> map) {
		return customerDao.find(map);
	}

	public Long getTotal(Map<String, Object> map) {
		return customerDao.getTotal(map);
	}

	public int add(Customer customer) {
		return customerDao.add(customer);
	}

	public int update(Customer customer) {
		return customerDao.update(customer);
	}

	public int delete(Integer id) {
		return customerDao.delete(id);
	}

	public Customer findById(Integer id) {
		return customerDao.findById(id);
	}

	public void checkCustomerLoss() {
		List<Customer> customerList = customerDao.findLossCustomer();	//查找流失客户
		for (Customer customer : customerList) {
			CustomerLoss customerLoss = new CustomerLoss();
			customerLoss.setCusNo(customer.getKhno());	//客户编号
			customerLoss.setCusName(customer.getName());	//客户名称
			customerLoss.setCusManager(customer.getCusManager());
			Order order = orderDao.findLastOrderByCusId(customer.getId());	//查找指定客户最近的订单
			if(order == null){
				customerLoss.setLastOrderTime(null);
			} else {
				customerLoss.setLastOrderTime(order.getOrderDate());	//设置最近的下单日期
			}
			customerLossDao.add(customerLoss);
			customer.setState(1);	//客户状态修改成1 流失状态
			customerDao.update(customer);
		}
		
	}
}
