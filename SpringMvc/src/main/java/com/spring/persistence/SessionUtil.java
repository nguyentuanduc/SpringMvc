package com.spring.persistence;

import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.spring.entity.Category;
import com.spring.entity.Product;
import com.spring.entity.Publish;
import com.spring.entity.UserCustom;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;

@Service("sessionUtil")
public class SessionUtil {

	private static final Logger logger = Logger.getLogger(SessionUtil.class);
	
	protected static SessionFactory factory = getSessionFactory();


	public SessionUtil() {
		
	}

	public static SessionFactory getSessionFactory() {
		logger.info("getSessionFactory begin ");
		if(factory == null) {
			StandardServiceRegistry standardRegistry = (StandardServiceRegistry) new StandardServiceRegistryBuilder().configure().build();
			Metadata metadata = new MetadataSources(standardRegistry).getMetadataBuilder().build();
			factory = metadata.getSessionFactoryBuilder().build();
		}
		logger.info("getSessionFactory end ");
		return factory;
	}
	
	
	public List<Category> listCategory() {
		logger.info("listCategory begin ");
		Session session = factory.openSession();
		List<Category> list = session.createQuery("from category c", Category.class).list();
		logger.info("list Category  " + list.size());
		session.close();
		logger.info("listCategory end ");
		return list;
	}
	
	
	
	public List<Product> listProducts() {
		logger.info("listProducts begin ");
		Session session = factory.openSession();
		List<Product> list = session.createQuery(" from products p where p.disable = "+ false , Product.class).list();
		logger.info("list Products  " + list.size());
		session.close();
		logger.info("listProducts end ");
		return list;
	}

	public List<Product> listProductsDetail() {
		logger.info("listProductsDetail begin ");
		Session session = factory.openSession();
		List<Product> list = session.createQuery("select distinct p from products p left join fetch p.publishs t left join fetch p.categorys c", Product.class).list();
		logger.info("list Products  " + list.size());
		session.close();
		logger.info("listProductsDetail end ");
		return list;
	}


	public Product getProductById(int id) {
		logger.info("getProductById begin ");
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		String sql = "select distinct p from products p left join fetch p.publishs t left join fetch p.categorys h where p.id = :id";
		Query<Product> query = session.createQuery(sql, Product.class);
		query.setParameter("id", id);
		Product product = (Product) query.uniqueResult();
		tx.commit();
		session.close();
		logger.info("getProductById end ");
		return product;
	}

	public void removePublish(Product product) {
		logger.info("removePublish begin ");
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		
		Set<Publish> publishs = new HashSet<Publish>(product.getPublishs());
		for(Publish  element : publishs) {
			product.removePublish(element);
			element.setProduct(null);
		}
		session.merge(product);
		tx.commit();
		logger.info("removePublish end ");
		session.close();
	}
	
	
	public void updateProduct(Product product) {
		logger.info("updateProduct begin ");
		removePublish(product);
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		Set<String> listPublish = product.getListPublish();
		

		if(listPublish != null) {
			for(String  element : listPublish) {
				Publish publish = new Publish();
				publish.setName(element);
				// set one to many
				product.addPublish(publish);
			}
		}

		Set<Category> categorys = new HashSet<Category>();
		Set<String> listCategory = product.getListCategory();
		if(listCategory != null) {
			for(String  element : listCategory) {
				Category category = new Category();
				category.setCategory_id(element);
				categorys.add(category);
			}
			// set many to many
			product.setCategorys(categorys);
		}
		session.update(product);
		tx.commit();
		logger.info("updateProduct end ");
		session.close();
	}

	public Product addProduct(Product product) {
		logger.info("addProduct begin ");
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		
		Set<String> listPublish = product.getListPublish();
		
		if(listPublish != null){
			for(String  element : listPublish) {
				Publish publish = new Publish();
				publish.setName(element);
				// set one to many
				product.addPublish(publish);
			}
		}
		
		Set<Category> categorys = new HashSet<Category>();
		Set<String> listCategory = product.getListCategory();
		
		if(listCategory != null){
			for(String  element : listCategory) {
				Category category = new Category();
				category.setCategory_id(element);
				categorys.add(category);
			}
		}
		
		// set many to many
		product.setCategorys(categorys);
		session.save(product);
		tx.commit();
		session.close();
		logger.info("addProduct end ");
		return product;
	}

	public void delete(Product product) {
		logger.info("delete begin ");
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		product.setDisable(true);
		session.update(product);
		tx.commit();
		session.close();
		logger.info("delete end ");
	}
	
	public UserCustom findUserByUsername(String name) {
		logger.info("findUserByUsername begin ");
		Session session = factory.openSession();
		logger.info("name  " + name);
		String sql = "from UserCustom u where username =:username";
		Query<UserCustom> query = session.createQuery(sql, UserCustom.class);
		query.setParameter("username", name);
		
		UserCustom user = (UserCustom) query.uniqueResult();
		session.close();
		logger.info("findUserByUsername end ");
		return user;
	}
	
	
	public List<Product> getListProductsByTime(int day) {
		logger.info("getListProductsByTime begin ");
		Session session = factory.openSession();
		long now = System.currentTimeMillis();
		logger.info("now " + now);
		long startMillis = now - (day * 24L * 60L * 60L * 1000L);
		Date start = new Date(startMillis);

		Query<Product> query = session.createQuery("from products p where created > :start", Product.class);
		query.setParameter("start", start);
		List<Product> list = query.list();
		
		session.close();
		logger.info("getListProductsByTime end ");
		return list;
	}
	
}
