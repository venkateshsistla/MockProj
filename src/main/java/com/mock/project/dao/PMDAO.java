package com.mock.project.dao;


import java.util.List;

import com.mock.project.model.Order;
import com.mock.project.model.Status;

public interface PMDAO<Order> extends GenericDAO<Order,Long> {




	
	public void updateStatus(Status status,List order_id);

	Order edit(Order object);

	Order ammend(Order object);

	List<Order> findAll();
	
	List<Order> findAllByName(String name, Long id);
	
	List<Order> findAllByID(Long id);
	
	List<Order> findAllStatusNew(Long pmId,Status status);
	
	public Long getTraderId(String traderName);
	public List<String> getTraderNameList();
}
