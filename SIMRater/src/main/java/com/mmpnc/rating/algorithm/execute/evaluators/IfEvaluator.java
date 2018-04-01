package com.mmpnc.rating.algorithm.execute.evaluators;

import java.util.List;

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

public class IfEvaluator implements Evaluator {

	private Context context;
	private Evaluator condition;
	private List<Evaluator> ifStatement;
	private List<Evaluator> elseStatement;

	private static final Logger logger = LoggerFactory
			.getLogger(IfEvaluator.class);

	public IfEvaluator(Context context, Evaluator booleanEvaluator,
			List<Evaluator> ifStatement, List<Evaluator> elseStatement) {
		super();
		this.context = context;
		this.condition = booleanEvaluator;
		this.ifStatement = ifStatement;
		this.elseStatement = elseStatement;
	}

	@Override
	public void setContext(Context context) {
		this.context = context;
	}

	@Override
	public String evaluate() {

		if (logger.isErrorEnabled()) {
			if (context == null) {
				logger.error("IfEvaluator.context Value is not set");
			}
		}

		this.condition.setContext(context);
		boolean flag = (Boolean) this.condition.evaluate();

		if (logger.isInfoEnabled()) {
			logger.info("Condition returns the boolean value as " + flag);
		}

		if (flag) {
			for (Evaluator eval : ifStatement) {
				eval.setContext(context);
				eval.evaluate();
			}
		} else {
			if (elseStatement != null && elseStatement.size() > 0) {
				for (Evaluator eval : elseStatement) {
					eval.setContext(context);
					eval.evaluate();
				}
			}
		}
		return "";
	}

}
