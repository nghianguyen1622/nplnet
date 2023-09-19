package com.npl.global.common;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.npl.global.dto.settings.ProgramDto;
import com.npl.global.security.NplUserDetails;

@Service
@Transactional
public class CheckUtil {

	public String canAccess(String urlHelp, String htmlNo) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		NplUserDetails userDetails = (NplUserDetails) auth.getPrincipal();

		List<ProgramDto> listMenus = userDetails.getMyMenuList();

		if (userDetails.hasRole("Admin")) {
			return htmlNo;
		}

		boolean canAccess = listMenus.stream().anyMatch(programDto -> programDto.getHelpUrl().equals(urlHelp)
				&& programDto.getUseYn().equals(YesNoStatus.YES.getValue()));

		return canAccess ? htmlNo : "error/403";
	}

	public String getComId() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		NplUserDetails userDetails = (NplUserDetails) auth.getPrincipal();
		return userDetails.getUser().getCompany().getComId();
	}
	
	public boolean isAdmin() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		NplUserDetails userDetails = (NplUserDetails) auth.getPrincipal();
		return userDetails.hasRole("Admin");
	}

}
