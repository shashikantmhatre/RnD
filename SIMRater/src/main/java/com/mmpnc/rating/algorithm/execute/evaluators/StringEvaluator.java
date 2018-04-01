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

public class StringEvaluator implements Evaluator {

	private String value; 
	private static final Logger logger = LoggerFactory.getLogger(StringEvaluator.class); 
	
	public StringEvaluator(String string) {
		this.value = string;
	}

	@Override
	public void setContext(Context context) {

	}

	@Override
	public Object evaluate() {
		if (value != null && !value.equals("")) {
			if (logger.isInfoEnabled()) {
				logger.info("StringEvaluator.evaluate returns the value as "
						+ value);
			}
			return value.replace("\"", "'");
		} else {
			return "''";
		}
	}

}
