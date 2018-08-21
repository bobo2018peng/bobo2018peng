package com.itheima.crm.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.itheima.crm.pojo.User;
@Repository
public interface Userdao {
/**
 * 注册
 */
	//添加用户
		public void addUser(User user);
		/**
		 * 登录
		 */
		public void findUserByNameAndPwd(@Param("name")String name, @Param("password")String password);
			
		}

