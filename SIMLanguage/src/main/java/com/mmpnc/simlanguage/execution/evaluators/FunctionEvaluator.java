package com.mmpnc.simlanguage.execution.evaluators;

import java.util.List;

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

public class FunctionEvaluator implements Evaluator {
	
	private List<Evaluator> evallist;
	
	public FunctionEvaluator(String constant, String functionName,
			List<Evaluator> evallist) {
		this.evallist = evallist;
	}

	@Override
	public void setContext(Context context) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object evaluate() {
		for(Evaluator eval : evallist){
			eval.evaluate();
		}
		
		return null;
	}

}
