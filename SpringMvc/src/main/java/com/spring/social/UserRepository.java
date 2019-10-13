package com.spring.social;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.spring.entity.Authorities;
import com.spring.entity.UserCustom;
import com.spring.persistence.SessionUtil;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionKey;
import org.springframework.social.connect.UserProfile;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

	public UserCustom findById(String id) {
		SessionFactory factory = SessionUtil.getSessionFactory();
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		String sql = "Select id,email,user_name, first_name,last_name,"//
				+ " password,role"//
				+ " from UserCustom u "//
				+ " where id = ? ";
		Query<UserCustom> query = session.createQuery(sql, UserCustom.class);
		query.setParameter(0, id);
		UserCustom result = (UserCustom) query.uniqueResult();
		System.out.println("        result" + result);
		tx.commit();
		session.close();

		return result;
	}

	public UserCustom findByEmail(String email) {
		SessionFactory factory = SessionUtil.getSessionFactory();
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		String sql = "Select id, email,user_name,first_name,last_name,"//
				+ " password,role"//
				+ " from UserCustom u "//
				+ " where email = ? ";
		Query<UserCustom> query = session.createQuery(sql, UserCustom.class);
		query.setParameter(0, email);
		UserCustom result = (UserCustom) query.uniqueResult();
		System.out.println("        result" + result);
		tx.commit();
		session.close();

		return result;
	}

	public UserCustom findByUserName(String userName) {

		SessionFactory factory = SessionUtil.getSessionFactory();
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();

		String sql = "Select id, email,username,first_name,last_name,"//
				+ " password,role"//
				+ " from UserCustom u "//
				+ " where username = ? ";
		Query<UserCustom> query = session.createQuery(sql, UserCustom.class);
		query.setParameter(0, userName);
		UserCustom result = (UserCustom) query.uniqueResult();
		System.out.println("        result" + result);
		tx.commit();
		session.close();

		return result;
	}

	public UserCustom registerNewUserAccount(UserCustom userCustom) {
		SessionFactory factory = SessionUtil.getSessionFactory();
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();

		session.save(userCustom);
		System.out.println("        registerNewUserAccount " + userCustom.getId());
		tx.commit();
		session.close();

		return userCustom;
	}

	private String findAvailableUserName(String userName_prefix) {
		UserCustom account = this.findByUserName(userName_prefix);
		if (account == null) {
			return userName_prefix;
		}
		int i = 0;
		while (true) {
			String userName = userName_prefix + "_" + i++;
			account = this.findByUserName(userName);
			if (account == null) {
				return userName;
			}
		}
	}

	
	
	
	 // Auto Create USER_ACCOUNTS.
	   public UserCustom createUserAccount(Connection<?> connection) {
	 
	       ConnectionKey key = connection.getKey();
	       // (facebook,12345), (google,123) ...
	 
	       System.out.println("key= (" + key.getProviderId() + "," + key.getProviderUserId() + ")");
	 
	       UserProfile userProfile = connection.fetchUserProfile();
	 
	       String email = userProfile.getEmail();
	       UserCustom account = this.findByEmail(email);
	       if (account != null) {
	           return account;
	       }
	 
	       // Create User_Account.
	       String sql = "Insert into User_Accounts "//
	               + " (id, email,user_name,first_name,last_name,password,role) "//
	               + " values (?,?,?,?,?,?,?) ";
	 
	       // Random string with 36 characters.
	       //String id = UUID.randomUUID().toString();
	        
	       String userName_prefix = userProfile.getFirstName().trim().toLowerCase()//
	                           +"_"+ userProfile.getLastName().trim().toLowerCase();
	        
	       String userName = this.findAvailableUserName(userName_prefix);
	 
	       
	       UserCustom user = new UserCustom();
	       user.setUsername(userName);
	       user.setEmail(email);
	       user.setFirstName(userProfile.getFirstName());
	       user.setLastName(userProfile.getLastName());
	       
	       Authorities authority = new Authorities();
	       authority.setUser(user);
	       authority.setUsername(user.getUsername());
	       authority.setAuthority("ROLE_USER");
	       
	       Set<Authorities> authorities = new HashSet<>();
	       authorities.add(authority);
	       
	       user.setAuthorities(authorities);
	       
	       SessionFactory factory = SessionUtil.getSessionFactory();
			Session session = factory.openSession();
			Transaction tx = session.beginTransaction();

			session.save(user);
			System.out.println("        registerNewUserAccount " + user.getId());
			tx.commit();
			session.close();
			// String id = user.getId();
	       return findById(String.valueOf(user.getId()));
	   }
}




















