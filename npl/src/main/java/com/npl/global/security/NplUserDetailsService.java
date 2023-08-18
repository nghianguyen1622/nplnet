package com.npl.global.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.npl.global.config.InfoPcClientUtil;
import com.npl.global.entity.User;
import com.npl.global.service.user.UserService;

public class NplUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserService service;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = service.findUserNameParam(username);
		if(user != null) {
			
			NplUserDetails userDetails = new NplUserDetails(user);
			userDetails.setDeviceName(InfoPcClientUtil.getDevice());
	        userDetails.setIpAddress(InfoPcClientUtil.getIpAddress());
	        userDetails.setBrowserName(InfoPcClientUtil.getBrowserName());
	        userDetails.setBrowserVersion(InfoPcClientUtil.getBrowserVersion());
	        userDetails.setMacAddress("");
	        
			return userDetails;
		}
			throw new UsernameNotFoundException("Không tìm thấy người dùng");
		
		
	}

}
