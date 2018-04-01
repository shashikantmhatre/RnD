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

public class NotBooleanEvaluator implements Evaluator {

	private Context context;
	private Evaluator condition;
	
	public NotBooleanEvaluator(Evaluator bool) {
		this.condition = bool;
	}

	@Override
	public void setContext(Context context) {
		this.context = context;
	}

	@Override
	public String evaluate() {

		this.condition.setContext(context);
		StringBuffer expression = new StringBuffer();
		expression.append("not ( ");
		expression.append(this.condition.evaluate());
		expression.append(" )");
		
//		System.out.println("Not Operation String is " + expression);
		
		return expression.toString();
	}

}
