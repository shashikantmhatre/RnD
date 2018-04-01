package com.mmpnc.rating.algorithm.executor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Namespace;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mmpnc.context.Context;
import com.mmpnc.context.ContextBase;
import com.mmpnc.context.ContextParam;
import com.mmpnc.rating.algorithm.Variable;
import com.mmpnc.rating.algorithm.dao.impl.hibernate.RatingEntityDAO;
import com.mmpnc.rating.algorithm.execute.AlgorithmExecutor;
import com.mmpnc.rating.algorithm.util.ProgramCallLog;
import com.mmpnc.rating.dao.impl.hibernate.ObjectDAO;
import com.mmpnc.rating.dao.impl.hibernate.ProductDAO;
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

public class OldAlgorithmExecutorImplTest {

	private static final Logger logger = LoggerFactory
			.getLogger(OldAlgorithmExecutorImplTest.class);

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
		
		Calendar starttime = Calendar.getInstance();
		starttime.setTimeInMillis(System.currentTimeMillis());
		
//		String fileName = "C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRater\\src\\test\\resources\\com\\mmpnc\\SamplePolicyData.xml";
//		String outputFileName = "C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRater\\src\\test\\resources\\com\\mmpnc\\SamplePolicyOutputData.xml";

		String fileName = "C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRater\\src\\test\\resources\\com\\mmpnc\\SingleLocationPolicyData.xml";
		String outputFileName = "C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRater\\src\\test\\resources\\com\\mmpnc\\SingleLocationPolicyOutputData1.xml";

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

		Configuration config = new Configuration()
				.addAnnotatedClass(Product.class)
				.addAnnotatedClass(RatingEntity.class)
				.addAnnotatedClass(Process.class)
				.addAnnotatedClass(Step.class)
				.addAnnotatedClass(Program.class)
				.addAnnotatedClass(RateTable.class)
				.addAnnotatedClass(RateTableConfiguration.class)
				.addAnnotatedClass(RateTableEntry.class)
				.addAnnotatedClass(DomainTable.class)
				.addAnnotatedClass(DomainTableConfiguration.class)
				.addAnnotatedClass(DomainTableEntry.class)
				.setProperty("hibernate.dialect",
						"org.hibernate.dialect.MySQLDialect")
				.setProperty("hibernate.connection.username", "root")
				.setProperty("hibernate.connection.password", "password")
				.setProperty("hibernate.connection.url",
						"jdbc:mysql://localhost:3306/productdata")
				.setProperty("hibernate.connection.driver_class",
						"com.mysql.jdbc.Driver")
				.setProperty("hibernate.show_sql", "false")
				.setProperty("hibernate.current_session_context_class",
						"thread")
				.setProperty("hibernate.hbm2ddl.auto", "validate");

		Document doc = getDocument(fileName);
		Node rootNode = doc.node(0);

		Namespace namespace = new Namespace("rating", "http://mmpnc.com/rating");
		doc.getRootElement().add(namespace);
        
		SessionFactory factory = config.buildSessionFactory();

		Session session = factory.openSession();
		session.beginTransaction();

		
		
		Product product = new Product();
		product.setId(rootNode.valueOf("Ratebook"));

		ObjectDAO bd = new ProductDAO(product);
		product = (Product) bd.getUniqueRecord(session);

		
		System.out.println("Product " + product.getTitle());
		
		RatingEntity entity = new RatingEntity();
		entity.setName("Policy");
		entity.setProduct(product);

		bd = new RatingEntityDAO(entity);

		entity = (RatingEntity) bd.getUniqueRecord(session);

		System.out.println("Processing Start Time -> " + System.currentTimeMillis());

		for (Process process : entity.getProcesses()) {

			System.out.println("Process -> " + process.getName());
			System.out.println("Process id -> " + process.getId());
			StringBuffer algo = new StringBuffer();

			for (Step step : process.getSteps()) {
				System.out.println("Step Block -> " + step.getBlock());
				algo.append(step.getBlock()).append(" ");
			}

			// set this when we start the algorithm execution
			Context context = new ContextBase();
			context.putValue(ContextParam.PROCESS, process);
			context.putValue(ContextParam.SESSION, session);
			context.putValue(ContextParam.ENTITY, entity);
			context.putValue(ContextParam.LOCALVARIABLES,
					new HashMap<String, Variable>());
			// context.putValue(ContextParam.CURRENTPROGRAM, "");
			context.putValue(ContextParam.RATEBOOK, product);

			context.putValue(ContextParam.ROOTNODENAME, "Policy");
			context.putValue(ContextParam.PRODUCTIDNODENAME, "Ratebook");
			
			ProgramCallLog prg = new ProgramCallLog();
			prg.logCall("", "/Policy");
			prg.logCall("", "/Policy/Premium");

//			prg.logCall("", "/Policy/BOP/TerrorismCoverage");
//			prg.logCall("", "/Policy/BOP/BOPTerrorismCoverage");
//			prg.logCall("", "/Policy/BOP/BOPTerrorismCoverage/Premium");
//			prg.logCall("", "/Policy/BOP/BOPLocation/DedFactor");
			
			context.putValue(ContextParam.PROGRAMCALLLOG, prg);

//			algo = new StringBuffer();
//			
//			algo.append("LOOP THROUGH \"../BOPLocation/BOPStructure/BOPClassification\" ");
//			algo.append("LV_INTEGER:sample = 0 ");
//			algo.append("IF ( XPATH:\"../TerrorismCoverage\" Is Equal To \"Yes\" ) THEN "); 
//			algo.append("LV_INTEGER:sample  = LV_INTEGER:sample + ( XPATH:\"../BOPLocation/BOPStructure/BOPClassification/Number\" * XPATH:\"../BOPLocation/Number\" ) ");
//			algo.append("END IF ");
//			algo.append("Premium = Premium + LV_INTEGER:sample ");
//			algo.append("END LOOP ");
			
//			algo.append("LOOP THROUGH \"BOP/BOPLocation\" ");
//			algo.append("	LOOP THROUGH \"BOP/BOPLocation/BOPStructure/BOPClassification\" ");
//			algo.append("		Premium = Premium + XPATH:\"BOP/BOPLocation/BOPStructure/BOPClassification/Number\" ");
//			algo.append("	END LOOP");
//			algo.append("END LOOP");
//			algo.append("");
		
//			Node currentNode = doc.selectSingleNode("/Policy/BOP/BOPTerrorismCoverage");
			
//			AlgorithmExecutor exec = new AlgorithmExecutorImpl(doc, context,algo , currentNode);
			AlgorithmExecutor exec = new AlgorithmExecutor(doc, context,algo , null);
			try {
				exec.execute();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// System.out.println(prg.toString());
		}

		System.out.println("Start Time -> " + starttime.getTimeInMillis());
		System.out.println("End Time -> " + System.currentTimeMillis());

//		session.getTransaction().commit();
		session.close();

		logger.info("Ended the Algorithm Compilation @ "
				+ Calendar.getInstance().getTimeInMillis());

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
