package com.foxminded.university.spring.config;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jndi.JndiTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
@Configuration
@ComponentScan(basePackages = { "com.foxminded.university" })
@PropertySource("classpath:application.properties")
public class UniversityConfiguration {
	private static final String URL = "url";
	private static final String USER = "dbuser";
	private static final String DRIVER = "driver";
	private static final String PASSWORD = "dbpassword";

	@Bean
	public DataSource dataSource(Environment environment) {
		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
		driverManagerDataSource.setUrl(environment.getProperty(URL));
		driverManagerDataSource.setUsername(environment.getProperty(USER));
		driverManagerDataSource.setPassword(environment.getProperty(PASSWORD));
		driverManagerDataSource.setDriverClassName(environment.getProperty(DRIVER));
		return driverManagerDataSource;
	}

	@Bean
	DataSource dataSource() {
		DataSource dataSource = null;
		JndiTemplate jndi = new JndiTemplate();
		try {
			dataSource = jndi.lookup("java:comp/env/jdbc/university", DataSource.class);
		} catch (NamingException e) {
		}
		return dataSource;
	}

	@Bean
	JdbcTemplate jdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

	@Bean
	WebMvcConfigurer webMvcConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addResourceHandlers(ResourceHandlerRegistry registry) {
				registry.addResourceHandler("/properties/**").addResourceLocations("classpath:/static/");
			}
		};
	}
}
