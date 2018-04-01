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

public class CustomOperatorEvaluator implements Evaluator {

	private Evaluator left;
	private Evaluator right;
	private Context context;
	private Logger logger = LoggerFactory.getLogger(CustomOperatorEvaluator.class);
	
	public CustomOperatorEvaluator(Evaluator left, Evaluator right) {
		this.left = left;
		this.right = right;
	}

	@Override
	public void setContext(Context context) {
		this.context = context;
	}

	@Override
	public Object evaluate() {
		StringBuffer sb = new StringBuffer();
		sb.append(" boolean ( ");
		left.setContext(context);
		sb.append(left.evaluate());
		sb.append(" [ starts-with(.,");
		right.setContext(context);
		sb.append(right.evaluate());
		sb.append(") ] ) ");

		if (logger.isInfoEnabled()) {
			logger.info("CustomOperatorEvaluator.evaluatr returns " + sb);
		}

		return sb.toString();
	}

}
