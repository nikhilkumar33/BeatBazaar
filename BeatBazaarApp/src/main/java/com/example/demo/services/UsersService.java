package com.example.demo.services;

import com.example.demo.entities.Users;

public interface UsersService 
{
	public void addUsers(Users user);
	
	public boolean emailExists(String email);

	public boolean validateUser(String email, String password);
	
	public String getRole(String email);

	public Users getUser(String email);

	public void updateUser(Users user);

	public void deleteAccount(String email);

	public String getUsername(String email);

}
