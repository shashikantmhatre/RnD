package com.mmpnc.rating.algorithm.execute.evaluators;

import org.dom4j.Element;
import org.dom4j.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mmpnc.context.Context;
import com.mmpnc.context.ContextParam;
import com.mmpnc.simlanguage.Evaluator;

/**
 * Author : Shashikant Mhatre
 *
 * Created On : Oct 12, 2013
 *
 * Description :
 *
 * Note :
 */

public class AttachProgramEvaluator implements Evaluator {

	private Context context;
	private String programName;
	private Evaluator xpathEvaluator;
	
	private static final Logger logger = LoggerFactory
			.getLogger(ProgramEvaluator.class);

	public AttachProgramEvaluator(Context context, String programName, Evaluator xpathEvaluator) {
		this.context = context;
		this.programName = programName;
		this.xpathEvaluator = xpathEvaluator;
	}

	@Override
	public void setContext(Context context) {
		this.context = context;
	}

	@Override
	public String evaluate() {
		
		
		
		if(logger.isInfoEnabled()){
			logger.info("XXXXXXXXXXXX ---- We are in attach program call -----XXXXXXXXX");
		}
		
		if(this.programName != null ){
			
			if (logger.isInfoEnabled()) {
				logger.info("ProgramEvaluator.evaluate Call for "
						+ this.programName);
			}

			System.out.println("Missing Implementation **************************************");
		
		} else {
			this.xpathEvaluator.setContext(context);
			String xpathValue = (String) this.xpathEvaluator.evaluate();
			
			if(logger.isInfoEnabled()){
				logger.info("Corrected XPATH in Attach Program [ " + xpathValue + " ]" );
			}
			
			String basePath = "";
			String nodePath = "";
			
			if(xpathValue.replaceAll("(\\[).[^\\]]*(\\])", "").contains("/")){
				basePath = xpathValue.substring(0, xpathValue.lastIndexOf("/"));
				nodePath = xpathValue.substring(xpathValue.lastIndexOf("/") + 1).trim();
			} 
			
			Node currentNote = (Node) this.context.getValue(ContextParam.CURRENTNODE);
			
			Node rootNode = currentNote.getDocument().getRootElement();
			
			Element selectedElement =  (Element) rootNode.selectSingleNode(basePath);

			if(logger.isInfoEnabled()){
				logger.info("XPath of selectedElement in AttacgProgram.evaluate is "+ selectedElement.getUniquePath());
			}
			selectedElement.addElement(nodePath);
			
//			Element variableElement = (Element) currentNode.selectSingleNode(variable);
			
		}
		
		
		return null;
	}

}
