package com.mmpnc.rating.icm.algorithm.evaluator;

import java.util.List;

import com.mmpnc.context.Context;
import com.mmpnc.rating.icm.algorithm.AlgoConstant;
import com.mmpnc.rating.icm.algorithm.AlgorithmEvaluator;
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

public class FunctionAlgorithmEvaluator implements AlgorithmEvaluator {

	private AlgorithmEvaluator keyvalue;
	private List<AlgorithmEvaluator> args;
	private Context context;

	public FunctionAlgorithmEvaluator(AlgorithmEvaluator keyvalue,
			List<AlgorithmEvaluator> args) {
		this.keyvalue = keyvalue;
		this.args = args;
	}

	@Override
	public Object evaluate() {
		StringBuffer buffer = new StringBuffer();
		
		keyvalue.setContext(context);
		DataValue dv = (DataValue) keyvalue.evaluate();
		buffer.append(dv.getValue().trim());
		buffer.append(AlgoConstant.LeftRoundBracket);
				
		int count = 1;
		for(AlgorithmEvaluator arg : args){
			if(count > 1){
				buffer.append(AlgoConstant.Comma);
			}
			arg.setContext(context);
			buffer.append(arg.evaluate());
			count++;
		}
		
		buffer.append(AlgoConstant.RightRoundBracket);
		
		
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
