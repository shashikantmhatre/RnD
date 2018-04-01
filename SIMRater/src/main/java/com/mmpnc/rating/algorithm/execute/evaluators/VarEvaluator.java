package com.mmpnc.rating.algorithm.execute.evaluators;

import java.util.Map;

import org.dom4j.Element;
import org.dom4j.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mmpnc.context.Context;
import com.mmpnc.context.ContextParam;
import com.mmpnc.rating.algorithm.Variable;
import com.mmpnc.rating.algorithm.util.ProcessBlockExecutor;
import com.mmpnc.simlanguage.Evaluator;
//import com.mmpnc.rating.algorithm.util.ProcessBlockExecutor;

/**
 * Author : Shashikant Mhatre
 *
 * Created On : Oct 12, 2013
 *
 * Description :
 *
 * Note :
 */

public class VarEvaluator implements Evaluator {

	private String constant;
	private String variable;
	private Context context;
	private Node currentNode;
	
	private static final Logger logger = LoggerFactory
			.getLogger(VarEvaluator.class);

	public VarEvaluator(String constant, String variable) {
		this.constant = constant;
		this.variable = variable;
	}

	@Override
	public void setContext(Context context) {
		this.context = context;
	}

	@Override
	public String evaluate() {
		StringBuffer retValue = new StringBuffer();
		this.currentNode = (Node) this.context
				.getValue(ContextParam.CURRENTNODE);

		if (logger.isInfoEnabled()) {
			logger.info("Base XPath in VarEvaluate.evaluate is "
					+ this.currentNode.getUniquePath());
			logger.info("value in varEvaluator is " + this.constant + " : " + this.variable);
		}

		if(this.constant == null && (this.variable.equals("null") || this.variable.equals("true") || this.variable.equals("false"))){
			
			if (logger.isInfoEnabled()) {
				logger.info("key words passed as variable : [null, true, false]");
			}
			
			if(this.variable.equals("true") || this.variable.equals("false")){
				retValue.append(this.variable).append("()");
			} else{
				retValue.append(this.variable);
			}
			
			return retValue.toString();
			
		}
		
		
		if (this.constant == null) {

			// comment below block if not working correctly.
			if (logger.isInfoEnabled()) {
				logger.info("Check if Program is available for variable "
						+ this.variable);
			}

			if (this.variable != null && ! (this.variable.equals("null") || this.variable.equals("true") || this.variable.equals("false"))) {
				
//				instanciateVariableElement(this.currentNode, this.variable,"","0");
				
				executeSubProgram(this.variable, this.currentNode.getUniquePath());
			}

			if(this.variable.equals("true") || this.variable.equals("false")){
				retValue.append(this.variable).append("()");
			} else{
				retValue.append(this.variable);
			}
			

		} else {

			if (logger.isInfoEnabled()) {
				logger.info("Get Value for : " + this.constant + " : "
						+ this.variable);
			}

			switch (getVarType(this.constant)) {
			case LV_NUMERIC:
			case LV_INTEGER:
			case LV_DOUBLE:

				instanciateVariableElement(this.currentNode, this.variable, "BigDecimal", "0", true);
				
				retValue.append(executeSubProgram(this.variable, this.currentNode.getUniquePath()));

				break;
			case LV_BOOLEAN:
				
				instanciateVariableElement(this.currentNode, this.variable, "Boolean", "true", true);
				
				retValue.append(executeSubProgram(this.variable, this.currentNode.getUniquePath()));
				break;
				
			case LV_STRING:

				instanciateVariableElement(this.currentNode, this.variable, "String", "", true);

				retValue.append(executeSubProgram(this.variable, this.currentNode.getUniquePath()));

				break;
			case LV_VALUE:
				
				instanciateVariableElement(this.currentNode, this.variable, "Object", "", true);
				
				retValue.append(executeSubProgram(this.variable, this.currentNode.getUniquePath()));
				
				break;
			default:
				System.out.println("Check this default implementation for " + this.constant);
				retValue.append(this.variable);
			}

		}

		if (logger.isInfoEnabled()) {
			logger.info("VarEvaluator.evaluate returns the value " + retValue);
		}

		return retValue.toString();
	}
	
	private void addVariable(String variableName, Variable var) {

		@SuppressWarnings("unchecked")
		Map<String, Variable> vars = (Map<String, Variable>) this.context
				.getValue(ContextParam.LOCALVARIABLES);

		vars.put(variableName, var);
	}
	
	private VarType getVarType(String type) {
		VarType var = null;
		try {
			var = VarType.valueOf(type);
		} catch (Exception _ex) {
			var = VarType.NA;
		}
		return var;
	}

	private enum VarType {
		COLUMN_NUMERIC("COLUMN_NUMERIC"), COLUMN_STRING("COLUMN_STRING"), UI(
				"UI"), LV_NUMERIC("LV_NUMERIC"), LV_INTEGER("LV_INTEGER"), LV_VALUE("LV_VALUE"), LV_DOUBLE("LV_DOUBLE"), LV_STRING("LV_STRING"), CONSTANT(
				"CONSTANT"), NA("NA"), LV_BOOLEAN("LV_BOOLEAN");

		VarType(String type) {
			this.type = type;
		}

		@SuppressWarnings("unused")
		String type;
	}
	
	private String executeSubProgram(String programName, String reference) {

		if (logger.isInfoEnabled()) {
			logger.info("Call the subprocess for " + programName);
		}

		
		 ProcessBlockExecutor subProc = new ProcessBlockExecutor(this.context);
		 
		 if(reference.matches(".*/$")){
			 subProc.executeBlockSteps(programName, reference + programName);
		 } else {
			 subProc.executeBlockSteps(programName, reference + "/" + programName);
		 }

		return  new StringBuffer("").append(programName).toString();
	}

	
	private Element instanciateVariableElement(Node currentNode, String variable, String variableType, String defaultValue, boolean isLocalVariable){
		Element variableElement = (Element) currentNode
				.selectSingleNode(variable);

		if (variableElement == null) {
//			System.out.println("we created element");
			variableElement = ((Element) currentNode).addElement(variable);
			
			//set the local variable attribute to true
			if(isLocalVariable){
				variableElement.addAttribute("TempVariable", "true");
			}
			
			variableElement.setText(defaultValue);
			if(!variableType.equals("")){
				addVariable(this.variable, new Variable(variable,variableType));
			}
		}
		
		return variableElement;
	}
	
}
