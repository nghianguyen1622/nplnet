package com.npl.global.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "users")
public class User extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(length = 128)
	private String email;

	@Column(name = "ADDR", length = 128)
	private String adress;

	@Column(length = 64, nullable = false)
	private String password;
	
	@Column(name = "CHECK_PASS",length = 64, nullable = false)
	private String checkPass;

	@Column(name = "USER_NAME", length = 45, nullable = false)
	private String username;

	@Column(name = "PHONE")
	private String phone;

	@Column(name = "IMG", length = 64)
	private String img;

	@Column(name = "IDENTITY_CARD")
	private String identity_card;

	@Column(name = "BIRTH_DAY")
	private String birthDay;
	
	@Column(name = "LOGTIME")
	private Date logTime;
	
	private boolean enabled;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<UserMenu> userMenus;

	public User() {
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCheckPass() {
		return checkPass;
	}

	public void setCheckPass(String checkPass) {
		this.checkPass = checkPass;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getIdentity_card() {
		return identity_card;
	}

	public void setIdentity_card(String identity_card) {
		this.identity_card = identity_card;
	}

	public String getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}

	public Date getLogTime() {
		return logTime;
	}

	public void setLogTime(Date logTime) {
		this.logTime = logTime;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	public void addRole(Role role) {
		this.roles.add(role);
	}

	public List<UserMenu> getUserMenus() {
		return userMenus;
	}

	public void setUserMenus(List<UserMenu> userMenus) {
		this.userMenus = userMenus;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	public boolean hasRole(String roleName) {
		Iterator<Role> iterator = roles.iterator();

		while (iterator.hasNext()) {
			Role role = iterator.next();
			if (role.getName().equals(roleName)) {
				return true;
			}
		}
		return false;
	}
	
	@Transient
    public String getAbbrName() {
        String[] words = username.split("\\s+");
        StringBuilder abbreviation = new StringBuilder();

        for (String word : words) {
            if (!word.isEmpty()) {
                abbreviation.append(word.charAt(0));
            }
        }

        return abbreviation.toString().toUpperCase();
    }
}
