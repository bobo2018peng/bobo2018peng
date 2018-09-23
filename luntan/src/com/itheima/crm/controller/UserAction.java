package com.itheima.crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itheima.crm.pojo.User;
import com.itheima.crm.service.UserService;

   @Controller
   @RequestMapping("/user")
       public class UserAction {
    @Autowired
       private UserService userservice;
    @RequestMapping("regist")
       public String regist(User user,Model model) {
	    userservice.regist(user);
	    model.addAttribute("msg","注册成功");
	       return "success";
    }
    @RequestMapping("login")
    public String login(String username,String password,Model model) {
		userservice.login(username, password);
         model.addAttribute("msg", "登录成功");
		  return "success";
	}
    
    
}
