package com.mmpnc.rating.icm.content.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.mmpnc.rating.vo.DomainTable;
import com.mmpnc.rating.vo.DomainTableConfiguration;
import com.mmpnc.rating.vo.DomainTableEntry;
import com.mmpnc.rating.vo.Process;
import com.mmpnc.rating.vo.Product;
import com.mmpnc.rating.vo.Program;
import com.mmpnc.rating.vo.RateTable;
import com.mmpnc.rating.vo.RateTableConfiguration;
import com.mmpnc.rating.vo.RateTableEntry;
import com.mmpnc.rating.vo.RatingEntity;
import com.mmpnc.rating.vo.Step;

/**
 * Author : Shashikant Mhatre
 *
 * Created On : Oct 12, 2013
 *
 * Description :
 *
 * Note :
 */

public class HibernateUtil {

	private static final SessionFactory factory;

	static {
		Configuration config = new Configuration()
				.addAnnotatedClass(Product.class)
				.addAnnotatedClass(DomainTable.class)
				.addAnnotatedClass(DomainTableConfiguration.class)
				.addAnnotatedClass(DomainTableEntry.class)
				.addAnnotatedClass(RateTable.class)
				.addAnnotatedClass(RateTableConfiguration.class)
				.addAnnotatedClass(RateTableEntry.class)
				.addAnnotatedClass(RatingEntity.class)
				.addAnnotatedClass(Process.class)
				.addAnnotatedClass(Program.class)
				.addAnnotatedClass(Step.class)
				
				//MySql Setup
				.setProperty("hibernate.dialect","org.hibernate.dialect.MySQLDialect")
				.setProperty("hibernate.connection.username", "root")
				.setProperty("hibernate.connection.password", "password")
				.setProperty("hibernate.connection.url","jdbc:mysql://localhost:3306/ProductData")
				.setProperty("hibernate.connection.driver_class","com.mysql.jdbc.Driver")
				.setProperty("hibernate.show_sql", "false")
				.setProperty("hibernate.current_session_context_class","thread")
				.setProperty("hibernate.hbm2ddl.auto", "update")

				//Oracle Setup POC
//				.setProperty("hibernate.dialect","org.hibernate.dialect.Oracle10gDialect")
//				.setProperty("hibernate.connection.username", "productdata")
//				.setProperty("hibernate.connection.password", "productdata")
//				.setProperty("hibernate.connection.url","jdbc:oracle:thin:@10.90.182.55:1521:BIDPOC")
//				.setProperty("hibernate.connection.driver_class","oracle.jdbc.driver.OracleDriver")
//				.setProperty("hibernate.show_sql", "false")
//				.setProperty("hibernate.current_session_context_class","thread")
//				.setProperty("hibernate.hbm2ddl.auto", "update")

				//Oracle Setup Performance Testing
//				.setProperty("hibernate.dialect","org.hibernate.dialect.Oracle10gDialect")
//				.setProperty("hibernate.connection.username", "poc_rating")
//				.setProperty("hibernate.connection.password", "poc_rating")
//				.setProperty("hibernate.connection.url","jdbc:oracle:thin:@10.90.130.103:1521:pncprod")
//				.setProperty("hibernate.connection.driver_class","oracle.jdbc.driver.OracleDriver")
//				.setProperty("hibernate.show_sql", "false")
//				.setProperty("hibernate.current_session_context_class","thread")
//				.setProperty("hibernate.hbm2ddl.auto", "update")

				//Oracle Setup WC - HandsonEnvironment
//				.setProperty("hibernate.dialect","org.hibernate.dialect.Oracle10gDialect")
//				.setProperty("hibernate.connection.username", "WC_ISORATINGBASE")
//				.setProperty("hibernate.connection.password", "WC_ISORATINGBASE")
//				.setProperty("hibernate.connection.url","jdbc:oracle:thin:@172.16.20.248:1521:ORCL3")
//				.setProperty("hibernate.connection.driver_class","oracle.jdbc.driver.OracleDriver")
//				.setProperty("hibernate.show_sql", "true")
//				.setProperty("hibernate.current_session_context_class","thread")
//				.setProperty("hibernate.hbm2ddl.auto", "update")
				
				;

		factory = config.buildSessionFactory();
	}

	HibernateUtil() {
	}

	public static Session newSession() {
		return factory.openSession();
	}

	public static Session getSession() {
		return factory.getCurrentSession();
	}

	public static void closeSession() {
		factory.getCurrentSession().close();
	}

	public static Transaction beginTransaction() {
		Session session = getSession();
		return session.beginTransaction();
	}

	public static void endTransaction() {
		factory.getCurrentSession().getTransaction().commit();
	}
}
