package com.npl.global.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.npl.global.dao.user.UserDao;
import com.npl.global.entity.User;
import com.npl.global.model.user.UserModel;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;


	@Override
	public UserModel findUserName(String username) {
		return this.userDao.findUserName(username);
	}


	@Override
	public User findUserNameParam(String username) {
		return this.userDao.getUserByUserName(username);
	}


	@Override
	public String encryptPass(String passwd) {
		// TODO Auto-generated method stub
		return this.userDao.encryptPass(passwd);
	}


	@Override
	public void saveLogtime(User user) {
		userDao.save(user);
		
	}


}