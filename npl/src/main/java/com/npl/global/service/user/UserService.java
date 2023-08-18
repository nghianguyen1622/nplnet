package com.npl.global.service.user;

import com.npl.global.entity.User;
import com.npl.global.model.user.UserModel;

public interface UserService {
	public UserModel findUserName(String username);
	public User findUserNameParam(String username);
	
	// pass
	public String encryptPass(String passwd);
	public void saveLogtime(User user);
}
