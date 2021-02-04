package com.spring.social;

import org.apache.log4j.Logger;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionKey;
import org.springframework.social.connect.UserProfile;
 
public class MyUserAccountForm {
	private static final Logger logger = Logger.getLogger(MyUserAccountForm.class);

    private String id;
    private String email;
    private String userName;
    private String firstName;
    private String lastName;
    private String password;
    private String role;
    private String signInProvider;
    private String providerUserId;
 
    public MyUserAccountForm() {
 
    }
 
    public MyUserAccountForm(Connection<?> connection) {
    	logger.info("MyUserAccountForm 1");
        UserProfile socialUserProfile = connection.fetchUserProfile();
        logger.info("MyUserAccountForm 2");
        logger.info(socialUserProfile.toString());
        
        this.id = null;
        /*
        if(socialUserProfile.getEmail() != null) {
        	this.email = socialUserProfile.getEmail();
        }
        if(socialUserProfile.getUsername() != null) {
        	this.userName = socialUserProfile.getUsername();
        }
       
        if(socialUserProfile.getFirstName() != null) {
        	this.firstName = socialUserProfile.getFirstName();
        }
        
        if(socialUserProfile.getLastName() != null) {
        	this.lastName = socialUserProfile.getLastName();
        }*/
 
        if(connection.getKey() != null) {
        	 ConnectionKey key = connection.getKey();
             // google, facebook, twitter
             if( key.getProviderId() != null) {
            	 this.signInProvider = key.getProviderId();
             }
             // ID of User on google, facebook, twitter.
             // ID của User trên google, facebook, twitter.
             if( key.getProviderUserId() != null) {
            	 this.providerUserId = key.getProviderUserId();
             }
        }
       
    }
 
    public String getId() {
        return id;
    }
 
    public void setId(String id) {
        this.id = id;
    }
 
    public String getEmail() {
        return email;
    }
 
    public void setEmail(String email) {
        this.email = email;
    }
 
    public String getUserName() {
        return userName;
    }
 
    public void setUserName(String userName) {
        this.userName = userName;
    }
 
    public String getFirstName() {
        return firstName;
    }
 
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
 
    public String getLastName() {
        return lastName;
    }
 
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
 
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
 
    public String getRole() {
        return role;
    }
 
    public void setRole(String role) {
        this.role = role;
    }
 
    public String getSignInProvider() {
        return signInProvider;
    }
 
    public void setSignInProvider(String signInProvider) {
        this.signInProvider = signInProvider;
    }
 
    public String getProviderUserId() {
        return providerUserId;
    }
 
    public void setProviderUserId(String providerUserId) {
        this.providerUserId = providerUserId;
    }
 
}
