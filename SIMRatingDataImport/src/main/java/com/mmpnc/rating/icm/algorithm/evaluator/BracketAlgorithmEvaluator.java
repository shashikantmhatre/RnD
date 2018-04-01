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

public class BracketAlgorithmEvaluator implements AlgorithmEvaluator {

	private Context context;
	private AlgorithmEvaluator expression;

	public BracketAlgorithmEvaluator(AlgorithmEvaluator expression) {
		this.expression = expression;
	}

	@Override
	public StringBuffer evaluate() {
		
		StringBuffer buffer = new StringBuffer();
		
		buffer.append(AlgoConstant.LeftRoundBracket);
		expression.setContext(context);
		buffer.append(expression.evaluate()).append(" ");
		buffer.append(AlgoConstant.RightRoundBracket);
		
		System.out.println(" BracketAlgorithmEvaluator returns " + buffer);
		
		
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
