package com.mmpnc.rating.algorithm.executor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

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

import com.mmpnc.connection.helper.DBFactoryBuilder;
import com.mmpnc.connection.helper.Database;
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

import com.mmpnc.rating.dao.RatingDAOFactory;
import com.mmpnc.rating.dao.RatingDAOFactory.ContentOption;
import com.mmpnc.rating.rater.SIMRater;

public class AlgorithmExecutorImplTest {

	private static final Logger logger = LoggerFactory
			.getLogger(AlgorithmExecutorImplTest.class);

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
	
//	@Test
	public void testRatingExecutorWithHibernate() {
		
		Calendar preStart = Calendar.getInstance();
		
		System.out.println("Execution Start Time -> " + preStart.getTimeInMillis());
		
//		String fileName = "C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRater\\src\\test\\resources\\com\\mmpnc\\SamplePolicyData.xml";
//		String outputFileName = "C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRater\\src\\test\\resources\\com\\mmpnc\\SamplePolicyOutputData.xml";

//		String fileName = "C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRater\\src\\test\\resources\\com\\mmpnc\\SingleLocationPolicyData.xml";
//		String outputFileName = "C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRater\\src\\test\\resources\\com\\mmpnc\\SingleLocationPolicyOutputData.xml";

//		String fileName = "C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRater\\src\\test\\resources\\com\\mmpnc\\PAPolicyData.xml";
//		String outputFileName = "C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRater\\src\\test\\resources\\com\\mmpnc\\PAPolicyOutputData.xml";

		//CA NY
//		String fileName = "C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRater\\src\\test\\resources\\com\\mmpnc\\CANYPolicyData.xml";
//		String outputFileName = "C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRater\\src\\test\\resources\\com\\mmpnc\\CANYPolicyOutputData.xml";

		//CA NY
//		String fileName = "C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRater\\src\\test\\resources\\com\\mmpnc\\WCPolicyData.xml";
//		String outputFileName = "C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRater\\src\\test\\resources\\com\\mmpnc\\WCPolicyOutputData.xml";

//		String fileName = "C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRater\\src\\test\\resources\\com\\mmpnc\\MultiLocationPolicyData.xml";
//		String outputFileName = "C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRater\\src\\test\\resources\\com\\mmpnc\\MultiLocationOutputPolicyData.xml";

//		String fileName = "C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRater\\src\\test\\resources\\com\\mmpnc\\CWSampleBPPolicyData.xml";
//		String outputFileName = "C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRater\\src\\test\\resources\\com\\mmpnc\\CWSampleBPPolicyOutputData.xml";

//		String fileName = "C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRater\\src\\test\\resources\\com\\mmpnc\\SRE-WCPolicyData.xml";
//		String outputFileName = "C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRater\\src\\test\\resources\\com\\mmpnc\\SRE-WCPolicyData_response.xml";

		//Commercial Auto
		String fileName = "C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRater\\src\\test\\resources\\com\\mmpnc\\commercialauto\\CACAPolicyData.xml";
		String outputFileName = "C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRater\\src\\test\\resources\\com\\mmpnc\\commercialauto\\CACAPolicyDataResponse.xml";

		//Personal Auto
//		String fileName = "C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRater\\src\\test\\resources\\com\\mmpnc\\HM-PAPolicyData.xml";
//		String outputFileName = "C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRater\\src\\test\\resources\\com\\mmpnc\\HM-PAPolicyDataResponse.xml";
		
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
//				.setProperty("hibernate.dialect","com.mmpnc.dialect.oracle.MyOracle10gDialect")
//				.setProperty("hibernate.dialect","org.hibernate.dialect.Oracle10gDialect")
//				.setProperty("hibernate.connection.username", "stgpas_base")
//				.setProperty("hibernate.connection.password", "stgpas_base")
//				.setProperty("hibernate.connection.url","jdbc:oracle:thin:@172.16.20.248:1521:orcl3")
//				.setProperty("hibernate.connection.driver_class","oracle.jdbc.driver.OracleDriver")
//				.setProperty("hibernate.show_sql", "false")
//				.setProperty("hibernate.current_session_context_class","thread")
//				.setProperty("hibernate.hbm2ddl.auto", "none")				

				//Oracle Setup
//				.setProperty("hibernate.dialect","com.mmpnc.dialect.oracle.MyOracle10gDialect")
//				.setProperty("hibernate.dialect","org.hibernate.dialect.Oracle10gDialect")
//				.setProperty("hibernate.connection.username", "WC_ISORATINGBASE")
//				.setProperty("hibernate.connection.password", "WC_ISORATINGBASE")
//				.setProperty("hibernate.connection.url","jdbc:oracle:thin:@172.16.20.248:1521:ORCL3")
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
		
//		SIMRater rater = new SIMRater(doc, session, "Policy", "Ratebook");
		
		SIMRater rater = new SIMRater(doc, session, "SRERequest", "ProductId");
		
		rater.rate();
		
		System.out.println("Done");
		
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
	
	@Test
	public void testRatingExecutorWithXMLDatabase() {
		
		Calendar preStart = Calendar.getInstance();
		
		System.out.println("Execution Start Time -> " + preStart.getTimeInMillis());
		
//		String fileName = "C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRater\\src\\test\\resources\\com\\mmpnc\\SamplePolicyData.xml";
//		String outputFileName = "C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRater\\src\\test\\resources\\com\\mmpnc\\SamplePolicyOutputData.xml";
		
//		String fileName = "C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRater\\src\\test\\resources\\com\\mmpnc\\SingleLocationPolicyData.xml";
//		String outputFileName = "C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRater\\src\\test\\resources\\com\\mmpnc\\SingleLocationPolicyOutputData.xml";
		
//		String fileName = "C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRater\\src\\test\\resources\\com\\mmpnc\\PAPolicyData.xml";
//		String outputFileName = "C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRater\\src\\test\\resources\\com\\mmpnc\\PAPolicyOutputData.xml";
		
		//CA NY
//		String fileName = "C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRater\\src\\test\\resources\\com\\mmpnc\\CANYPolicyData.xml";
//		String outputFileName = "C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRater\\src\\test\\resources\\com\\mmpnc\\CANYPolicyOutputData.xml";
		
		//CA NY
//		String fileName = "C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRater\\src\\test\\resources\\com\\mmpnc\\WCPolicyData.xml";
//		String outputFileName = "C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRater\\src\\test\\resources\\com\\mmpnc\\WCPolicyOutputData.xml";
		
//		String fileName = "C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRater\\src\\test\\resources\\com\\mmpnc\\MultiLocationPolicyData.xml";
//		String outputFileName = "C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRater\\src\\test\\resources\\com\\mmpnc\\MultiLocationOutputPolicyData.xml";
		
//		String fileName = "C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRater\\src\\test\\resources\\com\\mmpnc\\CWSampleBPPolicyData.xml";
//		String outputFileName = "C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRater\\src\\test\\resources\\com\\mmpnc\\CWSampleBPPolicyOutputData.xml";
		
//		String fileName = "C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRater\\src\\test\\resources\\com\\mmpnc\\SRE-WCPolicyData.xml";
//		String outputFileName = "C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRater\\src\\test\\resources\\com\\mmpnc\\SRE-WCPolicyData_response.xml";
		
		//Commercial Auto
		String fileName = "C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRater\\src\\test\\resources\\com\\mmpnc\\commercialauto\\CACAPolicyData.xml";
		String outputFileName = "C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRater\\src\\test\\resources\\com\\mmpnc\\commercialauto\\CACAPolicyDataResponse.xml";
		
		//Personal Auto
//		String fileName = "C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRater\\src\\test\\resources\\com\\mmpnc\\HM-PAPolicyData.xml";
//		String outputFileName = "C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRater\\src\\test\\resources\\com\\mmpnc\\HM-PAPolicyDataResponse.xml";
		
		
		Document doc = getDocument(fileName);
		
		Database db;
		com.mmpnc.connection.helper.Session session;
			
		Map<String, String> xmlMap = new HashMap<String, String>();
//		xmlMap.put("","C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRatingDataImport\\src\\test\\resources\\com\\mmpnc\\rating\\iso\\content\\dataimport\\Corrected\\Commercial Auto");

		xmlMap.put("1","C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRatingDataImport\\src\\test\\resources\\com\\mmpnc\\rating\\iso\\content\\dataimport\\Corrected\\Commercial Auto\\F1244B7F-10B4-43DB-9460-C59E72E21E9D_ISO Commercial Auto CW 1_20140201.xml");
		xmlMap.put("2","C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRatingDataImport\\src\\test\\resources\\com\\mmpnc\\rating\\iso\\content\\dataimport\\Corrected\\Commercial Auto\\0E17C7FD-40B3-44B0-86AC-6858EBD58F49_ISO Commercial Auto CW 2_20140201.xml");
		xmlMap.put("3","C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRatingDataImport\\src\\test\\resources\\com\\mmpnc\\rating\\iso\\content\\dataimport\\Corrected\\Commercial Auto\\93BBD210-679F-4516-BA9F-3629CFE79583_ISO Commercial Auto CA_20140301.xml");
		
		db = DBFactoryBuilder.getXMLDatabase("RatingRuntimeXMLDB", xmlMap);
		session = db.openSession();
		
		RatingDAOFactory.getRatingDAOFactory(ContentOption.XML);
		
		Calendar startCal = Calendar.getInstance();
		
//		SIMRater rater = new SIMRater(doc, session, "Policy", "Ratebook");
		
		SIMRater rater = new SIMRater(doc, session, "SRERequest", "ProductId");
		
		rater.rate();
		
		System.out.println("Done");
		
		Calendar endCal = Calendar.getInstance();
		
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
