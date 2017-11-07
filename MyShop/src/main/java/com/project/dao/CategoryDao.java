package com.project.dao;

import java.util.List;

import org.hibernate.Query;

import com.project.pojo.Category;
import com.project.pojo.OrderLine;
import com.project.pojo.User;;

public class CategoryDao extends DAO {
	
	public List<Category> getCategoriess()
	{
		begin();
        Query q = getSession().createQuery("from Category");
        q.setMaxResults(12);
        List<Category> category = q.list();
        commit();
        return category;
	}
	
	
	
	public void createCategory(Category category)
	{
		begin();
		getSession().save(category);
		commit();
	}
	
	public void deleteCategory(Category category)
	{
		begin();
		getSession().delete(category);
		commit();
	}
	
	public Category findCategoryById(long categoryid)
	{
		begin();
        Query q = getSession().createQuery("from Category where categoryId = :categoryid");
        q.setLong("categoryid", categoryid);
        
        Category category = (Category)q.uniqueResult();
        commit();
        return category;
	}

}
