package com.mmpnc.rating.icm.algorithm.evaluator;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.Node;

import com.mmpnc.context.Context;
import com.mmpnc.context.ContextParam;
import com.mmpnc.rating.icm.algorithm.AlgoConstant;
import com.mmpnc.rating.icm.algorithm.AlgorithmEvaluator;
import com.mmpnc.rating.icm.algorithm.vo.DataValue;
import com.mmpnc.rating.icm.content.util.AlgorithmUtil;
import com.mmpnc.rating.vo.Process;
import com.mmpnc.rating.vo.Product;

/**
 * Author : Shashikant Mhatre
 *
 * Created On : Oct 12, 2013
 *
 * Description :
 *
 * Note :
 */

public class ClassEvaluator implements AlgorithmEvaluator {
	private Context context;
	private List<AlgorithmEvaluator> keyValues;
	private List<AlgorithmEvaluator> childNodes;
	
	public ClassEvaluator(List<AlgorithmEvaluator> keyValues, List<AlgorithmEvaluator> childNodes) {
		this.keyValues = keyValues;
		this.childNodes = childNodes;
	}

	@Override
	public StringBuffer evaluate() {
		StringBuffer buffer = new StringBuffer();
		String name = "";
//		String type = "";
//		String pass = "";
		
		String parentXpath = (String) context.getValue(ContextParam.PARENTXPATH);
		int spacecount = (Integer) context.getValue(ContextParam.SPACECOUNT);
		
		String currentXpath = "";
		
		for(AlgorithmEvaluator keyvalue : this.keyValues){
			keyvalue.setContext(context);
			DataValue dv = (DataValue) keyvalue.evaluate();
			
			if(dv.getKey().equals("name")){
				if(!dv.getValue().equals("")){
					name = dv.getValue().trim();
				}
			}

//			if(dv.getKey().equals("type")){
//				if(!dv.getValue().equals("")){
//					type = dv.getValue().trim();
//				}
//			}
//
//			if(dv.getKey().equals("pass")){
//				if(!dv.getValue().equals("")){
//					pass = dv.getValue().trim();
//				}
//			}			
		}
		
		if(name.equals("Policy")){
			currentXpath = parentXpath;
		} else {
			
			if(parentXpath.equals("")){
				currentXpath = name;
			} else {
				currentXpath = parentXpath + "/" + name;
			}
			
		}
		
		boolean isMultiple = false;
		
		if(!currentXpath.equals("")){
			isMultiple = isMultipleInstance(context, currentXpath);
		}
		
		context.putValue(ContextParam.PARENTXPATH, currentXpath);
		
		if(isMultiple){
			AlgorithmUtil.appendSpace(buffer, spacecount);
			buffer.append("LOOP THROUGH \"").append(currentXpath).append("\"").append(AlgoConstant.NewLine);
		}

		context.putValue(ContextParam.SPACECOUNT, spacecount + 1);

		for(AlgorithmEvaluator childNode : this.childNodes){
			if((childNode instanceof ClassEvaluator || childNode instanceof RatingEvaluator) ){
				childNode.setContext(context);
				buffer.append(childNode.evaluate());
			}
		}
		
		if(!currentXpath.equals("")){
			AlgorithmUtil.appendSpace(buffer, spacecount + 1);
			buffer.append("Execute (XPATH:\"").append(currentXpath).append("/Premium\")").append(AlgoConstant.NewLine);
		}
		
		if(isMultiple){
			AlgorithmUtil.appendSpace(buffer, spacecount);
			buffer.append("END LOOP ").append(AlgoConstant.NewLine);
		}

		context.putValue(ContextParam.SPACECOUNT, spacecount);
		context.putValue(ContextParam.PARENTXPATH, parentXpath);
		
//		System.out.println(buffer);
		
		return buffer;
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
	
	private boolean isMultipleInstance(Context context, String classPath){
		Document dsDatabase = (Document) context.getValue(ContextParam.OBJECTDATABASE);
		Node dsRootNode = dsDatabase.getRootElement();

		String xmlQuery = "";

		xmlQuery = "//" + classPath + "/@multiple";
		
		String multipleValue = dsRootNode.valueOf(xmlQuery);
		
		return new Boolean(multipleValue);		
	}

}
