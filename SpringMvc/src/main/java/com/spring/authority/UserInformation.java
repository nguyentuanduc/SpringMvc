package com.spring.authority;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.spring.entity.UserCustom;
import com.spring.social.MyUserAccount;

public class UserInformation extends User{

	private static final long serialVersionUID = 1L;

    private MyUserAccount myUserAccount;
	
	public UserInformation(String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities, MyUserAccount _myUserAccount) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		
		this.myUserAccount = _myUserAccount;
	}

	public UserInformation(String username, String password, Collection<? extends GrantedAuthority> authorities, MyUserAccount _myUserAccount) {
		this(username, password, true, true, true, true, authorities, _myUserAccount);
	}

	public MyUserAccount getMyUserAccount() {
		return myUserAccount;
	}

	public void setMyUserAccount(MyUserAccount myUserAccount) {
		this.myUserAccount = myUserAccount;
	}

	

	

	

	
}
