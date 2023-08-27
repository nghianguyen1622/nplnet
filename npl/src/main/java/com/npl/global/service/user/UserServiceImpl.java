package com.npl.global.service.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.npl.global.dao.user.UserDao;
import com.npl.global.dao.user.UserDaoExtend;
import com.npl.global.dto.ResultProcDto;
import com.npl.global.dto.user.UserDto;
import com.npl.global.entity.User;
import com.npl.global.model.user.RoleModel;
import com.npl.global.model.user.UserModel;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserDaoExtend userDaoExtend;


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


	@Override
	public List<UserModel> findAll(String comId) {
		// TODO Auto-generated method stub
		return userDao.findAll(comId);
	}


	@Override
	public ResultProcDto saveUser(UserDto userSave) throws Exception {
		// TODO Auto-generated method stub
		return userDaoExtend.callSaveUser(userSave);
	}


	@Override
	public ResultProcDto delUser(String userId) throws Exception {
		// TODO Auto-generated method stub
		return userDaoExtend.callDellUser(userId);
	}


	@Override
	public List<RoleModel> findRole() {
		// TODO Auto-generated method stub
		return userDao.findRole();
	}


	@Override
	public ResultProcDto saveUserImage(UserDto user) throws Exception {
		// TODO Auto-generated method stub
		return userDaoExtend.callUserImage(user);
	}


}