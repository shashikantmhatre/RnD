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

public class VarEvaluator implements Evaluator {
	private String variable;
	
	public VarEvaluator(String constant, String variable) {
		this.variable = variable;
	}

	@Override
	public void setContext(Context context) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object evaluate() {
		System.out.println(variable);
		return null;
	}

}
