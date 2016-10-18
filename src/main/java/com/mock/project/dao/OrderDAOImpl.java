package com.mock.project.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;


import com.mock.project.model.Block;
import com.mock.project.model.Order;




import com.mock.project.model.Status;

import com.mock.project.model.Order;




@Repository
public class OrderDAOImpl extends GenericDAOImplementation<Order, Long> implements OrderDAO{

	@PersistenceContext
	private EntityManager em;
	

	 @SuppressWarnings("unchecked")
	 @Override 
	 public List<Order> findAll() {
	//	 System.out.println("hey");
	List<Order> l=new ArrayList<Order>();

	
	Query query=em.createQuery("from Order where block_id is null");
	
	List<Order>l1=query.getResultList();
	
	
	for(Order i:l1){
		
		System.out.println(i);
	}
	

	return query.getResultList(); 
	 }

	@SuppressWarnings("unchecked")
	@Override
	public List<Order> findAll(int traderId) {


		Query query = em.createQuery("from Order where trader_id =:traderId");

		query.setParameter("traderId", traderId);

		return query.getResultList();
	}

	

	@Override
	public void updateStatus(long block_id, List order_id) {
		for (int i = 0; i < order_id.size(); i++) {

			long o_id = (Long) order_id.get(i);
			Query query = em.createQuery("update Order set block_id =:block_id" + " where order_id = :o_id");
			query.setParameter("block_id", block_id);
			query.setParameter("o_id", o_id);
		}

	}

	@Override
	public void updateBlock(Status changeStatus, List block_id) {
		for (int i = 0; i < block_id.size(); i++) {

			long b_id = (Long) block_id.get(i);
			Query query = em.createQuery("update order set status =:status" + "where block_id = :b_id");
			query.setParameter("status", changeStatus);
			query.setParameter("b_id", b_id);
		}
		



	}

	@Override

    public List<Block> recommend(List<Integer> selectedOrders) 
    {
           List<Order> checkedOrdersList=new ArrayList<Order>();
           List<Order> checkedOrders=new ArrayList<Order>();
           String symbol = null,side = null,status = null;
           int i,c=0;
           for(Integer order_id:selectedOrders)
           {
           
           TypedQuery<Order> query1=em.createQuery("from Order where order_id=:order_id",Order.class);
           query1.setParameter("order_id", order_id);
           checkedOrders=(List<Order>)query1.getResultList();
           Order order=checkedOrders.get(0);
           symbol=order.getSymbol();
           side=order.getSide();
           status=order.getStatus();
           checkedOrdersList.add(order);
           }
           System.out.println("here");
           for(Order c1: checkedOrders){
                  System.out.println(c1);
           }
           System.out.println("OrderDAOImpl"+side);
           TypedQuery<Block> query=em.createQuery("from Block where symbol=:symbol" + " and side=:side"+ " and status=:status",Block.class);
           query.setParameter("status", status);
           query.setParameter("symbol", symbol);
           query.setParameter("side", side);
           
           List<Block> blocks=query.getResultList();
           System.out.println("hey");
           
           Order check=checkedOrdersList.get(0);
           System.out.println("check" + check);
    
           for(i=1;i<checkedOrdersList.size();i++)
           {
                  if(check.getStatus()==(checkedOrdersList.get(i)).getStatus()&&check.getSide()==(checkedOrdersList.get(i)).getSide()&&check.getSymbol()==(checkedOrdersList.get(i)).getSymbol())
                  { c=0;
                  System.out.println("not break");}
                  
                  else
                  {c=1;
                  System.out.println("break");
                  break;}
           }
           if(c==1)      
           return null;
           else{
                  System.out.println("Else");
           return  blocks;}
    }




	public List<Order> findOrder(int orderId) {
		// TODO Auto-generated method stub
		//System.out.println("asdjdsak: "+orderId);
		List<Order> orders=new ArrayList<Order>();
			Query query=em.createQuery("from Order where order_Id=:orderId"+" and block_id is null");
		query.setParameter("orderId",orderId);
			//System.out.println(query);
		orders=query.getResultList();
		//System.out.println(orders.get(0));
		
		return orders;
		
	}

	@Override
	public void addBlock(Block block) {
		// TODO Auto-generated method stub
		em.persist(block);
	}

	@Override
	public List<Block> findAllBlocks(int traderId) {
		Query query = em.createQuery("from Block");
		//query.setParameter("traderId", traderId);

		return query.getResultList();
	}


}
