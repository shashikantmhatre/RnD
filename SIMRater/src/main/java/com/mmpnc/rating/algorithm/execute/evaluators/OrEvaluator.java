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

public class OrEvaluator implements Evaluator {

	private Context context;
	private Evaluator left;
	private Evaluator right;

	public OrEvaluator(Evaluator left, Evaluator right) {
		super();
		this.left = left;
		this.right = right;
	}

	@Override
	public void setContext(Context context) {
		this.context = context;
	}

	@Override
	public String evaluate() {
		StringBuffer sb = new StringBuffer();
		sb.append("(");
		left.setContext(context);
		sb.append(left.evaluate());
		sb.append(" or ");
		right.setContext(context);
		sb.append(right.evaluate());
		sb.append(")");
		return sb.toString();
	}

}
