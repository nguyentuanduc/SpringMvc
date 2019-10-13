package com.spring.config;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurer;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.social.google.connect.GoogleConnectionFactory;
import org.springframework.social.security.AuthenticationNameUserIdSource;

import com.spring.social.MyConnectionSignUp;
import com.spring.social.MyUserAccountDAO;

@Configuration
@EnableSocial
// Load to Environment.
@PropertySource("classpath:social-cfg.properties")
public class SocialConfig implements SocialConfigurer {

	private static final Logger logger = Logger.getLogger(SocialConfig.class);

	private boolean autoSignUp = false;

	@Autowired
	private DataSource dataSource;

	@Autowired
	private MyUserAccountDAO myUserAccountDAO;

	// @env: Read properties file config by @PropertySource.
	@Override
	public void addConnectionFactories(ConnectionFactoryConfigurer cfConfig, Environment env) {
		System.out.println("addConnectionFactories");
		logger.info("addConnectionFactories ");
		// Google
		GoogleConnectionFactory gfactory = new GoogleConnectionFactory(//
				env.getProperty("google.client.id"), //
				env.getProperty("google.client.secret"));

		gfactory.setScope(env.getProperty("google.scope"));

		cfConfig.addConnectionFactory(gfactory);
	}

	// The UserIdSource determines the userID of the user.
	@Override
	public UserIdSource getUserIdSource() {
		System.out.println("getUserIdSource");
		logger.info("getUserIdSource ");
		return new AuthenticationNameUserIdSource();
	}

	// Read and insert to USERCONNECTION.
	@Override
	public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
		System.out.println("getUsersConnectionRepository");
		logger.info("getUsersConnectionRepository ");
		// org.springframework.social.security.SocialAuthenticationServiceRegistry
		JdbcUsersConnectionRepository usersConnectionRepository = new JdbcUsersConnectionRepository(dataSource,
				connectionFactoryLocator,

				Encryptors.noOpText());

		if (autoSignUp) {
			// Config to:
			// After login to social.
			// Automatically create corresponding USER_ACCOUNT if not already.
			ConnectionSignUp connectionSignUp = new MyConnectionSignUp(myUserAccountDAO);
			usersConnectionRepository.setConnectionSignUp(connectionSignUp);
		} else {
			// Config to:
			// After login to social.
			// If USER_ACCOUNTS does not exists
			// Redirect to register page.
			usersConnectionRepository.setConnectionSignUp(null);
		}
		return usersConnectionRepository;
	}

	// This bean manages the connection flow between the account provider and
	// the example application.
	@Bean
	public ConnectController connectController(ConnectionFactoryLocator connectionFactoryLocator, //
			ConnectionRepository connectionRepository) {
		System.out.println("connectController");
		logger.info("connectController ");
		return new ConnectController(connectionFactoryLocator, connectionRepository);
	}

}
