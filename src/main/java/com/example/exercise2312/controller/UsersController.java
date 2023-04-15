package com.example.exercise2312.controller;

import com.example.exercise2312.model.User;
import com.example.exercise2312.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
public class UsersController {

	private final UserService userService;

	@Autowired
	public UsersController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping(value = "/")
	public String showUserList(Model model) {
		model.addAttribute("UserList", userService.findAllUsers());
		return "list";
	}

	@GetMapping(value = "/new")
	public String showNewUser(ModelMap model) {
		User user = new User();
		model.addAttribute("addnew", true);
		model.addAttribute("user", user);
		return "info";
	}

	@PostMapping(value = "/new")
	public String addNewUser(@ModelAttribute("user") User user) {
		userService.saveUser(user);
		return "redirect:/";
	}

	@GetMapping("/{id}")
	public String showEditUser(@PathVariable("id") int id, ModelMap model) {
		User user = userService.findUserById(id);
		model.addAttribute("addnew", false);
		model.addAttribute("user", user);
		return "info";
	}

	@PutMapping("/{id}")
	public String editUser(@ModelAttribute("user") User user) {
		userService.updateUser(user);
		return "redirect:/";
	}

	@DeleteMapping  ("/{id}")
	public String deleteUser(@PathVariable("id") int id) {
		userService.removeUserById(id);
		return "redirect:/";
	}
}