package com.npl.global.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import com.npl.global.model.user.UserModel;
import com.npl.global.service.user.UserService;

public class CustomLoginFailHandler implements AuthenticationFailureHandler {

	@Autowired
	private UserService userService;

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		UserModel smUser = userService.findUserName(request.getParameter("username"));
		if (smUser != null) {
			try {
				userService.findUserNameParam(smUser.getUsername());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		response.sendRedirect("/login?error=true&msg=" + exception.getMessage());
	}
}