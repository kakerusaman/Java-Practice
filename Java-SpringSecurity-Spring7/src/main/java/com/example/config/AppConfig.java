package com.example.config;


import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
@ComponentScan(basePackages = {"com.example.service"})
@EnableTransactionManagement
@MapperScan("com.example.mapper")
@PropertySource("classpath:application.properties")
public class AppConfig {

	private final Environment env;

	public AppConfig(Environment env) {
		this.env = env;
	}

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.postgresql.Driver");
		dataSource.setUrl("jdbc:postgresql://localhost:5434/springsecurity_spring7");
		dataSource.setUsername("postgres");
		dataSource.setPassword("yourpassword");
		return dataSource;
	}

	@Bean
	public PlatformTransactionManager transactionManager(DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	@Bean
	public SqlSessionFactoryBean sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
		sessionFactoryBean.setDataSource(dataSource());
		sessionFactoryBean.setConfigLocation(new ClassPathResource("/mybatis-config.xml"));
		Resource[] mapperLocations = new PathMatchingResourcePatternResolver()
				.getResources("classpath:mapper/**/*.xml");
		sessionFactoryBean.setMapperLocations(mapperLocations);
		return sessionFactoryBean;
	}

	@Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource ms = new ResourceBundleMessageSource();
        ms.setBasename("message");
        ms.setDefaultEncoding("UTF-8");
        return ms;
    }

	@Bean
	public LocalValidatorFactoryBean validator() {
    	LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
    	bean.setValidationMessageSource(messageSource());
    	return bean;
	}

	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

	@Bean
	public JavaMailSenderImpl mailSender() {
		String mailFrom = env.getProperty("mail.from");
		String mailPassword = env.getProperty("mail.password");
		System.out.println("=== mail.from: " + mailFrom);
		System.out.println("=== mail.password length: " + (mailPassword != null ? mailPassword.length() : "null"));
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(587);
		mailSender.setUsername(mailFrom);
		mailSender.setPassword(mailPassword);
		mailSender.getJavaMailProperties().setProperty("mail.smtp.auth", "true");
		mailSender.getJavaMailProperties().setProperty("mail.smtp.starttls.enable", "true");
		return mailSender;
	}

}
