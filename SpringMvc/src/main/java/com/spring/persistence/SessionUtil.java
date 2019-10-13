package com.spring.persistence;

import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;

import com.spring.entity.Publish;
import com.spring.entity.UserCustom;
import com.spring.entity.Authorities;
import com.spring.entity.Category;
import com.spring.entity.Contact;
import com.spring.entity.ContractTelDetail;
import com.spring.entity.Hobby;
import com.spring.entity.Product;

@Service("sessionUtil")
public class SessionUtil {

	//private final static SessionUtil instance = new SessionUtil();
	protected static SessionFactory factory = getSessionFactory();

	private static List<UserCustom>  allUser = getAllUser();

	public SessionUtil() {
		System.out.println(" ----------------- SessionUtil");
	}

	public static SessionFactory getSessionFactory() {
		System.out.println(" -----------------  getSessionFactory");
		if(factory == null) {
			StandardServiceRegistry standardRegistry = (StandardServiceRegistry) new StandardServiceRegistryBuilder().configure().build();
			Metadata metadata = new MetadataSources(standardRegistry).getMetadataBuilder().build();
			factory = metadata.getSessionFactoryBuilder().build();
		}
		return factory;
	}
	
	

	public void insertHQLContactNew() {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		String sql = "insert into contact_new(first_name, last_name, birth_date, version)"+
		" select  c.first_name, c.last_name, c.birth_date, c.version from contact_insert c where c.id = 2";

		Query<Contact> query = session.createQuery(sql, Contact.class);
		int result = query.executeUpdate();
		System.out.println("        result" + result);
		tx.commit();
		session.close();
	}

	public void insertHQLContact() {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		String sql = "insert into contact(first_name, last_name, birth_date, version)"+
		" select  c.first_name, c.last_name, c.birth_date, c.version from contact_insert c where c.id = 2";

		Query<Contact> query = session.createQuery(sql,Contact.class);
		int result = query.executeUpdate();
		System.out.println("        result" + result);
		tx.commit();
		session.close();
	}

	public void deleteHQLContact() {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		String sql = "delete from contact where id = 5";

		session.createQuery(sql);
		tx.commit();
		session.close();
	}

	public void updateHQLContact() {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		String sql = "update contact set first_name=:first_name where id=:id";

		Query<Contact> query = session.createQuery(sql, Contact.class);
		query.setParameter("first_name", "Hero1");
		query.setParameter("id", 4);
		int result = query.executeUpdate();
		System.out.println("        result" + result);
		tx.commit();
		session.close();
	}


	public void selectByIdHQLContact(int id) {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		String sql = "select c from contact c where c.id = :id";

		Query<Contact> query = session.createQuery(sql,  Contact.class);
		query.setParameter(0, id);
		Contact result = (Contact) query.uniqueResult();
		System.out.println("        result" + result);
		tx.commit();
		session.close();
		
	}


	/*public static Session getSession() {
		return getInstance().factory.openSession();
	}*/

	/*private static SessionUtil getInstance() {
		return instance;
	}*/

	public List<Product> listProducts() {
		Session session = factory.openSession();
		List<Product> list = session.createQuery("from products p", Product.class).list();

		if(list.size() > 0) {
			for(Product c : list) {
				System.out.println(c);
			}
		}
		session.close();
		return list;
	}

	public Product getProductById(int id) {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		String sql = "from products p where id = :id";

		Query<Product> query = session.createQuery(sql, Product.class);
		query.setParameter("id", id);
		Product product = (Product) query.uniqueResult();
		System.out.println("        result" + product);
		tx.commit();
		session.close();
		return product;
	}

	public void updateProduct(Product product) {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		System.out.println("---------updateProduct----------");
		System.out.println(product);
		System.out.println("---------updateProduct----------");
		session.update(product);
		tx.commit();
		session.close();
	}

	public Product addProduct(Product product) {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(product);
		
		System.out.println("-------------------");
		System.out.println(product.getId());
		System.out.println("-------------------");
		tx.commit();
		session.close();

		return product;
	}

	public void delete(Product product) {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		session.delete(product);
		tx.commit();
		session.close();
	}
	
	
	public List<Contact> listContact() {
		Session session = factory.openSession();
		List<Contact> list = session.createQuery("from contact c", Contact.class).list();

		if(list.size() > 0) {
			for(Contact c : list) {
				System.out.println(c);
			}
		}
		return list;
	}

	public void listContactDetail() {
		Session session = factory.openSession();
		List<Contact> list = session.createQuery("select distinct c from contact c left join fetch c.contractTelDetails", Contact.class).list();

		if(list.size() > 0) {
			for(Contact c : list) {
				System.out.println(c);
				if(c.getContractTelDetails() != null) {
					Set<ContractTelDetail> contractTelDetails = c.getContractTelDetails();
					for(ContractTelDetail detail : contractTelDetails) {
						System.out.println(detail);
					}
				}

			}
		}
	}

	public void getAllProductDetails() {
		// select distinct c from contact c left join fetch c.contractTelDetails
		System.out.println(125);
		Session session = factory.openSession();
		List<Product> list = session.createQuery("select distinct p from products p left join fetch p.publishs", Product.class).list();

		if(list.size() > 0) {
			for(Product c : list) {
				System.out.println(c);
				if(c.getCategory() != null) {
					Set<Publish> category = c.getPublishs();
					for(Publish detail : category) {
						System.out.println(detail);
					}
				}
				
			}
		}
	}
	
	
	
	public void listContactHobby() {
		System.out.println(125);
		Session session = factory.openSession();
		List<Contact> list = session.createQuery("select distinct c from contact c left join fetch c.contractTelDetails t left join fetch c.hobbys h", Contact.class).list();

		if(list.size() > 0) {
			for(Contact c : list) {
				System.out.println(c);
				if(c.getContractTelDetails() != null) {
					Set<ContractTelDetail> contractTelDetails = c.getContractTelDetails();
					for(ContractTelDetail detail : contractTelDetails) {
						System.out.println(detail);
					}
				}
				if(c.getHobbys() != null) {
					Set<Hobby> hobbys = c.getHobbys();
					for(Hobby hobby : hobbys) {
						System.out.println();
						System.out.println(hobby);
					}
				}
			}
		}
	}
	
	public void getAllProductDetails1() {
		System.out.println(125);
		Session session = factory.openSession();
		List<Product> list = session.createQuery("select distinct p from products p left join fetch p.publishs t left join fetch p.categorys h", Product.class).list();

		if(list.size() > 0) {
			for(Product c : list) {
				System.out.println(c);
				if(c.getPublishs() != null) {
					Set<Publish> contractTelDetails = c.getPublishs();
					for(Publish detail : contractTelDetails) {
						System.out.println(detail);
					}
				}
				if(c.getCategorys() != null) {
					Set<Category> categorys = c.getCategorys();
					for(Category category : categorys) {
						
						System.out.println(category);
					}
				}
				System.out.println();
			}
		}
	}
	
	public static List<UserCustom> getAllUser(){
		System.out.println("        getAllUser" );
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();

		List<UserCustom> list = session.createQuery("from UserCustom u", UserCustom.class).list();

		for (UserCustom a : list) {
			System.out.println("        UserCustom" + a);
		}
		tx.commit();
		session.close();
		System.out.println("        getAllUser end" );
		return list;
	}
	
	public UserCustom findUserByUsername(String name) {
		
		/*UserCustom user = null;
		List<UserCustom> list = allUser;

		for (UserCustom userCustom : list) {
			System.out.println("        UserCustom" + userCustom);
			if(userCustom.getUsername().equals(name)) {
				user = userCustom;
				System.out.println("        findUserByUsername end" + userCustom);
				break;
			}
		}

		return user;*/
		
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		
		System.out.println("        name" + name);
		String sql = "from UserCustom u where username =:username";
		Query<UserCustom> query = session.createQuery(sql, UserCustom.class);
		query.setParameter("username", name);
		System.out.println("        name 1" + name);
		
		UserCustom user = (UserCustom) query.uniqueResult();
		System.out.println("        name 2" + name);
		System.out.println("        user" + user);
		if (user != null && user.getAuthorities() != null) {
			Set<Authorities> authorities = user.getAuthorities();
			for (Authorities a : authorities) {
				System.out.println("        authority" + a);
			}
		}
		tx.commit();
		session.close();	
		return user;
	}
	
}
