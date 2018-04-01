package com.mmpnc.rating.algorithm.executor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

import com.mmpnc.rating.rater.SIMRater;

public class BIDAlgorithmExecutorImplTest {

	private static final Logger logger = LoggerFactory
			.getLogger(BIDAlgorithmExecutorImplTest.class);

	private Document getDocument(String fileName) {
		SAXReader reader = new SAXReader();
		Document document = null;
		try {
			document = reader.read(new File(fileName));
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return document;
	}
	
	@Test
	public void testExecutor() {
		
		Calendar preStart = Calendar.getInstance();
		
		System.out.println("Start Time -> " + preStart.getTimeInMillis());
		
//		String fileName = "C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRater\\src\\test\\resources\\com\\mmpnc\\BIDPAPolicyData.xml";
//		String outputFileName = "C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRater\\src\\test\\resources\\com\\mmpnc\\BIDPAPolicyOutputData.xml";

//		String fileName = "C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRater\\src\\test\\resources\\com\\mmpnc\\bid\\PA_GQ_Req1.xml";
//		String outputFileName = "C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRater\\src\\test\\resources\\com\\mmpnc\\bid\\PA_GQ_Req1_output.xml";

		//PA
		String fileName = "C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRater\\src\\test\\resources\\com\\mmpnc\\bid\\PAS-BPQ_Request.xml";
		String outputFileName = "C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRater\\src\\test\\resources\\com\\mmpnc\\bid\\PAS-BPQ_Request_output.xml";

		//CO
//		String fileName = "C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRater\\src\\test\\resources\\com\\mmpnc\\bid\\PAS-GQ-CO-Request.xml";
//		String outputFileName = "C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRater\\src\\test\\resources\\com\\mmpnc\\bid\\PAS-GQ-CO-Response.xml";

		//CO
//		String fileName = "C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRater\\src\\test\\resources\\com\\mmpnc\\bid\\PAS-GQ-CO-V07012013-Request.xml";
//		String outputFileName = "C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRater\\src\\test\\resources\\com\\mmpnc\\bid\\PAS-GQ-CO-V07012013-Response.xml";
		
		//MO
//		String fileName = "C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRater\\src\\test\\resources\\com\\mmpnc\\bid\\PAS-GQ-MO-V07012013-Request.xml";
//		String outputFileName = "C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRater\\src\\test\\resources\\com\\mmpnc\\bid\\PAS-GQ-MO-V07012013-Response.xml";

		//OR
//		String fileName = "C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRater\\src\\test\\resources\\com\\mmpnc\\bid\\PAS-GQ-OR-V07012013-Request.xml";
//		String outputFileName = "C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRater\\src\\test\\resources\\com\\mmpnc\\bid\\PAS-GQ-OR-V07012013-Response.xml";


		Configuration config = new Configuration()
				.addAnnotatedClass(RatingEntity.class)
				.addAnnotatedClass(Process.class)
				.addAnnotatedClass(Step.class)
				.addAnnotatedClass(Program.class)
				.addAnnotatedClass(Product.class)
				.addAnnotatedClass(RateTable.class)
				.addAnnotatedClass(RateTableConfiguration.class)
				.addAnnotatedClass(RateTableEntry.class)
				.addAnnotatedClass(DomainTable.class)
				.addAnnotatedClass(DomainTableConfiguration.class)
				.addAnnotatedClass(DomainTableEntry.class)
				
				//MySql setup
				.setProperty("hibernate.dialect","org.hibernate.dialect.MySQLDialect")
				.setProperty("hibernate.connection.username", "root")
				.setProperty("hibernate.connection.password", "password")
				.setProperty("hibernate.connection.url","jdbc:mysql://localhost:3306/productdata")
				.setProperty("hibernate.connection.driver_class","com.mysql.jdbc.Driver")
				.setProperty("hibernate.show_sql", "false")
				.setProperty("hibernate.current_session_context_class",	"thread")
				.setProperty("hibernate.hbm2ddl.auto", "validate")
				
				//Oracle Setup
//				.setProperty("hibernate.dialect","org.hibernate.dialect.Oracle10gDialect")
//				.setProperty("hibernate.connection.username", "productdata")
//				.setProperty("hibernate.connection.password", "productdata")
//				.setProperty("hibernate.connection.url","jdbc:oracle:thin:@10.90.182.55:1521:BIDPOC")
//				.setProperty("hibernate.connection.driver_class","oracle.jdbc.driver.OracleDriver")
//				.setProperty("hibernate.show_sql", "false")
//				.setProperty("hibernate.current_session_context_class","thread")
//				.setProperty("hibernate.hbm2ddl.auto", "none")			
				;

		
		
		
		Document doc = getDocument(fileName);
        
		SessionFactory factory = config.buildSessionFactory();

		Session session = factory.openSession();
		session.beginTransaction();
		
		Calendar startCal = Calendar.getInstance();
		
		SIMRater rater = new SIMRater(doc, session, "SRERequest", "ProductId");
		rater.rate();
		
		Calendar endCal = Calendar.getInstance();
		session.close();

		logger.info("Ended the Algorithm Compilation @ "
				+ Calendar.getInstance().getTimeInMillis());

		System.out.println("Pre Processing Time in Mili " + (startCal.getTimeInMillis() - preStart.getTimeInMillis()));
		System.out.println("actual Processing Time in Mili " + (endCal.getTimeInMillis() - startCal.getTimeInMillis()));
		System.out.println("Total Processing Time in Mili " + (endCal.getTimeInMillis() - preStart.getTimeInMillis()));
		
		try {
			OutputFormat format = OutputFormat.createPrettyPrint();
			XMLWriter writer = new XMLWriter(new FileWriter(outputFileName),
					format);
			writer.write(doc);
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
