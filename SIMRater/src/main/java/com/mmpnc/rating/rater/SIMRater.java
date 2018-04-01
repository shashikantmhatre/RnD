package com.mmpnc.rating.rater;

import java.util.HashMap;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Namespace;
import org.dom4j.Node;
//import org.hibernate.Session;

import com.mmpnc.context.Context;
import com.mmpnc.context.ContextBase;
import com.mmpnc.context.ContextParam;
import com.mmpnc.rating.algorithm.Variable;
//import com.mmpnc.rating.algorithm.dao.impl.hibernate.ProgramDAO;
//import com.mmpnc.rating.algorithm.dao.impl.hibernate.RatingEntityDAO;
//import com.mmpnc.rating.algorithm.dao.impl.hibernate.StepDAO;
import com.mmpnc.rating.algorithm.execute.AlgorithmExecutor;
import com.mmpnc.rating.algorithm.util.ProgramCallLog;
import com.mmpnc.rating.dao.BaseDAO;
import com.mmpnc.rating.dao.RatingDAOFactory;
//import com.mmpnc.rating.dao.impl.hibernate.ObjectDAO;
//import com.mmpnc.rating.dao.impl.hibernate.ProductDAO;
import com.mmpnc.rating.vo.Product;
import com.mmpnc.rating.vo.Process;
import com.mmpnc.rating.vo.Program;
import com.mmpnc.rating.vo.RatingEntity;
import com.mmpnc.rating.vo.Step;


/**
 * Author : Shashikant Mhatre
 *
 * Created On : Jan 10, 2014
 *
 * Description :
 *
 * Note :
 */

public class SIMRater implements Rater {
	
	private Document doc;
//	private Session session;
	
	private Object session;
	
	private String rootNodeName;
	private String productIDNodeName;
	
//	public SIMRater(Document xmlDocument, Session session, String rootNodeName, String productIDNodeName){
	public SIMRater(Document xmlDocument, Object session, String rootNodeName, String productIDNodeName){
		this.rootNodeName = rootNodeName;
		this.productIDNodeName = productIDNodeName;
		this.doc = xmlDocument;		

		Namespace namespace = new Namespace("rating", "http://mmpnc.com/rating");
		doc.getRootElement().add(namespace);
		
		this.session = session;
	}
	
	@Override
	public Document rate() {
		//This was changed to get the RootElement rather than 0 th node
//		Node rootNode = doc.node(0);
		Node rootNode = doc.getRootElement();
		
		Product product = new Product();
		product.setId(rootNode.valueOf(productIDNodeName));

		System.out.println("Product Id " + product.getId());	
		
		System.out.println("Start Time -> " + System.currentTimeMillis());
		
//		ObjectDAO bd = new ProductDAO(product);
		BaseDAO bd = RatingDAOFactory.getRatingDAOFactory().getProductDAO(product);

		product = (Product) bd.getUniqueRecord(session);

		RatingEntity entity = new RatingEntity();
		entity.setName(rootNodeName);
		entity.setProduct(product);

//		bd = new RatingEntityDAO(entity);
		bd = RatingDAOFactory.getRatingDAOFactory().getRatingEntityDAO(entity);

		entity = (RatingEntity) bd.getUniqueRecord(session);

		Process ratingProcess = new Process();
		ratingProcess.setExecutionorder(-1);
		ratingProcess.setEntity(entity);

		bd = RatingDAOFactory.getRatingDAOFactory().getProcessDAO(ratingProcess);
		List<Process> ratingEntityProcesse = bd.getRecords(session);
				
//		for (Process process : entity.getProcesses()) {
		for (Process process : ratingEntityProcesse) {

			System.out.println("Process -> " + process.getName());
			System.out.println("Process id -> " + process.getId());
			StringBuffer algo = new StringBuffer();

			//modified
			Program ratingProgram = new Program();
			ratingProgram.setExecutionorder(-1);
			ratingProgram.setName("Premium");
			ratingProgram.setProcess(process);
			
//			ObjectDAO progBaseDAO = new ProgramDAO(ratingProgram);
			BaseDAO progBaseDAO = RatingDAOFactory.getRatingDAOFactory().getProgramDAO(ratingProgram);

			ratingProgram = progBaseDAO.getUniqueRecord(session);
			
			//Commented below block as we will get the stpes from program itself.
			Step tStep = new Step();
			tStep.setProcess(process);
			tStep.setProgram(ratingProgram);
			
			BaseDAO stepDAO = RatingDAOFactory.getRatingDAOFactory().getStepDAO(tStep);
			
			List<Step> steps = stepDAO.getRecords(session);
			
//			for (Step step : ratingProgram.getSteps()) {
			for (Step step : steps) {
//				System.out.println("Step Block -> " + step.getBlock());
				algo.append(step.getBlock()).append(" ");
			}

			// set this when we start the algorithm execution
			Context context = new ContextBase();
			context.putValue(ContextParam.PROCESS, process);
			context.putValue(ContextParam.SESSION, session);
			context.putValue(ContextParam.ENTITY, entity);
			context.putValue(ContextParam.LOCALVARIABLES, new HashMap<String, Variable>());
//			context.putValue(ContextParam.CURRENTPROGRAM, "");
			context.putValue(ContextParam.RATEBOOK, product);
			
			context.putValue(ContextParam.ROOTNODENAME, rootNodeName);
			context.putValue(ContextParam.PRODUCTIDNODENAME, productIDNodeName);
			
			ProgramCallLog prg = new ProgramCallLog();
			prg.logCall("", "/" + rootNodeName);
			prg.logCall("", "/" + rootNodeName + "/Premium");
			context.putValue(ContextParam.PROGRAMCALLLOG, prg);
			
			AlgorithmExecutor exec = new AlgorithmExecutor(doc, context, algo);
			try {
				exec.execute();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		System.out.println("End Time -> " + System.currentTimeMillis());
		
		return doc;
	}
}
