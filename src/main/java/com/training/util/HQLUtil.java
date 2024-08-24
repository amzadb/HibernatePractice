package com.training.util;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.training.BookPOJO;
import com.training.PublisherPOJO;

public class HQLUtil {
	private static SessionFactory sessionFactory = null;
	static {
		try {
			Properties settings = new Properties();			
			settings.put("hibernate.connection.driver_class", "org.postgresql.Driver");
			settings.put("hibernate.connection.url", "jdbc:postgresql://localhost/MyDB");
			settings.put("hibernate.connection.username", "postgres");
			settings.put("hibernate.connection.password", "postgres");
			settings.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
			settings.put("hibernate.show_sql", "true");
			settings.put("hibernate.hbm2ddl.auto", "update");
			
			Configuration configuration = new Configuration();
			configuration.setProperties(settings);

			configuration.addAnnotatedClass(PublisherPOJO.class);
			configuration.addAnnotatedClass(BookPOJO.class);

			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
					.applySettings(configuration.getProperties()).build();
			sessionFactory = configuration.buildSessionFactory(serviceRegistry);

		} catch (Throwable ex) {
			System.err.println("SessionFactory creation failed. " + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
