package com.spring.persistence;

import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.spring.entity.Contact;
import com.spring.entity.ContractTelDetail;
import com.spring.entity.Hobby;
import com.spring.entity.Product;

@Service("sessionUtil")
public class SessionUtil {

	//private final static SessionUtil instance = new SessionUtil();
	private  SessionFactory factory;


	public SessionUtil() {
		System.out.println("        SessionUtil");
		Configuration config = new Configuration();
		config.configure();
		ServiceRegistryBuilder builder = new ServiceRegistryBuilder();
		builder.applySettings(config.getProperties());
		ServiceRegistry service = builder.buildServiceRegistry();
		factory = config.buildSessionFactory(service);
	}


	public void insertHQLContactNew() {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		String sql = "insert into contact_new(first_name, last_name, birth_date, version)"+
		" select  c.first_name, c.last_name, c.birth_date, c.version from contact_insert c where c.id = 2";

		Query query = session.createQuery(sql);
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

		Query query = session.createQuery(sql);
		int result = query.executeUpdate();
		System.out.println("        result" + result);
		tx.commit();
		session.close();
	}

	public void deleteHQLContact() {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		String sql = "delete from contact where id = 5";

		Query query = session.createQuery(sql);
		int result = query.executeUpdate();
		System.out.println("        result" + result);
		tx.commit();
		session.close();
	}

	public void updateHQLContact() {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		String sql = "update contact set first_name=:first_name where id=:id";

		Query query = session.createQuery(sql);
		query.setString("first_name", "Hero1");
		query.setInteger("id", 4);
		int result = query.executeUpdate();
		System.out.println("        result" + result);
		tx.commit();
		session.close();
	}


	public void selectByIdHQLContact(int id) {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		String sql = "select c from contact c where c.id = :id";

		Query query = session.createQuery(sql);
		query.setInteger("id", id);
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
		List<Product> list = session.createQuery("from products p").list();

		if(list.size() > 0) {
			for(Product c : list) {
				System.out.println(c);
			}
		}
		return list;
	}

	public Product getProductById(int id) {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		String sql = "from products p where id = :id";

		Query query = session.createQuery(sql);
		query.setInteger("id", id);
		Product product = (Product) query.uniqueResult();
		System.out.println("        result" + product);
		tx.commit();
		session.close();
		return product;
	}

	public void updateProduct(Product product) {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(product);
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

	public List<Contact> listContact() {
		Session session = factory.openSession();
		List<Contact> list = session.createQuery("from contact c").list();

		if(list.size() > 0) {
			for(Contact c : list) {
				System.out.println(c);
			}
		}
		return list;
	}

	public void listContactDetail() {
		Session session = factory.openSession();
		List<Contact> list = session.createQuery("select distinct c from contact c left join fetch c.contractTelDetails").list();

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

	public void listContactHobby() {
		System.out.println(125);
		Session session = factory.openSession();
		List<Contact> list = session.createQuery("select distinct c from contact c left join fetch c.contractTelDetails t left join fetch c.hobbys h").list();

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
}
