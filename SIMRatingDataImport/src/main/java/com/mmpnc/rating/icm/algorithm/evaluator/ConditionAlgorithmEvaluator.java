package com.mmpnc.rating.icm.algorithm.evaluator;

import com.mmpnc.context.Context;
import com.mmpnc.rating.icm.algorithm.AlgoConstant;
import com.mmpnc.rating.icm.algorithm.AlgorithmEvaluator;
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

public class ConditionAlgorithmEvaluator implements AlgorithmEvaluator {

	private AlgorithmEvaluator expression;
	private Context context;

	public ConditionAlgorithmEvaluator(AlgorithmEvaluator expression) {
		this.expression = expression;
	}

	@Override
	public Object evaluate() {
		StringBuffer buffer = new StringBuffer();
		
		buffer.append(AlgoConstant.LeftRoundBracket);
		expression.setContext(context);
		buffer.append(expression.evaluate());
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
