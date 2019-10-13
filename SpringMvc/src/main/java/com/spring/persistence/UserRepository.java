/*package com.spring.persistence;

import java.util.Set;
import org.hibernate.Session;

import com.spring.entity.User;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;

import com.spring.entity.Authorities;

@Service("userRepository")
public class UserRepository extends SessionUtil {

	public User findUserByUsername(String name) {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		System.out.println("        name" + name);
		String sql = "select u from User u where u.username =:username";
		Query<User> query = session.createQuery(sql, User.class);
		query.setParameter("username", name);
		System.out.println("        name 1" + name);
		User user = (User) query.uniqueResult();
		System.out.println("        name 2" + name);
		System.out.println("        user" + user);
		if (user != null && user.getAuthorities() != null) {
			Set<Authorities> authorities = user.getAuthorities();
			for (Authorities a : authorities) {
				System.out.println("        authority" + a);
			}
		}
		
		session.close();

		return null;
	}
}
*/