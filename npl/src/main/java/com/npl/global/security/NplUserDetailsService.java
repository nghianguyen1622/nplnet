package com.npl.global.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.npl.global.config.InfoPcClientUtil;
import com.npl.global.dto.settings.ProgramDto;
import com.npl.global.dto.settings.UserMenuDto;
import com.npl.global.entity.User;
import com.npl.global.service.settings.ProgramService;
import com.npl.global.service.settings.UserMenuService;
import com.npl.global.service.user.UserService;

public class NplUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProgramService prgService;
	
	@Autowired
	private UserMenuService uMnService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userService.findUserNameParam(username);
		if(user != null) {
			
			List<ProgramDto> listUPrg = prgService.findMenuByUser(user.getUserId());
			List<ProgramDto> listAllPrg = prgService.allPrograms();
			
			NplUserDetails userDetails = new NplUserDetails(user);
			userDetails.setDeviceName(InfoPcClientUtil.getDevice());
	        userDetails.setIpAddress(InfoPcClientUtil.getIpAddress());
	        userDetails.setBrowserName(InfoPcClientUtil.getBrowserName());
	        userDetails.setBrowserVersion(InfoPcClientUtil.getBrowserVersion());
	        userDetails.setMacAddress("");
	        
	        userDetails.setMyMenuList(listUPrg);
	        userDetails.setMainList(listAllPrg);
	        
	        List<UserMenuDto> listUserMenu = uMnService.listMenu(user.getUserId());
	        userDetails.setUserMenuList(listUserMenu);
	        
			return userDetails;
		}
			throw new UsernameNotFoundException("Không tìm thấy người dùng");
		
		
	}

}
