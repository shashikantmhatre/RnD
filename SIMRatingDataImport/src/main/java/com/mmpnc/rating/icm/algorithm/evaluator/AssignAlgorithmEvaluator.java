package com.mmpnc.rating.icm.algorithm.evaluator;

import java.util.List;

import com.mmpnc.context.Context;
import com.mmpnc.context.ContextParam;
import com.mmpnc.rating.icm.algorithm.AlgoConstant;
import com.mmpnc.rating.icm.algorithm.AlgorithmEvaluator;
import com.mmpnc.rating.icm.algorithm.util.ContentCorrection;
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

public class AssignAlgorithmEvaluator implements AlgorithmEvaluator {

	private List<AlgorithmEvaluator> keyvalues;
	private AlgorithmEvaluator expression;
	private Context context;
	
	public AssignAlgorithmEvaluator(List<AlgorithmEvaluator> keyvalues, AlgorithmEvaluator expression) {
		this.keyvalues = keyvalues;
		this.expression = expression;
	}

	@Override
	public StringBuffer evaluate() {

		StringBuffer buffer = new StringBuffer();
		String variableType = "";
		String leftValue = "";
		
		int spaceCount = (Integer) this.context.getValue(ContextParam.SPACECOUNT);
		
		if((Boolean) context.getValue(ContextParam.FORMAT)){
			AlgorithmUtil.appendSpace(buffer, spaceCount);
		}
		
		for(AlgorithmEvaluator keyvalue : this.keyvalues){
			keyvalue.setContext(context);
			DataValue dv = (DataValue) keyvalue.evaluate();
			
			if(dv.getKey().equals("variable")){
				if(!dv.getValue().equals("")){					
					leftValue = dv.getValue().trim();
				}				
			} else if(dv.getKey().equals("variableType")){
				if(!dv.getValue().equals("")){
					variableType = dv.getValue();
				}
			}			
		}
		
		if(!variableType.equals("") && !ContentCorrection.ignoreVariableType(variableType) ) {
			
			if(variableType.startsWith("XPATH")){
				buffer.append(variableType).append(AlgoConstant.Colon).append(AlgoConstant.DoubleQuote).append(leftValue).append(AlgoConstant.DoubleQuote);
			} else {
				buffer.append(variableType).append(AlgoConstant.Colon).append(leftValue);
			}
			
		} else {
			
			if(leftValue.contains("/")){
				
				if(leftValue.startsWith("\"")){
					buffer.append(AlgoConstant.xpath).append(AlgoConstant.Colon);
					buffer.append(leftValue);
				} else {
					buffer.append(AlgoConstant.xpath).append(AlgoConstant.Colon);
					buffer.append(AlgoConstant.DoubleQuote);
					buffer.append(leftValue);
					buffer.append(AlgoConstant.DoubleQuote);
				}
				
			} else{				
				
				buffer.append(ContentCorrection.updateContent(leftValue));
			
			}
		}
			
		buffer.append(" = ");
		
		expression.setContext(context);
		buffer.append(expression.evaluate()).append(" ");
		
		if((Boolean) context.getValue(ContextParam.FORMAT)){
			buffer.append(AlgoConstant.NewLine);
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
