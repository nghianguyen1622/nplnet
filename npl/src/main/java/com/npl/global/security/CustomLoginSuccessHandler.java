package com.npl.global.security;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import com.npl.global.entity.User;
import com.npl.global.service.user.UserService;


public class CustomLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
	
	@Autowired
	private UserService userService;
	
    public CustomLoginSuccessHandler(String defaultTargetUrl) {
        setDefaultTargetUrl(defaultTargetUrl);
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session != null) {
        	
        	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        	NplUserDetails userDetails = (NplUserDetails)auth.getPrincipal();
	    	try {
	    		userService.findUserName(userDetails.getUser().getUsername());
	    		
				User user = userDetails.getUser();
				user.setLogTime(new Date());
				userService.saveLogtime(user);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	 
	    	response.sendRedirect("/");
        } else {
            super.onAuthenticationSuccess(request, response, authentication);
        }
    }
}
