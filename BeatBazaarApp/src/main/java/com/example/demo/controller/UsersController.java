package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entities.Users;
import com.example.demo.services.UsersService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class UsersController 
{
	@Autowired
	UsersService userv;
	
	@PostMapping("/register")
	public String addUsers(@ModelAttribute Users user)
	{
		boolean userstatus = userv.emailExists(user.getEmail());
		if(userstatus==false)
		{
			userv.addUsers(user);
			return "registersuccess";
		}
		else
		{
			return "registerfail";
		}
		
	}
	@PostMapping("/login")
	public String validateUser(@RequestParam String email,@RequestParam String password, HttpSession session,Model model)
	{
		
		//invoking validateUser() in service
				if(userv.validateUser(email, password)==true)
				{
					session.setAttribute("email", email);
					//checking weather the user is admin or customer
					if(userv.getRole(email).equals("admin"))
					{
						return "adminhome";
					}
					else
					{
						Users user = userv.getUser(email);
						model.addAttribute("user", user);
						return "customerhome";
					}

				}
				else
				{
					return "loginfail";
				}
	}
	@GetMapping("/exploresongs")
	public String exploreSongs(HttpSession session)
	{
		String email = (String) session.getAttribute("email");
		Users user = userv.getUser(email);
		boolean userstatus = user.isPremium();
		if(userstatus==true)
		{
			return "premiumuser";
		}
		else
		{
			return "makepayment";
		}
	}
	
	@GetMapping("/logout")
	public String logoutUser(HttpSession session,HttpServletRequest request,HttpServletResponse response)
	{
		session.invalidate();
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0
        response.setDateHeader("Expires", 0); // Proxies
        
        // Invalidate the session cookie
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
        }		return "login";
	}
	
	@PostMapping("/resetpassword")
	public String resertPassword(@RequestParam String email, @RequestParam String petname, @RequestParam String password)
	{
		Users user = userv.getUser(email);
		String db_petname = user.getPetname();
		if(db_petname.equals(petname))
		{
			user.setPassword(password);
			userv.updateUser(user);
			return "resetsuccess";
		}
		else
		{
			return "resetfail";
		}
	}
	
	
	@GetMapping("/deleteaccount")
	public String deleteAccount(HttpSession session)
	{
		String email = (String) session.getAttribute("email");
		userv.deleteAccount(email);
		return "login";
	}
}
