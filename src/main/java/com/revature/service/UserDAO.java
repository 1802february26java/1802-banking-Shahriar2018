package com.revature.service;
import com.revature.model.User;


public interface UserDAO {

	public User LogIn(String uid);
	public boolean UpdateBalance(long balance,String pass);
	public boolean addUser(User user);


}