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

public class UnaryNegationEvaluator implements Evaluator {

	private Context context;
	private Evaluator op1;
	private static final Logger logger = LoggerFactory
			.getLogger(UnaryNegationEvaluator.class);

	public UnaryNegationEvaluator(Evaluator op1) {
		super();
		this.op1 = op1;
	}

	@Override
	public void setContext(Context context) {
		this.context = context;
	}

	@Override
	public String evaluate() {

		StringBuffer sb = new StringBuffer();
		op1.setContext(context);
		sb.append("-").append(this.op1.evaluate());

		if(logger.isInfoEnabled()){
			logger.info("UnaryNegationEvaluator returns " + sb);
		}
		return sb.toString();
	}

}
