package com.mmpnc.rating.icm.algorithm.evaluator;

import java.util.List;
//import java.util.Map;

//import org.dom4j.Node;

import com.mmpnc.context.Context;
import com.mmpnc.context.ContextParam;
//import com.mmpnc.context.ContextParam;
import com.mmpnc.rating.icm.algorithm.AlgoConstant;
import com.mmpnc.rating.icm.algorithm.AlgorithmConfigurator;
import com.mmpnc.rating.icm.algorithm.AlgorithmEvaluator;
import com.mmpnc.rating.icm.algorithm.util.ContentCorrection;
import com.mmpnc.rating.icm.algorithm.vo.DataValue;
import com.mmpnc.rating.vo.Process;
import com.mmpnc.rating.vo.Product;
//import com.mmpnc.rating.vo.Step;
//import com.mmpnc.rating.iso.content.AlgorithmContentParser;

/**
 * Author : Shashikant Mhatre
 *
 * Created On : Oct 12, 2013
 *
 * Description :
 *
 * Note :
 */

public class ExpressionAlgorithmEvaluator implements AlgorithmEvaluator {

	private Context context;
	private List<AlgorithmEvaluator> keyvalues;
	private List<AlgorithmEvaluator> expressions;

	public ExpressionAlgorithmEvaluator(List<AlgorithmEvaluator> keyvalues,
			List<AlgorithmEvaluator> expressions) {
		this.keyvalues = keyvalues;
		this.expressions = expressions;
	}

//	@SuppressWarnings("unchecked")
	@Override
	public Object evaluate() {
		StringBuffer buffer = new StringBuffer();
		String variableType = "";
		String operation = "";
		String ruleReference = "";
		String value = "";
		
		
		//TODO we have to define this

		for(AlgorithmEvaluator keyvalue : this.keyvalues){
			keyvalue.setContext(context);
			DataValue dv = (DataValue) keyvalue.evaluate();
			
			if(dv.getKey().equals("op")){
				if(!dv.getValue().equals("")){
					operation = ContentCorrection.updateOperator(dv.getValue());
				}				
			} else if(dv.getKey().equals("ruleReference")){
				if(!dv.getValue().equals("")){
					ruleReference = dv.getValue();
				}
			} else if(dv.getKey().equals("value")){
				if(!dv.getValue().equals("")){
					value = dv.getValue().trim().replaceAll("^'|'$", "\"");
				}
			} else if(dv.getKey().equals("variableType")){
				if(!dv.getValue().equals("")){
					variableType = dv.getValue();
				}
			}			
		}
		
	
		//this will reset the VariableType for domainType value
		if(value.contains("{")){
			variableType = "";
			value = decreptFunction(value);
		}
		
//		if(value.contains("Count") || value.contains("Sum") || value.contains("::")){
//			System.out.println("We have xml Function " + value);
//		}
		
				
		if(expressions != null && expressions.size() > 0 ){
			//TODO this is only applicable when expression is an operation
		
			if(expressions.size() == 1 && ( operation != null && !operation.equals(""))){
				if(operation.equals("bracket")){
					buffer.append(" ").append(AlgoConstant.LeftRoundBracket);
				} else {
					buffer.append(" ").append(operation).append(AlgoConstant.LeftRoundBracket);
				}
			} else if (expressions.size() >= 1 && ( operation != null && operation.equals("-"))){
				buffer.append(" ").append(AlgoConstant.LeftRoundBracket);
			}
			
			int count = 1;
//			System.out.println("expression count " + expressions.size());
			for(AlgorithmEvaluator expression : expressions){
				
				expression.setContext(context);
				buffer.append(expression.evaluate());

				count++;
				
//				System.out.println("Buffer ---> " + buffer);
				
				if(expressions.size() > 1 && count%2 == 0){
					buffer.append(AlgoConstant.Space).append(operation).append(AlgoConstant.Space);
				}
				
			}
			
			if(expressions.size() == 1 && ( operation != null && !operation.equals(""))){
				buffer.append(AlgoConstant.RightRoundBracket);
			} else if (expressions.size() >= 1 && ( operation != null && operation.equals("-"))){
				buffer.append(" ").append(AlgoConstant.RightRoundBracket);
			}
			
		} else{
			if(!variableType.equals("") && !ContentCorrection.ignoreVariableType(variableType) ) {
					
					if(variableType.startsWith("XPATH")){
						buffer.append(variableType).append(AlgoConstant.Colon).append(AlgoConstant.DoubleQuote).append(value).append(AlgoConstant.DoubleQuote);
					} else {
						
						//search and process the local variable
						if(ContentCorrection.isLocalVariabletype(variableType)){
							
							ContentCorrection.searchAndProcessLocalVariable(context,value);
							
							/*
							 
							Node currentProcess = (Node) context.getValue(ContextParam.CURRENTPROCESS);
							String currentProcessName = currentProcess.valueOf("@name");
							
							if(!(currentProcessName != null && currentProcessName.equals(value))){
								Map<String,String> processedBlock = (Map<String, String>) context.getValue(ContextParam.PROCESSEDBLOCK);
								
								Node parentProcess = (Node) context.getValue(ContextParam.PARENTPROCESS);
								String parentProcessName = parentProcess.valueOf("@name");
								
								StringBuffer programPath = new StringBuffer(); 
								programPath.append(context.getValue(ContextParam.SCOPE)).append("/");
								programPath.append(parentProcessName).append("/");
								programPath.append(value);
								
//								System.out.println("Put this in processedblock " + programPath);
								
								if(!processedBlock.containsKey(programPath.toString())){
									processedBlock.put(programPath.toString(), programPath.toString());
									
//									System.out.println("we are again processing " + programPath);
									
									// get the prior PCH steps and add it to the current process steps.
									List<Step> pchSteps =  AlgorithmContentParser.searchAndProcess(value, context);
									
									List<Step> localPCHSteps = (List<Step>) context.getValue(ContextParam.PCHSTEPS);
									localPCHSteps.addAll(pchSteps);
								}									
							}
							
							*/
						}
						
						buffer.append(variableType).append(AlgoConstant.Colon).append(value);
					}
					
			} else if (!ruleReference.equals("")){
				buffer.append(AlgoConstant.xpath).append(AlgoConstant.Colon).append(AlgoConstant.DoubleQuote).append(value).append(AlgoConstant.DoubleQuote);
			} else {
				buffer.append(ContentCorrection.updateContent(value));
			}
		}
		
		return buffer; 
	}

	private String decreptFunction(String value) {
//		System.out.println("function decrept ");
		
		String functionName = value.substring(0, value.indexOf("{")).trim();
//		System.out.println("Function Name : " + functionName);

		
		AlgorithmConfigurator config = (AlgorithmConfigurator) context.getValue(ContextParam.CONFIGURTOR);
		
		//if we found a statement which is a pch program instead of domain table and rate table.
		//get that PCH block and put statements in the current pch.
		if(config.getProgramPCH().matchProgramName(value))
		{
			System.out.println("We matched the Program in decreptFunction " + functionName);
			ContentCorrection.searchAndProcessLocalVariable(this.context,functionName.trim());
		}		
		
		String arguments = value.substring(value.indexOf("{") + 1,value.indexOf("}"));
//		System.out.println("arguments : " + arguments);
		
		String[] args = arguments.split(",");
		
		StringBuffer buffer = new StringBuffer();
		
		if(functionName.startsWith("Lookup")){
			buffer.append(functionName);
		} else {
			buffer.append(functionName).append(AlgoConstant.LeftRoundBracket);
			boolean flag = false;
			for(String arg : args){
				if(flag){
					buffer.append(AlgoConstant.Comma);
				}
				
				arg = arg.trim().replaceAll("^'|'$", "\"");
				
				if(arg.contains("/")){
					if(arg.startsWith("\"")){
						buffer.append(AlgoConstant.xpath).append(AlgoConstant.Colon).append(arg);
					}else{
						buffer.append(AlgoConstant.xpath).append(AlgoConstant.Colon).append(AlgoConstant.DoubleQuote).append(arg).append(AlgoConstant.DoubleQuote);
					}
				} else {
					buffer.append(arg);
				}
				
				flag = true;
			}
			
			buffer.append(AlgoConstant.RightRoundBracket);
		}
		
		return buffer.toString();		
	}

	@Override
	public void setContext(Context context) {
		this.context = context;
	}

	@Override
	public void setProduct(Product product) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setProcess(Process process) {
		// TODO Auto-generated method stub
		
	}
	
}
