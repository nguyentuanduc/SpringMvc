package com.spring.authority;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.spring.entity.Authorities;
import com.spring.social.MyUserAccount;
import com.spring.social.MyUserAccountDAO;

@Service("userDetailsServiceImp")
public class UserDetailsServiceImp implements UserDetailsService {

	
	public UserDetailsServiceImp() {
		System.out.println(" ------------------------------ UserDetailsServiceImp contructor");
	}
	
	/*@Autowired
	private SessionUtil SessionUtil;*/
	
	@Autowired
	private MyUserAccountDAO myUserAccountDAO;

	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println(" ------------------------------  loadUserByUsername");
		UserInformation user = myUserAccountDAO.findUserByUsername(username);
		System.out.println(" ------------------------------  loadUserByUsername ---");
		UserBuilder builder = null;
		
		if(user !=  null) {
			/*builder = org.springframework.security.core.userdetails.User.withUsername(username);
			builder = UserInformation.withUsername(username);
			builder.disabled(false);
			builder.password(user.getPassword());
			String[] authorities = user.getAuthorities().stream().map(a -> a.getAuthority()).toArray(String[]::new);
			
			builder.authorities(authorities);*/
			
			// UserInformation userInfor = new UserInformation(user.getUsername(), user.getPassword(), authorities, null, user.getEmail());
		}
		else {
			throw new UsernameNotFoundException("user not found");
		}
		//UserInformation userInformation = (UserInformation) builder.build();
		//userInformation.setEmail(user.getEmail());
		//return builder.build();
		System.out.println(user.getPassword());
		/*List<GrantedAuthority> _authorities = new ArrayList<GrantedAuthority>();
		GrantedAuthority grant = new SimpleGrantedAuthority("ROLE_USER");
		_authorities.add(grant);
	    //List<GrantedAuthority> _authorities = buildUserAuthority(user.getRole());
		System.out.println("called buildUserForAuthentication(Users user, List<GrantedAuthority> authorities) method....");
		return new UserInformation(user.getUserName(), user.getPassword(), true, true, true, true, _authorities, user);*/
		
		return user;
	}

	
	private List<GrantedAuthority> buildUserAuthority(Set<Authorities> userRoles) {
	    Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>(); 
	    for(Authorities authority  : userRoles){
	        System.out.println("called buildUserAuthority(Set<UserRole> userRoles) method.....");
	        setAuths.add(new SimpleGrantedAuthority(authority.getAuthority()));
	    }
	    
	    List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>(setAuths);
	    return grantedAuthorities;
    }

	
	
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	