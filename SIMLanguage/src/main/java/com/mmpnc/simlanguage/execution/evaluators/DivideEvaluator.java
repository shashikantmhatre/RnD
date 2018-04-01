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

public class DivideEvaluator implements Evaluator {
	private Evaluator left;
	private Evaluator right;
	
	public DivideEvaluator(Evaluator left, Evaluator right) {
		this.left = left;
		this.right = right;
	}

	@Override
	public void setContext(Context context) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object evaluate() {
		left.evaluate();
		right.evaluate();
		return null;
	}

}
