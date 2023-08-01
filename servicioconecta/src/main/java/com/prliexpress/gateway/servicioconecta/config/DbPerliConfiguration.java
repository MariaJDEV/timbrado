package com.prliexpress.gateway.servicioconecta.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "claroPayEntityManagerFactory",
transactionManagerRef = "claroPayTransactionManager", basePackages = {
		"com.prliexpress.gateway.servicioconecta.repositoryperli" })
public class DbPerliConfiguration {

	@Primary
	@Bean(name = "claroPayDataSource")
	@ConfigurationProperties(prefix = "sqlserver.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}

	@Primary
	@Bean(name = "claroPayEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder,
			 @Qualifier("claroPayDataSource") DataSource dataSource) {
		return builder.dataSource(dataSource)
				.packages("com.prliexpress.gateway.servicioconecta.entityperli").persistenceUnit("PerliExp").build();
	}                      
	@Primary
	@Bean(name = "claroPayTransactionManager")
	public PlatformTransactionManager transactionManager(@Qualifier("claroPayEntityManagerFactory")EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}
	
}
