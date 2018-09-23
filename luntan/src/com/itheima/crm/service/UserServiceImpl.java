package com.itheima.crm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itheima.crm.mapper.Userdao;
import com.itheima.crm.pojo.User;


@Service
public class UserServiceImpl implements UserService {
@Autowired
private Userdao userDao;
	@Override
	public void regist(User user) {
		userDao.addUser(user);
	   }
	@Override
	public void login(String username, String password) {
	userDao.findUserByNameAndPwd(username, password);
	
		
	}
	}


