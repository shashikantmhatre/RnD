package com.mmpnc.rating.icm.algorithm.evaluator;

import java.util.List;

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

public class ErrorEvaluator implements AlgorithmEvaluator {
	private Context context;
	private List<AlgorithmEvaluator> keyValues;
	
	public ErrorEvaluator(List<AlgorithmEvaluator> keyValues) {
		this.keyValues = keyValues;
	}

	@Override
	public Object evaluate() {
		StringBuffer buffer = new StringBuffer();
		String variables = "";
		int spacecount = (Integer) context.getValue(ContextParam.SPACECOUNT);
		
		String parentXpath = (String) context.getValue(ContextParam.PARENTXPATH);
		
		for(AlgorithmEvaluator keyvalue : this.keyValues){
		
			keyvalue.setContext(context);
			DataValue dv = (DataValue) keyvalue.evaluate();
			
			if(dv.getKey().equals("variables")){
				if(!dv.getValue().equals("")){
					variables = dv.getValue().trim();
				}
			}
			
		}
		
		for(String process : variables.split(",")){
			AlgorithmUtil.appendSpace(buffer, spacecount);
			buffer.append("Error (XPATH:\"").append(parentXpath).append("/").append(process.trim()) .append("\")").append(AlgoConstant.NewLine);
		}
				
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

}
