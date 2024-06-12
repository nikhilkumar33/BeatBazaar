package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Users;
import com.example.demo.repositories.UsersRepository;

@Service
public class UsersServiceImplementation implements UsersService
{
	@Autowired
	UsersRepository urepo;

	@Override
	public void addUsers(Users user) {
		urepo.save(user);
	}

	@Override
	public boolean emailExists(String email) {
		if(urepo.findByEmail(email) == null)
		{
		return false;
		}
		else
		{
			return true;
		}
	}

	@Override
	public boolean validateUser(String email, String password) {
		Users user = urepo.findByEmail(email);
		if(user!=null)
		{
			String db_password = user.getPassword();
			if( db_password.equals(password))
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			return false;
		}
	}
	//fetching the data and get the role from the fetched data 
	@Override
	public String getRole(String email) {
		 return (urepo.findByEmail(email).getRole());
		
	}

	@Override
	public Users getUser(String email) {
		Users user = urepo.findByEmail(email);
		return user;
	}

	@Override
	public void updateUser(Users user) {
		urepo.save(user);
	}

	@Override
	public void deleteAccount(String email) {
		Users user = urepo.findByEmail(email);	
		int userid = user.getId();
		urepo.deleteById(userid);
	}

	
}
