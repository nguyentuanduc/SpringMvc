package com.spring.social;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Service;

 
@Service
public class MySocialUserDetailsService implements SocialUserDetailsService {
    
	private static final Logger logger = Logger.getLogger(MySocialUserDetailsService.class);

   @Autowired
   private MyUserAccountDAO myUserAccountDAO;
 
   // Loads the UserDetails by using the userID of the user.
   // (This method Is used by Spring Security API).
   @Override
   public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException, DataAccessException {
	   logger.info("loadUserByUserId ");
       MyUserAccount account= myUserAccountDAO.findById(userId);
        
       MySocialUserDetails userDetails= new MySocialUserDetails(account);
        
       return userDetails;
   }
 
}
