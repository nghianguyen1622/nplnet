package com.npl.global.service.user;

import java.util.List;

import com.npl.global.dto.ResultProcDto;
import com.npl.global.dto.user.UserDto;
import com.npl.global.entity.User;
import com.npl.global.model.user.UserModel;

public interface UserService {
	public UserModel findUserName(String username);
	public User findUserNameParam(String username);
	
	public List<UserModel> findAll(String comId);
	public ResultProcDto saveUser(UserDto userSave) throws Exception;
	public ResultProcDto delUser(String userId) throws Exception;
	
	// pass
	public String encryptPass(String passwd);
	public void saveLogtime(User user);
}
