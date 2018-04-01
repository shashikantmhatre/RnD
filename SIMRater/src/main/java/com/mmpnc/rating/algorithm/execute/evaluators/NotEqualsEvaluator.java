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

public class NotEqualsEvaluator implements Evaluator {

	private Context context;
	private Evaluator left;
	private Evaluator right;
	private static final Logger logger = LoggerFactory
			.getLogger(NotEqualsEvaluator.class);

	public NotEqualsEvaluator(Evaluator left, Evaluator right) {
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
		if(context==null){
			logger.error("Context Value not set in NotEqualsEvaluator!");
		}
		
		StringBuffer sb = new StringBuffer();
		
		//set the op2 context
		Object op2Result = "";
		right.setContext(context);
		op2Result = right.evaluate();
		
		if (op2Result instanceof String && op2Result.equals("null")) {
			sb.append(" boolean( ");
			left.setContext(context);
			sb.append(left.evaluate());
			sb.append(" ) ");
		} else {
			left.setContext(context);
			sb.append(left.evaluate());
			sb.append(" != ");
			sb.append(op2Result);
		}
		if(logger.isInfoEnabled()){
			logger.info("Not Equal Evaluator returns -> " + sb.toString());
		}
		return sb.toString();
	}

}
