package com.mmpnc.rating.algorithm.execute.evaluators;

import java.math.BigDecimal;

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

public class AssignmentEvaluator implements Evaluator {
	
	private Context context;
	private Evaluator left;
	private Evaluator right;
	private static final Logger logger = LoggerFactory
			.getLogger(AssignmentEvaluator.class);
	
	public AssignmentEvaluator(Context context, Evaluator left, Evaluator right) {
		this.context = context;
		this.left = left;
		this.right = right;
	}

	@Override
	public void setContext(Context context) {
		this.context = context;
	}

	@Override
	public Object evaluate() {

		Node currentNode = (Node) context.getValue(ContextParam.CURRENTNODE);

		if (logger.isInfoEnabled()) {
			logger.info("Current XPATH Pointer at "
					+ currentNode.getUniquePath());
		}

		left.setContext(context);
		String xPath = (String) left.evaluate();

		right.setContext(context);
		Object val = right.evaluate();

		if (right instanceof StringEvaluator) {
			if (logger.isInfoEnabled()) {
				logger.info("Assign value at " + xPath
						+ " as [StringEvaluator] " + val);
			}

			
			instanciateVariableElement(currentNode, xPath, "String", ((String) val).replaceAll("^'|'$", ""));

		} else {

				if (val instanceof String) {

					String xPathValue = currentNode.valueOf((String) val);

					if (logger.isInfoEnabled()) {
						logger.info("Assign String Value at "
								+ xPath
								+ " as "
								+ xPathValue
								+ " [ "
								+ val
								+ " ] of type "
								+ (xPathValue != null ? xPathValue.getClass()
										.getSimpleName() : null));
					}
					
					instanciateVariableElement(currentNode, xPath, "Object", xPathValue);

				} else if (val instanceof BigDecimal){
					instanciateVariableElement(currentNode, xPath, "BigDecimal", ((BigDecimal)val).toString());
				} else {
					instanciateVariableElement(currentNode, xPath, "", val.toString());
				}
			
		}
		return null;
	}
	
	
	private Element instanciateVariableElement(Node currentNode, String variable, String variableType, String defaultValue){
		Element variableElement = (Element) currentNode
				.selectSingleNode(variable);

		if (variableElement == null) {
			
			if(logger.isInfoEnabled()){
				logger.info("We did not get the " + variable + " in the xml, for assignment we will create it! ");
			}
			
			
			
			if(variable.startsWith("/")){
				System.out.println("we are in process to create the element from root path");
				String basePath = "";
				String nodePath = "";
				
				if(variable.replaceAll("(\\[).[^\\]]*(\\])", "").contains("/")){
					basePath = variable.substring(0, variable.lastIndexOf("/"));
					nodePath = variable.substring(variable.lastIndexOf("/") + 1);
				} 
				
				Node rootNode = currentNode.getDocument().getRootElement();
				Element parentElement = (Element) rootNode
						.selectSingleNode(basePath);
				variableElement = parentElement.addElement(nodePath);
				variableElement.setText(defaultValue);
				
			} else {
				variableElement = ((Element) currentNode).addElement(variable);
				variableElement.setText(defaultValue);
			}
			
		} else{
			variableElement.setText(defaultValue);
			
		}
		
		return variableElement;
	}

}
