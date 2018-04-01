package com.mmpnc.rating.algorithm.util;

import java.util.HashMap;
import java.util.List;
//import java.util.Map;

import org.dom4j.Node;
//import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mmpnc.context.Context;
import com.mmpnc.context.ContextBase;
import com.mmpnc.context.ContextParam;
import com.mmpnc.rating.algorithm.Variable;
//import com.mmpnc.rating.algorithm.dao.impl.hibernate.ProcessDAO;
//import com.mmpnc.rating.algorithm.dao.impl.hibernate.ProgramDAO;
//import com.mmpnc.rating.algorithm.dao.impl.hibernate.RatingEntityDAO;
//import com.mmpnc.rating.algorithm.dao.impl.hibernate.StepDAO;
import com.mmpnc.rating.algorithm.execute.AlgorithmExecutor;
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
 * Created On : Oct 12, 2013
 *
 * Description :
 *
 * Note :
 */

public class ProcessBlockExecutor {

	// private String output;
	private Context context;
	private static final Logger logger = LoggerFactory
			.getLogger(ProcessBlockExecutor.class);

	public ProcessBlockExecutor(Context rootContext) {
		this.context = new ContextBase();

		// TODO
		// uncommented below try block to try new thing of passing the cloned xpath reference
		try {
			if (rootContext.getValue(ContextParam.XPATHRESOLVER) != null) {
				this.context.putValue(ContextParam.XPATHRESOLVER,
						((XPathResolver) rootContext
								.getValue(ContextParam.XPATHRESOLVER)).clone());
			}
		} catch (Exception _ex) {
			logger.error("Error occured in cloning XPathResolver " + _ex);
		}

//		this.context.putValue(ContextParam.JXPATHCONTEXT,
//				rootContext.getValue(ContextParam.JXPATHCONTEXT));
		this.context.putValue(ContextParam.SESSION,
				rootContext.getValue(ContextParam.SESSION));
		this.context.putValue(ContextParam.ENTITY,
				rootContext.getValue(ContextParam.ENTITY));
		this.context.putValue(ContextParam.PROCESS,
				rootContext.getValue(ContextParam.PROCESS));
		this.context.putValue(ContextParam.RATEBOOK,
				rootContext.getValue(ContextParam.RATEBOOK));
		this.context.putValue(ContextParam.LOCALVARIABLES,
				rootContext.getValue(ContextParam.LOCALVARIABLES));
		this.context.putValue(ContextParam.PROGRAMCALLLOG,
				rootContext.getValue(ContextParam.PROGRAMCALLLOG));
		
		// below changes are for the dom4j implementation
		this.context.putValue(ContextParam.CURRENTNODE,
				rootContext.getValue(ContextParam.CURRENTNODE));
		this.context.putValue(ContextParam.PREVIOUSNODE,
				rootContext.getValue(ContextParam.PREVIOUSNODE));
		this.context.putValue(ContextParam.XMLDOCUMENT,
				rootContext.getValue(ContextParam.XMLDOCUMENT));
		
		// below changes are done after SIMRater implementation
		this.context.putValue(ContextParam.ROOTNODENAME, rootContext.getValue(ContextParam.ROOTNODENAME));
		this.context.putValue(ContextParam.PRODUCTIDNODENAME, rootContext.getValue(ContextParam.PRODUCTIDNODENAME));
	}

	public boolean executeBlockSteps(String actual, String reference) {
		
		logger.info("We are in executeblocksteps");
		
//		if( ! executeProcessSteps(actual,reference)){
//			return executeProgramSteps(actual,reference);
//		}		

		
		//Currently Rating engine is modified to execute statements from a RatingProgram object only and not from the RatingProcess.
		//Hence below function execution is commented.
		// TODO un comment this if not working
//		executeProcessSteps(actual,reference);
		
		executeProgramSteps(actual,reference);
		
		return false;
	}

	@SuppressWarnings("unused")
	private boolean executeProcessSteps(String actual, String reference) {
		// TODO Auto-generated method stub
		
		if(logger.isInfoEnabled()){
			logger.info("before start " + reference);
		}
		if(reference.matches(".*/Premium$") && !isBlockExecuted((reference.replaceAll("/Premium", "").equals("") ? "/" : reference.replaceAll("/Premium", "")))){
			
			if(logger.isInfoEnabled()){
				logger.info("We are in executeProcessSteps");
			}
			Node currentNode = (Node) this.context.getValue(ContextParam.CURRENTNODE);
			Object session = this.context.getValue(ContextParam.SESSION);
			Process priorProcess = (Process) this.context
					.getValue(ContextParam.PROCESS);

			if (priorProcess == null ){
				return false;
			}
			
			Node referenceNode = currentNode.selectSingleNode(reference.replaceAll("/Premium", ""));
			
			if( ! isObjectPresent(referenceNode)){
				ProgramCallLog prgCall = (ProgramCallLog) this.context
						.getValue(ContextParam.PROGRAMCALLLOG);
				prgCall.logCall("", (reference.replaceAll("/Premium", "").equals("") ? "/" : reference.replaceAll("/Premium", "")));
				return false;
			}

			//get the ratebook here
			Product parentNodeRatebook = (Product) context.getValue(ContextParam.RATEBOOK); 
			Product currentNodeRatebook = getProduct(referenceNode);
			
			String[] programDefinition = getProgramReference(reference);
			
			RatingEntity entity = new RatingEntity();
			entity.setName(programDefinition[0]);
			
			if(entity.getName().equals("")){
				String rootNodeName = (String) context.getValue(ContextParam.ROOTNODENAME);
				entity.setName(rootNodeName);
			}			
			
			entity.setProduct(currentNodeRatebook);
			
//			ObjectDAO ratingEntityDAO = new RatingEntityDAO(entity);
			BaseDAO ratingEntityDAO = RatingDAOFactory.getRatingDAOFactory().getRatingEntityDAO(entity);
			
			entity = ratingEntityDAO.getUniqueRecord(session);
			
			if(entity == null){
				if(logger.isInfoEnabled()){
					logger.info("Could not find the rating entity [ " + programDefinition[0] + " ] definition in database ");
				}
				return false;
			}
			
			Process ratingProcess = new Process();
			ratingProcess.setName(priorProcess.getName());
			ratingProcess.setExecutionorder(priorProcess.getExecutionorder());
			ratingProcess.setEntity(entity);
			
//			ObjectDAO processDAO = new ProcessDAO(ratingProcess);
			BaseDAO processDAO = RatingDAOFactory.getRatingDAOFactory().getProcessDAO(ratingProcess);
	
			ratingProcess = processDAO.getUniqueRecord(session);			
			
			if(logger.isInfoEnabled()){
				logger.info("Rating process " + ratingProcess.getId());
			}
			StringBuffer algo = new StringBuffer();
			
			for(Step step : ratingProcess.getSteps()){
				if(step.getProgram() == null){
					algo.append(step.getBlock()).append("\n");
				}
			}
						
			if(algo.length()>0){
				
				ProgramCallLog prgCall = (ProgramCallLog) this.context
						.getValue(ContextParam.PROGRAMCALLLOG);
				prgCall.logCall("", (reference.replaceAll("/Premium", "").equals("") ? "/" : reference.replaceAll("/Premium", "")));
				
				this.context.putValue(ContextParam.CURRENTNODE, referenceNode);				
				
//				if (logger.isInfoEnabled()) {
//					logger.info("Algorithm Block is " + algo);
//				}

				this.context.putValue(ContextParam.LOCALVARIABLES,
						new HashMap<String, Variable>());
				this.context.putValue(ContextParam.ENTITY, entity);
				this.context.putValue(ContextParam.PROCESS, ratingProcess);
				
				// reset xpath resolver before starting the next context
				this.context.putValue(ContextParam.XPATHRESOLVER, null);
				
				this.context.putValue(ContextParam.RATEBOOK,currentNodeRatebook);

				AlgorithmExecutor exec = new AlgorithmExecutor(this.context, algo);
				try {
					exec.execute();
				} catch (Exception e) {
					logger.error("Error in ProcessBlockExecutor " , e);
				}

				// reset the programCallLog to original
				this.context.putValue(ContextParam.PROGRAMCALLLOG, prgCall);
				// System.out.println("After " +prgCall.toString());

				// clear the local variables;
//				System.out.println("Clear Local Variables after execution of Process -> "+ reference);
				clearLocalVariables();
				
				//set the ratebook back to old one
				this.context.putValue(ContextParam.RATEBOOK,parentNodeRatebook);
				
				return true;
			}
			
			return false;
		}
		
		
		return false;
	}

	private boolean executeProgramSteps(String actual, String reference) {
		if (!isBlockExecuted(reference)) {

			if(logger.isInfoEnabled()){
				logger.info("We are in executeProgramSteps");
			}
			
			String[] programDefinition = getProgramReference(reference);
			
			ProgramCallLog prgCall = (ProgramCallLog) this.context
					.getValue(ContextParam.PROGRAMCALLLOG);
			prgCall.logCall("", reference);

			Node currentNode = (Node) this.context
					.getValue(ContextParam.CURRENTNODE);
			Object session = this.context
					.getValue(ContextParam.SESSION);
			Process priorProcess = (Process) this.context
					.getValue(ContextParam.PROCESS);

			if (priorProcess == null) {
				return false;
			}

			if(logger.isInfoEnabled()){
				logger.info("Check if object exists " +  reference.substring(0,reference.lastIndexOf("/")));
			}
			Node referenceNode = currentNode.selectSingleNode(reference.substring(0,reference.lastIndexOf("/")));
			
			if(! isObjectPresent(referenceNode)){
				return false;
			} 
			
			
			//get the ratebook here
			Product priorRatebook = (Product) context.getValue(ContextParam.RATEBOOK); 
			Product currentRatebook = getProduct(referenceNode);
			
			
			//we should get the ratebook which has the rating algorithm
			//so go till the root ratebook to get the algorithm
			
			/*
			RatingEntity entity = new RatingEntity();
			entity.setName(programDefinition[0]);
			entity.setRatebook(currentRatebook);
			
			if(entity.getName().equals("")){
				String rootNodeName = (String) context.getValue(ContextParam.ROOTNODENAME);
				entity.setName(rootNodeName);
			}
			
			BaseDAO ratingEntityDAO = new RatingEntityDAO(entity);
			entity = ratingEntityDAO.getUniqueRecord(session);

			if(entity == null){
				if(logger.isInfoEnabled()){
					logger.info("Could not find the rating entity [ " + programDefinition[0] + " ] definition in database ");
				}
				return false;
			}
			
			Process ratingProcess = new Process();
			ratingProcess.setName(priorProcess.getName());
			ratingProcess.setExecutionorder(priorProcess.getExecutionorder());
			ratingProcess.setEntity(entity);

			BaseDAO processDAO = new ProcessDAO(ratingProcess);
			ratingProcess = processDAO.getUniqueRecord(session);

			if(logger.isInfoEnabled()){
				logger.info("Rating process " + ratingProcess.getId());
			}
			Program ratingProgram = new Program();
			ratingProgram.setName(programDefinition[1]);
			ratingProgram.setProcess(ratingProcess);
			
			BaseDAO progBaseDAO = new ProgramDAO(ratingProgram);
			ratingProgram = progBaseDAO.getUniqueRecord(session);
			*/
			
			Program ratingProgram = getRatingProgram(programDefinition[0], programDefinition[1], currentRatebook, priorProcess, session);
			
			if(ratingProgram == null){
				if(logger.isInfoEnabled()){
					logger.info("Did not find the program " + programDefinition[1]);
				}
				return false;
			}
			
			if(logger.isInfoEnabled()){
				logger.info("Rating Program " + ratingProgram.getId());
				logger.info("Rating Program Name " + ratingProgram.getName());
			}
			
			StringBuffer algo = new StringBuffer();

			Step tStep = new Step();
			tStep.setProcess(ratingProgram.getProcess());
			tStep.setProgram(ratingProgram);
			
			BaseDAO stepDAO = RatingDAOFactory.getRatingDAOFactory().getStepDAO(tStep);
			
			List<Step> steps = stepDAO.getRecords(session);
			
//			for (Step step : ratingProgram.getSteps()) {
			for (Step step : steps) {
				algo.append(step.getBlock()).append("\n");
			}

			
			if (algo.length() > 0) {

				this.context.putValue(ContextParam.CURRENTNODE,	referenceNode);

//				if (logger.isInfoEnabled()) {
//					logger.info("Algorithm in executeProgramSteps " + algo );
//				}

				this.context.putValue(ContextParam.LOCALVARIABLES,
						new HashMap<String, Variable>());
				
//				this.context.putValue(ContextParam.ENTITY, ratingEntity);
//				this.context.putValue(ContextParam.PROCESS, ratingProcess);

				this.context.putValue(ContextParam.ENTITY, ratingProgram.getProcess().getEntity());
				this.context.putValue(ContextParam.PROCESS, ratingProgram.getProcess());

				// reset xpath resolver before starting the next context
				this.context.putValue(ContextParam.XPATHRESOLVER, null);
				this.context.putValue(ContextParam.RATEBOOK,currentRatebook);
				
				
				AlgorithmExecutor exec = new AlgorithmExecutor(this.context, algo);
				try {
					exec.execute();
				} catch (Exception e) {
					logger.error("Error in ProcessBlockExecutor " , e);
				}
					
				// reset the programCallLog to original
				this.context.putValue(ContextParam.PROGRAMCALLLOG, prgCall);
				// System.out.println("After " +prgCall.toString());

				// clear the local variables;
//				System.out
//						.println("Clear Local Variables after execution of Process in executeProgramSteps -> "
//								+ reference);
				clearLocalVariables();
				
				//set the ratebook back to old one
				this.context.putValue(ContextParam.RATEBOOK,priorRatebook);
				
				return true;
			}
		}
		return false;
	}
	
	private String[] getProgramReference(String refXpath){
		String[] retArray = new String[2];
		String xpathCorrected = refXpath.replaceAll("(\\[).[^\\]]*(\\])", "");
		
//		System.out.println("XPATH corrected " + xpathCorrected);
//		System.out.println("XPATH array " + xpathCorrected.split("/").length);
		
		String[] xArray = xpathCorrected.split("/");
		
		
		if(xArray.length > 2){
//			System.out.println("Reference - " + xArray[xArray.length-2]);
			retArray[0] = xArray[xArray.length-2];
		}else{
			RatingEntity entity = (RatingEntity) this.context.getValue(ContextParam.ENTITY);
			retArray[0] = entity.getName() ;
		}
//		System.out.println("Program - " + xArray[xArray.length-1]);
		retArray[1] = xArray[xArray.length-1];
		
		return retArray;
	}
	
	private boolean isBlockExecuted(String reference){
		
		ProgramCallLog prgCall = (ProgramCallLog) this.context
				.getValue(ContextParam.PROGRAMCALLLOG);
		if(logger.isInfoEnabled()){
			logger.info("reference " + reference + " returns " + prgCall.isCalled(reference));
		}
		return prgCall.isCalled(reference);
	}
	
	private void clearLocalVariables() {
//		@SuppressWarnings("unchecked")
//		Map<String, Variable> vars = (Map<String, Variable>) this.context
//				.getValue(ContextParam.LOCALVARIABLES);
//
//		JXPathContext jxpcontext = (JXPathContext) context
//				.getValue(ContextParam.JXPATHCONTEXT);
//
//		for (String var : vars.keySet()) {
//			if (jxpcontext.getVariables().isDeclaredVariable(var)) {
//				if (logger.isInfoEnabled()) {
//					logger.info("Remove Variable -> " + var);
//				}
//				jxpcontext.getVariables().undeclareVariable(var);
//			}
//		}
	}
	
	private boolean isObjectPresent(Node pointer){
		if(pointer != null){
			return true;
		}
		
		return false;
	}
	
	
	
	private Product getProduct(Node referenceNode) {
		String productIDNodeName = (String) context.getValue(ContextParam.PRODUCTIDNODENAME);
		
//		System.out.println("ProductIDNodeName " + productIDNodeName);
		
		String ratebookid = (String) referenceNode.valueOf(productIDNodeName);

		if (ratebookid != null && !ratebookid.equals("")) {
			
			if(logger.isInfoEnabled()){
				logger.info("We got the Ratebook as " + ratebookid + " at " + referenceNode.getUniquePath());
			}
			
			Product rb = new Product();
			rb.setId(ratebookid);
			
//			ObjectDAO bd = new ProductDAO(rb);
			BaseDAO bd = RatingDAOFactory.getRatingDAOFactory().getProductDAO(rb);
			
			rb = (Product) bd.getUniqueRecord(context
					.getValue(ContextParam.SESSION));
			return rb;
		} else {
			return (Product) context.getValue(ContextParam.RATEBOOK);
		}

	}
	
	private Program getRatingProgram(String entityName , String programName, Product currentRatebook, Process executingProcess, Object session){
		//we should get the ratebook which has the rating algorithm
		//so go till the root ratebook to get the algorithm
		
		
		if(logger.isInfoEnabled()){
			logger.info("******************************* we are in getRatingProgram ********************************");
		}
		
		if(currentRatebook == null){
			return null;
		}
		
		RatingEntity entity = new RatingEntity();
		entity.setName(entityName);
		entity.setProduct(currentRatebook);
		
		if(entity.getName().equals("")){
			String rootNodeName = (String) context.getValue(ContextParam.ROOTNODENAME);
			entity.setName(rootNodeName);
		}
		
//		ObjectDAO ratingEntityDAO = new RatingEntityDAO(entity);
		BaseDAO ratingEntityDAO = RatingDAOFactory.getRatingDAOFactory().getRatingEntityDAO(entity);
		
		entity = ratingEntityDAO.getUniqueRecord(session);

		if(entity == null){
			if(logger.isInfoEnabled()){
				logger.info("Could not find the rating entity [ " + entityName + " ] definition in database ");
			}
			return null;
		}
		
		Process ratingProcess = new Process();
		ratingProcess.setName(executingProcess.getName());
		ratingProcess.setExecutionorder(executingProcess.getExecutionorder());
		ratingProcess.setEntity(entity);

//		ObjectDAO processDAO = new ProcessDAO(ratingProcess);
		BaseDAO processDAO = RatingDAOFactory.getRatingDAOFactory().getProcessDAO(ratingProcess);
		
		ratingProcess = processDAO.getUniqueRecord(session);

		if(logger.isInfoEnabled()){
			logger.info("Rating process " + ratingProcess.getId());
		}
		Program ratingProgram = new Program();
		ratingProgram.setName(programName);
		ratingProgram.setProcess(ratingProcess);
		
//		ObjectDAO progBaseDAO = new ProgramDAO(ratingProgram);
		BaseDAO progBaseDAO = RatingDAOFactory.getRatingDAOFactory().getProgramDAO(ratingProgram);

		ratingProgram = progBaseDAO.getUniqueRecord(session);
		
//		if(ratingProgram.getName() != null && !ratingProgram.getName().equals(""))
		if(ratingProgram != null)
		{
			return ratingProgram;
		} else{
			BaseDAO bd = RatingDAOFactory.getRatingDAOFactory().getProductDAO(currentRatebook.getParentRefId());
			Product parentProduct = bd.getUniqueRecord(session);
			
			return getRatingProgram(entityName, programName, parentProduct, executingProcess, session);
		}
	}
	
}
