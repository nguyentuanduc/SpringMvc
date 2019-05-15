package com.spring.persistence;

import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.spring.entity.Contact;
import com.spring.entity.ContractTelDetail;

public class SessionUtil {

	private final static SessionUtil instance = new SessionUtil();
	private final SessionFactory factory; 
	
	
	public SessionUtil() {
		Configuration config = new Configuration();
		config.configure();
		ServiceRegistryBuilder builder = new ServiceRegistryBuilder();
		builder.applySettings(config.getProperties());
		ServiceRegistry service = builder.buildServiceRegistry();
		factory = config.buildSessionFactory(service);
	}
	
	public static Session getSession() {
		return getInstance().factory.openSession();
	}
	
	private static SessionUtil getInstance() {
		return instance;
	}
	
	public void listContact() {
		Session session = factory.openSession();
		List<Contact> list = session.createQuery("from contact c").list();
		
		if(list.size() > 0) {
			for(Contact c : list) {
				System.out.println(c);
			}
		}
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
}
























