package com.itheima.crm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
     @Controller
   public class test {
	 @RequestMapping("/list")
	 @ResponseBody
     public String list() {
	   return "nihao";
   }
 }
