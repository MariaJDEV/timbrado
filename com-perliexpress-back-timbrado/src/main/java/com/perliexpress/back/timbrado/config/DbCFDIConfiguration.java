package com.perliexpress.back.timbrado.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;


@Configuration
@EnableJpaRepositories(entityManagerFactoryRef = "cfdiEntityManagerFactory", transactionManagerRef = "cfdiTransactionManager", basePackages = {
		"com.perliexpress.back.timbrado.repositorycfdi" })
//com.perliexpress.back.timbrado.repositorycfdi
public class DbCFDIConfiguration {
	@Bean(name = "cfdiDataSource")
	@ConfigurationProperties(prefix = "sqlserver2.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "cfdiEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("cfdiDataSource") DataSource dataSource) {
		return builder.dataSource(dataSource)
				// Ruta de las clases Entity para la base de datos de Cliente
				//
				.packages("com.perliexpress.back.timbrado.entitycfdi").persistenceUnit("PerliCfdi").build();
	}

	@Bean(name = "cfdiTransactionManager")
	public PlatformTransactionManager transactionManager(
			@Qualifier("cfdiEntityManagerFactory") EntityManagerFactory clientEntityManagerFactory) {
		return new JpaTransactionManager(clientEntityManagerFactory);
	}
	
}
