package com.itheima.crm.service;

import com.itheima.crm.pojo.User;

   public interface UserService {
       void regist(User user);
       void login(String username,String password);
   } 
