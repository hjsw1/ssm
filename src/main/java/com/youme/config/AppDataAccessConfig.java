package com.youme.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
public class AppDataAccessConfig {
	private static final Logger logger = LoggerFactory.getLogger(AppDataAccessConfig.class);

	private static final String driverClassName = "com.mysql.jdbc.Driver";
	private static final String url = "jdbc:mysql://localhost:3306/ssm?useUnicode=true&characterEncoding=UTF-8";
	private static final String username = "root";
	private static final String password = "root";

	@Bean(destroyMethod = "close")
	public DataSource dataSource() {
		logger.debug("jdbc.url is:" + url);
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(driverClassName);
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		dataSource.setMaxTotal(50);
		dataSource.setMinIdle(5);
		dataSource.setMaxIdle(10);
		return dataSource;
	}

	@Bean
	public DataSourceTransactionManager txManager() {
		return new DataSourceTransactionManager(dataSource());
	}

	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		return sessionFactory.getObject();
	}
}
