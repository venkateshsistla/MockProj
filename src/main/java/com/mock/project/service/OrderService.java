package com.mock.project.service;

import java.util.List;


import com.mock.project.model.Order;



import com.mock.project.model.*;



public interface OrderService {
	List<Order> displaylist();
	List<Order> display(int traderId);
	

	void updateStatusInOrder(long block_id,List order_id);

	public void addOrder(Order d);
	void updateStatus(String status, List block_id);


}