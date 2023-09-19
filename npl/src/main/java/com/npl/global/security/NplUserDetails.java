package com.npl.global.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.npl.global.dto.settings.ProgramDto;
import com.npl.global.dto.settings.UserMenuDto;
import com.npl.global.entity.Company;
import com.npl.global.entity.Program;
import com.npl.global.entity.Role;
import com.npl.global.entity.User;

public class NplUserDetails implements UserDetails {
	private static final long serialVersionUID = 1L;
	
	private String password;

	private String username;
	
	private Boolean enabled;
	
	private Boolean accountNonExpired;

	private Boolean accountNonLocked;

	private boolean credentialsNonExpired;
	
	private Collection<? extends GrantedAuthority> authorities;
	
	private User user;
	
	private Company company;

	private List<ProgramDto> myMenuList;
	private List<ProgramDto> mainList;
	private List<UserMenuDto> userMenuList;

	private String deviceName;
	private String ipAddress;
	private String browserName;
	private String browserVersion;
	private String macAddress;
	
	public NplUserDetails(String username, String password, Boolean enabled, Collection<? extends GrantedAuthority> authorities) {
	    this.enabled=enabled;
	    this.username=username;
	    this.password=password;
	    this.accountNonExpired=true;
	    this.accountNonLocked=true;
	    this.credentialsNonExpired=true;
	    this.authorities=authorities;
	}
	
	public NplUserDetails(String password, String username, Boolean enabled, Boolean accountNonExpired, Boolean accountNonLocked, boolean credentialsNonExpired, Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
        this.password = password;
        this.username = username;
        this.enabled = enabled;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
    }
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}


	public List<ProgramDto> getMyMenuList() {
		return myMenuList;
	}

	public void setMyMenuList(List<ProgramDto> myMenuList) {
		this.myMenuList = myMenuList;
	}

	public List<ProgramDto> getMainList() {
		return mainList;
	}

	public void setMainList(List<ProgramDto> mainList) {
		this.mainList = mainList;
	}

	public List<Program> getChirdList() {
		return chirdList;
	}

	public void setChirdList(List<Program> chirdList) {
		this.chirdList = chirdList;
	}

	private List<Program> chirdList;
	
	public NplUserDetails(User user) {
		this.user = user;
	}

	public NplUserDetails(String password, User user) {
		this.password = password;
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<Role> roles = user.getRoles();
		
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		
		for(Role role : roles) {
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		}
		
		return authorities;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return user.isEnabled();
	}

	public void setUsername(String username) {
		this.user.setUsername(username);
	}
	
	public String getComname() {
		return this.user.getCompany().getComName();
	}
	
	public String getAbbrName() {
		return this.user.getAbbrName();
	}
	
	
	public boolean hasRole(String roleName) {
		return user.hasRole(roleName);
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getBrowserName() {
		return browserName;
	}

	public void setBrowserName(String browserName) {
		this.browserName = browserName;
	}

	public String getBrowserVersion() {
		return browserVersion;
	}

	public void setBrowserVersion(String browserVersion) {
		this.browserVersion = browserVersion;
	}

	public String getMacAddress() {
		return macAddress;
	}

	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Boolean getAccountNonExpired() {
		return accountNonExpired;
	}

	public void setAccountNonExpired(Boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	public Boolean getAccountNonLocked() {
		return accountNonLocked;
	}

	public void setAccountNonLocked(Boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	public List<UserMenuDto> getUserMenuList() {
		return userMenuList;
	}

	public void setUserMenuList(List<UserMenuDto> userMenuList) {
		this.userMenuList = userMenuList;
	}

}
