package com.scalefocus.edu.config;

import java.util.Properties;
import javax.naming.InitialContext; 
import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration; 
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;


@Configuration
@EnableJpaRepositories(basePackages="com.scalefocus.edu.db.dao")
public class DBConfig {
	/*
	 * Does jndi look up for the DataSource and loads it as a Spring Bean
	 */
	@Bean
	public DataSource dataSource() throws NamingException {
		Context initContext = new InitialContext();
		Context envContext = (Context) initContext.lookup("java:comp/env");
		return (DataSource) envContext.lookup("jdbc/ClientStore");
	}

	/*
	 * Loads JPA transaction manager as a Spring Bean
	 */
	@Bean
	public JpaTransactionManager transactionManager() {
		return new JpaTransactionManager();
	}
	
	/*
	 * Initializes entity manager factory and loads it as a Spring Bean to be used by
	 * Spring Data.
	 */
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(
			DataSource datasource) {
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setDataSource(datasource);
		entityManagerFactoryBean
				.setPersistenceProviderClass(HibernatePersistenceProvider.class);
		entityManagerFactoryBean
				.setPackagesToScan("com.scalefocus.edu.db.model");
		Properties jpaProperties = new Properties();
		jpaProperties.setProperty("hibernate.dialect",
				"org.hibernate.dialect.MySQL5InnoDBDialect");
		jpaProperties.setProperty("hibernate.hbm2ddl.auto", "validate");
		entityManagerFactoryBean.setJpaProperties(jpaProperties);
		return entityManagerFactoryBean;
	}
}
