package com.mmpnc.simlanguage.execution.evaluators;

import com.mmpnc.context.Context;
import com.mmpnc.simlanguage.Evaluator;

/**
 * Author : Shashikant Mhatre
 *
 * Created On : Oct 12, 2013
 *
 * Description :
 *
 * Note :
 */

public class MinusEvaluator implements Evaluator {
	private Evaluator left;
	private Evaluator right;
	
	public MinusEvaluator(Evaluator left, Evaluator right) {
		this.left = left;
		this.right = right;
	}

	@Override
	public void setContext(Context context) {
		
	}

	@Override
	public Object evaluate() {
		left.evaluate();
		right.evaluate();
		return null;
	}

}
