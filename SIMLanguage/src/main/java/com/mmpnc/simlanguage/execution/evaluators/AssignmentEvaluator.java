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

public class AssignmentEvaluator implements Evaluator {
	private Evaluator right;
	
	public AssignmentEvaluator(Context context, Evaluator left, Evaluator right) {
		// TODO Auto-generated constructor stub
		this.right = right;
	}

	@Override
	public void setContext(Context context) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object evaluate() {
		right.evaluate();
		return null;
	}

}
