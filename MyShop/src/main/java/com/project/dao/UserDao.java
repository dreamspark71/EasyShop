package com.project.dao;

import java.util.List;

import org.hibernate.Query;

import com.project.pojo.Administrator;
import com.project.pojo.Product;
import com.project.pojo.User;

public class UserDao extends DAO{

	
	public User findByUserNamePassword(String username, String password)
	{
		begin();
        Query q = getSession().createQuery("from User where userName = :username and passWord = :password");
        q.setString("username", username);
        q.setString("password", password);
        User user = (User)q.uniqueResult();
        commit();
        return user;
	}
	
	
	public Administrator findByAdminUserNamePassword(String username, String password)
	{
		begin();
        Query q = getSession().createQuery("from Administrator where userName = :username and passWord = :password");
        q.setString("username", username);
        q.setString("password", password);
        Administrator user = (Administrator)q.uniqueResult();
        commit();
        return user;
	}
	
	public User findUserById(long userId)
	{
		begin();
        Query q = getSession().createQuery("from User where id = :id");
        q.setLong("id", userId);
        
        User user = (User)q.uniqueResult();
        commit();
        return user;
	}
	
	public List<User> getUsers()
	{
		begin();
        Query q = getSession().createQuery("from User");
        List<User> user = q.list();
        commit();
        return user;
	}
	
	public void createUser(User user)
	{
		begin();
		getSession().save(user);
		commit();
	}
	
	public void updateUser(User user)
	{
		begin();
		getSession().update(user);
		commit();
	}
	
	public void deleteUser(User user)
	{
		begin();
		getSession().delete(user);
		commit();
	}
}
