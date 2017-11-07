package com.project.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import com.project.pojo.Category;
import com.project.pojo.Product;

public class ProductDao extends DAO{

	
	public List<Product> getProductsForHomePage()
	{
		begin();
        Query q = getSession().createQuery("from Product");
        List<Product> products = q.list();
        commit();
        return products;
	}
	
	public Product getProductDetails(long id)
	{
		begin();

		Criteria crit= getSession().createCriteria(Product.class);
		crit.add(Restrictions.eq("productId", Long.valueOf(id)));
        return (Product) crit.uniqueResult();
        
	}
	
	public void createProduct(Product product)
	{
		begin();
		getSession().save(product);
		commit();
	}
	
	public void deleteProduct(Product product)
	{
		begin();
		getSession().delete(product);
		commit();
	}
	
	
	public Product findProductById(long productId)
	{
		begin();
        Query q = getSession().createQuery("from Product where productId = :id");
        q.setLong("id", productId);
        
        Product product = (Product)q.uniqueResult();
        commit();
        return product;
	}
}
