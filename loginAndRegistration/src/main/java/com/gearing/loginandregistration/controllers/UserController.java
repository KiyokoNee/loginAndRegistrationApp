package com.gearing.loginandregistration.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.gearing.loginandregistration.models.LoginUser;
import com.gearing.loginandregistration.models.User;
import com.gearing.loginandregistration.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class UserController {
	@Autowired
	private UserService userServ;
	
	@GetMapping("/")
	public String index(Model model, @ModelAttribute User newUser, @ModelAttribute LoginUser newLogin, HttpSession session) {
		if(session.getAttribute("userId") != null)
			return "redirect:/welcome";
		
		model.addAttribute("newUser", new User());
		model.addAttribute("newLogin", new LoginUser());
		return "index.jsp";
	}
	
	@PostMapping("/register")
	public String register(Model model, @Valid @ModelAttribute("newUser") User newUser,
			BindingResult result, HttpSession session) {
		// Attempt to register the new user
		userServ.register(newUser, result);
		
		// Due to pass by reference, we also get the errors from the register method
		if(result.hasErrors()) {
			model.addAttribute("newLogin", new LoginUser());
			return "index.jsp";
		}
		
		// No errors, so store session id to log them in
		session.setAttribute("userId", newUser.getId());
		
		// Goes to welcome page if all works well
		return "redirect:/welcome";
	}
	
	@PostMapping("/login")
	public String login(Model model, @Valid @ModelAttribute("newLogin") LoginUser newLogin,
			BindingResult result, HttpSession session) {
		// Attempt to login with the given information
		User loggedUser = userServ.login(newLogin, result);
		
		// Due to pass by reference, we also get the errors from the login method
		if(result.hasErrors()) {
			model.addAttribute("newUser", new User());
			return "index.jsp";
		}
		
		// No errors, so store session id to log them in
		session.setAttribute("userId", loggedUser.getId());
		
		// Go to welcome page if all goes well
		return "redirect:/welcome";
	}
	
	@GetMapping("/welcome")
	public String dashboard(HttpSession session, Model model) {
		Long userId = (Long) session.getAttribute("userId");
		if(userId == null) {
			return "redirect:/";
		}
		
		User user = userServ.findById(userId);
		
		model.addAttribute("username", user.getUsername());
		
		return "welcome.jsp";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		
		return "redirect:/";
	}
}
