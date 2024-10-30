package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.entities.Users;
import com.example.demo.services.UsersService;

import jakarta.servlet.http.HttpSession;

@Controller
public class NavController 
{
	@Autowired
	UsersService userv;
	@GetMapping("/map-register")
	public String registerMapping()
	{
		return "register";
	}
	@GetMapping("/map-login")
	public String loginMapping()
	{
		return "login";
	}
	@GetMapping("/map-songs")
	public String songMapping()
	{
		return "addsongs";
	}
	@GetMapping("/map-password")
	public String resetPassword()
	{
		return "resetpassword";
	}
	@GetMapping("/map-adminhome")
	public String adminHomeMapping()
	{
		return "adminhome";
	}
	@GetMapping("/map-custhome")
	public String customerHomeMapping(HttpSession session,Model model)
	{
		String email= (String)session.getAttribute("email");
		Users user=userv.getUser(email);
		model.addAttribute("user", user);
		return "customerhome";
	}
	
}
