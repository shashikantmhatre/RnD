package com.mmpnc.rating.algorithm.execute.evaluators;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

public class EqualsEvaluator implements Evaluator {

	private Context context;
	private Evaluator left;
	private Evaluator right;
	
	private static final Logger logger = LoggerFactory
			.getLogger(EqualsEvaluator.class);

	
	public EqualsEvaluator(Evaluator left, Evaluator right) {
		super();
		this.left = left;
		this.right = right;	}

	@Override
	public void setContext(Context context) {
		this.context = context;
	}

	@Override
	public String evaluate() {
StringBuffer sb = new StringBuffer();
		
		right.setContext(context);
		Object op2Result = right.evaluate();

		if (op2Result instanceof String && op2Result.equals("null")) {
			sb.append(" not( ");
			left.setContext(context);
			sb.append(left.evaluate());
			sb.append(" )");
		} else {
			left.setContext(context);
			sb.append(left.evaluate());
			sb.append(" = ");
			sb.append(op2Result);
		}

		if (logger.isInfoEnabled()) {
			logger.info(" EqualsEvaluator.evaluate returns " + sb);
		}
		return sb.toString();
	}
}
