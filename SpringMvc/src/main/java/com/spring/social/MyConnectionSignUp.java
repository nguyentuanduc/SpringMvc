/*package com.spring.social;


import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;

import com.spring.entity.UserCustom;
 
public class MyConnectionSignUp implements ConnectionSignUp {
 
    private MyUserAccountDAO myUserAccountDAO;
 
    public MyConnectionSignUp(MyUserAccountDAO myUserAccountDAO) {
        this.myUserAccountDAO = myUserAccountDAO;
    }
 
    // After login Social.
    // This method is called to create a USER_ACCOUNTS record
    // if it does not exists.
    @Override
    public String execute(Connection<?> connection) {
        
 
    	MyUserAccount account=    myUserAccountDAO.createUserAccount(connection);
        return account.getId();
    	
    }
 
}
*/