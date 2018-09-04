package com.hws.mybatisgenerator.core;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 
 * @author hws
 *
 */
@Component
public class JDBCPropertiesBean {
	@Value("${spring.datasource.url}")
	public   String url;
	@Value("${spring.datasource.username}")
	public  String username;
	@Value("${spring.datasource.password}")
	public  String password;
	@Value("${spring.datasource.driverClassName}")
	public  String driverClassName;
	public String getUrl() {
		return url;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public String getDriverClassName() {
		return driverClassName;
	}
	
}
