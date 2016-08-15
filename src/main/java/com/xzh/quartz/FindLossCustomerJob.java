package com.xzh.quartz;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.xzh.service.CustomerService;

/**
 * 查找流失客户定时任务
 * @author JSiu
 *
 */
@Component
public class FindLossCustomerJob {
	
	@Resource
	private CustomerService customerService;
	
	@Scheduled(cron = "0 0 2 * * ?")
	public void work(){
		customerService.checkCustomerLoss();
	}

}
