package com.revature.service;
import com.revature.model.User;


public interface UserDAO {

	public User LogIn(String uid);
	public long UpdateBalance(long balance,String pass);
	public boolean addUser(User user);
	public boolean checkAvailabilty(String uname);
	


}