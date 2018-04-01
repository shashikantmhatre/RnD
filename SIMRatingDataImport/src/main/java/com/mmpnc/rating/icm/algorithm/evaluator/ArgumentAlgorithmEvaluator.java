package com.mmpnc.rating.icm.algorithm.evaluator;

import java.util.List;

import com.mmpnc.context.Context;
import com.mmpnc.rating.icm.algorithm.AlgoConstant;
import com.mmpnc.rating.icm.algorithm.AlgorithmEvaluator;
import com.mmpnc.rating.icm.algorithm.util.ContentCorrection;
import com.mmpnc.rating.icm.algorithm.vo.Argument;
import com.mmpnc.rating.icm.algorithm.vo.DataValue;
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

public class ArgumentAlgorithmEvaluator implements AlgorithmEvaluator {

	private Context context;
	private List<AlgorithmEvaluator> keyvalues;

	public ArgumentAlgorithmEvaluator(List<AlgorithmEvaluator> keyvalues) {
		this.keyvalues = keyvalues;
	}

	@Override
	public Object evaluate() {
		StringBuffer buffer = new StringBuffer();
		
		Argument arg = new Argument();
		
		for(AlgorithmEvaluator keyvalue : this.keyvalues){
			keyvalue.setContext(context);
			DataValue dv = (DataValue) keyvalue.evaluate();
			
			if(dv.getKey().equals("number")){
				if(!dv.getValue().equals("")){
					arg.setSequence(Integer.parseInt(dv.getValue()));
				}				
			} else if(dv.getKey().equals("variableType")){
				if(!dv.getValue().equals("")){
					arg.setType(dv.getValue());
				}				
			} if(dv.getKey().equals("value")){
				if(!dv.getValue().equals("")){
					arg.setValue(dv.getValue().replaceAll("^'|'$", "\""));
				}				
			} 
		}
		
//		if(arg.getValue().contains("/")){
//			System.out.println("We have xml path in function call " + arg.getValue());
//		}
		
		if(arg.getType() != null && !ContentCorrection.ignoreVariableType(arg.getType()) ){
			if(arg.getType().startsWith("XPATH")){
				buffer.append(arg.getType()).append(AlgoConstant.Colon).append(AlgoConstant.DoubleQuote).append(arg.getValue()).append(AlgoConstant.DoubleQuote);
			} else {
				buffer.append(arg.getType()).append(":").append(arg.getValue());
			}
		} else {
			if(arg.getValue().contains("/")){
				
				if(arg.getValue().startsWith("\"")){
					buffer.append(AlgoConstant.xpath).append(AlgoConstant.Colon);
					buffer.append(arg.getValue());
				} else {
					buffer.append(AlgoConstant.xpath).append(AlgoConstant.Colon);
					buffer.append(AlgoConstant.DoubleQuote);
					buffer.append(arg.getValue());
					buffer.append(AlgoConstant.DoubleQuote);
				}
			} else if (arg.getValue().trim().startsWith("Int32.Parse")) {
				buffer.append("number").append(AlgoConstant.LeftRoundBracket);
				buffer.append(arg.getValue().trim().replace("Int32.Parse", ""));
				buffer.append(AlgoConstant.RightRoundBracket);
				
			} else {				
				if(ContentCorrection.isLocalVariableType(this.context,arg.getValue()))
				{
					ContentCorrection.searchAndProcessLocalVariable(context,arg.getValue());
					
					buffer.append(AlgoConstant.LocalValue).append(AlgoConstant.Colon).append(arg.getValue());
				} else {
					buffer.append(arg.getValue());
				}
			}
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
