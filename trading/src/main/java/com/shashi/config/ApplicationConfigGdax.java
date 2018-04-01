package com.shashi.config;

import java.sql.Connection;
import java.sql.Statement;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.FilterType;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.web.socket.client.WebSocketConnectionManager;
import org.springframework.web.socket.client.jetty.JettyWebSocketClient;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

import com.shashi.trading.gdax.SimpleClientWebSocketHandler;

@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = false)
@ComponentScan(basePackages = { "com.shashi" }, excludeFilters = {
		@ComponentScan.Filter(type = FilterType.REGEX, pattern = ""), })
public class ApplicationConfigGdax {

	@Bean
	public DataSource metadataDSForH2() throws Exception {
		final DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName("org.h2.Driver");
		ds.setUrl("jdbc:h2:tcp://localhost:9092/~/audit");
		ds.setUsername("sa");
		ds.setPassword("password");

		return ds;
	}

	
	@Bean
	public JettyWebSocketClient client() {
		return new JettyWebSocketClient();
	}

}
