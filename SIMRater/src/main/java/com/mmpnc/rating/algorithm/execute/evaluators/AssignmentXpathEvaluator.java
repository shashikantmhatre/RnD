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

public class AssignmentXpathEvaluator implements Evaluator {

	private Context context;
	private Evaluator left;
	private Evaluator right;
	
	private static final Logger logger = LoggerFactory
			.getLogger(AssignmentXpathEvaluator.class);
	
	public AssignmentXpathEvaluator(Context context,
			Evaluator left, Evaluator right) {
		super();
		this.context = context;
		this.left = left;
		this.right = right;
	}

	@Override
	public void setContext(Context context) {
		this.context = context;
	}

	@Override
	public Object evaluate() {
		if (logger.isInfoEnabled()) {
			logger.info("evaluate the AssignmentXpathEvaluator by calling AssignmentEvaluator");
		}
		AssignmentEvaluator eval = new AssignmentEvaluator(context, this.left, this.right);
		eval.evaluate();
		return null;
	}

}
