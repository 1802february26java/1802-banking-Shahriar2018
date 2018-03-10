package com.revature.repository;

import java.util.List;

import com.revature.model.User;

public interface UserDAO {

	public User LogIn(String uid);
	public boolean UpdateBalance(long balance,String pass);


}