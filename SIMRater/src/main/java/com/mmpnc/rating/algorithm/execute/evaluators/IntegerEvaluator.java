package com.mmpnc.rating.algorithm.execute.evaluators;

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

public class IntegerEvaluator implements Evaluator {

	private String integer;
	
	public IntegerEvaluator(String string) {
		this.integer = string;
	}

	@Override
	public void setContext(Context context) {

	}

	@Override
	public Object evaluate() {
		return this.integer;
	}

}
