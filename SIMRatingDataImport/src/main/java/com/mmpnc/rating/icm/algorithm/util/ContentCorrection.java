package com.mmpnc.rating.icm.algorithm.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Node;

import com.mmpnc.context.Context;
import com.mmpnc.context.ContextParam;
import com.mmpnc.rating.icm.algorithm.AlgoConstant;
import com.mmpnc.rating.icm.algorithm.AlgorithmConfigurator;
import com.mmpnc.rating.vo.Step;
import com.mmpnc.rating.iso.content.AlgorithmContentParser;
import com.mmpnc.rating.iso.content.VariableType;

/**
 * Author : Shashikant Mhatre
 *
 * Created On : Oct 12, 2013
 *
 * Description :
 *
 * Note :
 */

public class ContentCorrection {
	private static Map<String,String> specialFunctionMap;

	static{
		populateSpecialFunctionMap();
	}
	
	private static void populateSpecialFunctionMap() {
		specialFunctionMap = new HashMap<String, String>();
		specialFunctionMap.put("Round Up Dollar", "RoundDollar");
		specialFunctionMap.put("Round Up Hundreds", "RoundHundredth");
		specialFunctionMap.put("Round Up Thousands", "RoundThousandth");
		specialFunctionMap.put("Round Up HundredThousands",
				"RoundHundredThousandth");
		specialFunctionMap.put("Round Up Hundred Thousands",
				"RoundHundredThousandth");
		specialFunctionMap.put("Round Up Ten Thousands", "RoundTenThousandth");
		specialFunctionMap.put("Round Up TenThousands", "RoundTenThousandth");
		specialFunctionMap.put("Ceiling", "Ceiling");		
		specialFunctionMap.put("Not", "NOT");
		specialFunctionMap.put("&", "&&");
		
	}
	
	public static String updateContent(String content) {
		if (content.equals("Total Premium")) {
			return "Premium";
		} else if (content.contains(".Date") || content.contains(".Days")|| content.contains(".Month")|| content.contains(".Year")){
			StringBuffer buffer = new StringBuffer();
			buffer.append(AlgoConstant.xpath).append(AlgoConstant.Colon).append(AlgoConstant.DoubleQuote).append(content).append(AlgoConstant.DoubleQuote);
			return buffer.toString();
		}
		return content;
	}
	
	public static boolean ignoreVariableType(String varType) {
		VariableType variableType = VariableType.valueOf(varType);
		if (variableType != null
				&& (variableType.equals(VariableType.CONSTANT)
						|| variableType.equals(VariableType.COLUMN_STRING)
						|| variableType.equals(VariableType.COLUMN_NUMERIC)
						|| variableType.equals(VariableType.UI)
						|| variableType.equals(VariableType.UI_NUMERIC) 
						|| variableType.equals(VariableType.UI_STRING))) {
			return true;
		}
		
		return false;
	}
	
	public static boolean isLocalVariabletype(String varType){
		VariableType variableType = VariableType.valueOf(varType);
		if(variableType != null
				&& (variableType.equals(VariableType.LV_VALUE)
				|| variableType.equals(VariableType.LV_INTEGER) 
				|| variableType.equals(VariableType.LV_NUMERIC) 
				|| variableType.equals(VariableType.LV_DOUBLE) 
				|| variableType.equals(VariableType.LV_STRING) 
				|| variableType.equals(VariableType.LV_BOOLEAN) 
				|| variableType.equals(VariableType.LV_DATE) 
				|| variableType.equals(VariableType.LV_TIMESPAN))){
			return true;
		} 		
		return false;
	}
	
	public static String updateOperator(String op) {
		
		if (specialFunctionMap.containsKey(op)){
			return specialFunctionMap.get(op);
		} else {
			return op.replaceAll("\"", "'");
		}
	}

	
	public static boolean isLocalVariableType(Context context, String variable){
		String scope = (String) context.getValue(ContextParam.SCOPE);
		Document dsDatabase = (Document) context.getValue(ContextParam.OBJECTDATABASE);
		
		if(variable.matches("^(\"|[0-9]).*") || variable.trim().contains(".")){
			return false;
		} else if(isPresent(dsDatabase, scope, variable)){
			return false;
		}else{
			return true;
		}
	}
	
	private static boolean isPresent(Document dsDatabase,String scope, String attribute){
		Node dsRootNode = dsDatabase.getRootElement();

		StringBuffer qry = new StringBuffer();
		qry.append("//").append(scope).append("/").append(attribute);
		
		Node node = dsRootNode.selectSingleNode(qry.toString()) ;
		
		if(node != null && (node.getName().equals(attribute))){
			return true;
		}else{
			return false;
		}		
	}
	
	@SuppressWarnings("unchecked")
	public static void searchAndProcessLocalVariable(Context context, String programName){
		{
			
			Node currentProcess = (Node) context.getValue(ContextParam.CURRENTPROCESS);
			
			String currentProcessName;
			
			if(currentProcess != null){
				currentProcessName = currentProcess.valueOf("@name");
			} else {
				currentProcessName = "Premium";
			}
			
			if(!(currentProcessName != null && currentProcessName.equals(programName))){
				
				AlgorithmConfigurator config = (AlgorithmConfigurator) context.getValue(ContextParam.CONFIGURTOR);
				
				if(config.getIgnorePCH().isAvailable(programName.trim()))
				{
					System.out.println("PCH ignored - " + programName + " as this is marked in ignored PCH list ");
					return;
				}
				
				Map<String,String> processedBlock = (Map<String, String>) context.getValue(ContextParam.PROCESSEDBLOCK);
				
				Node parentProcess = (Node) context.getValue(ContextParam.PARENTPROCESS);
				String parentProcessName = parentProcess.valueOf("@name");
				
				StringBuffer programPath = new StringBuffer(); 
				programPath.append(context.getValue(ContextParam.SCOPE)).append("/");
				programPath.append(parentProcessName).append("/");
				programPath.append(programName);
				
//				System.out.println("Put this in processedblock " + programPath);
				
				if(!processedBlock.containsKey(programPath.toString())){
					processedBlock.put(programPath.toString(), programPath.toString());
					
//					System.out.println("Start processing " + programPath);
					
					// get the prior PCH steps and add it to the current process steps.
					List<Step> pchSteps =  AlgorithmContentParser.searchAndProcess(programName, context);
					
					List<Step> localPCHSteps = (List<Step>) context.getValue(ContextParam.PCHSTEPS);
					localPCHSteps.addAll(pchSteps);
				}									
			}
		}
	}
	
	
}
