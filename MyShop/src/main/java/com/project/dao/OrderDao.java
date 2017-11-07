package com.project.dao;

import java.util.List;

import org.hibernate.Query;

import com.project.pojo.Order;
import com.project.pojo.OrderLine;
import com.project.pojo.Product;

public class OrderDao extends DAO{
	public void createOrder(Order order)
	{
		begin();
		getSession().save(order);
		commit();
	}
	
	public void createOrderLine(OrderLine orderLine)
	{
		begin();
		getSession().save(orderLine);
		commit();
	}
	
	public List<Order> getOrders()
	{
		begin();
        Query q = getSession().createQuery("from Order");
        List<Order> orders = q.list();
        commit();
        return orders;
	}
}
