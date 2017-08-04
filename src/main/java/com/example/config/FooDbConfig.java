package com.example.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
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
@EnableJpaRepositories(
  entityManagerFactoryRef = "entityManagerFactory",
  basePackages = { "com.example.bean.repository" }
)
public class FooDbConfig {
  
	@Bean
	@Primary
	@ConfigurationProperties("spring.datasource")
	public DataSourceProperties fooDataSourceProperties() {
	    return new DataSourceProperties();
	}	
	
	@Bean(name = "mydataSource")
	@Primary
	@ConfigurationProperties("spring.datasource")
	public org.apache.tomcat.jdbc.pool.DataSource fooDataSource() {
		
		org.apache.tomcat.jdbc.pool.DataSource ds = (org.apache.tomcat.jdbc.pool.DataSource)DataSourceBuilder.create().type(org.apache.tomcat.jdbc.pool.DataSource.class).build();
		ds.setMaxActive(30);
		ds.setMaxWait(10000);
		return ds;
	}
	
//  @Primary
//  @Bean(name = "mydataSource")
//  @ConfigurationProperties(prefix = "spring.datasource")
//  public DataSource dataSource() {
//    return DataSourceBuilder.create().build();
//  }
  
  
  @Primary
  @Bean(name = "entityManagerFactory")
  public LocalContainerEntityManagerFactoryBean entityManagerFactory(
    EntityManagerFactoryBuilder builder, @Qualifier("mydataSource") DataSource dataSource) {
    return builder.dataSource(dataSource)
      .packages("com.example.bean")
      .persistenceUnit("foo")
      .build();
  }
    
  @Primary
  @Bean(name = "transactionManager")
  public PlatformTransactionManager transactionManager(
    @Qualifier("entityManagerFactory") EntityManagerFactory entityManagerFactory) {
    return new JpaTransactionManager(entityManagerFactory);
  }
}