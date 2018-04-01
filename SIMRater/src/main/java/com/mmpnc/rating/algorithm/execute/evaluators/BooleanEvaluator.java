package com.mmpnc.rating.algorithm.execute.evaluators;

import org.dom4j.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mmpnc.context.Context;
import com.mmpnc.context.ContextParam;
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

public class BooleanEvaluator implements Evaluator {

	private Evaluator condition;
	private Context context;

	private static final Logger logger = LoggerFactory
			.getLogger(BooleanEvaluator.class);

	public BooleanEvaluator(Evaluator bool) {
		super();
		this.condition = bool;
	}

	@Override
	public void setContext(Context context) {
		this.context = context;
	}

	@Override
	public Boolean evaluate() {
		if (logger.isErrorEnabled()) {
			if (context == null) {
				logger.error("Context Value not set in BooleanOperation.class");
			}
		}

		condition.setContext(context);
		String expression = (String) condition.evaluate();

		Node currentNode = (Node) this.context.getValue(ContextParam.CURRENTNODE); 
		
		if(logger.isInfoEnabled()){
			logger.info("Expression for condition is " + expression);
			logger.info("boolean value of expression is " + currentNode.valueOf(expression));
		}
		
		return Boolean.parseBoolean(currentNode.valueOf(expression));

	}


}
