package com.mmpnc.rating.algorithm.execute.evaluators;

import java.math.BigDecimal;
import java.math.RoundingMode;

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

public class SpecialFunctionEvaluator implements Evaluator {

	private Context context;
	private String programName;
	private Evaluator op1;

	private static final Logger logger = LoggerFactory
			.getLogger(SpecialFunctionEvaluator.class);

	public SpecialFunctionEvaluator(Context context, String programName,
			Evaluator op1) {
		this.context = context;
		this.programName = programName;
		this.op1 = op1;
	}

	@Override
	public void setContext(Context context) {
		this.context = context;
	}

	@Override
	public Object evaluate() {
		Object sfEvaluateReturn = null;
		BigDecimal functionReturn = new BigDecimal(0);

		op1.setContext(context);
		sfEvaluateReturn = op1.evaluate();

		Node currentNode = (Node) context
				.getValue(ContextParam.CURRENTNODE);
		BigDecimal bd = null;

		if(sfEvaluateReturn instanceof String)
		{
			String xPathValue = "";
		
			try {
				if (logger.isInfoEnabled()) {
					logger.info("execute the SpecialFunction " + programName
							+ " for " + sfEvaluateReturn);
					logger.info("Current xpath pointer is @ " + currentNode.getUniquePath());
				}
	
				xPathValue = currentNode.valueOf((String)sfEvaluateReturn);
				
				if(logger.isInfoEnabled()){
					logger.info("We get the value as " + xPathValue + " and classtype is " + xPathValue.getClass().getName());
				}
	
				if (xPathValue != null && !xPathValue.equals("NaN")) {
					bd = new BigDecimal(xPathValue);
				} else {
					return 0;
				}
	
			} catch (Exception _ex) {
				logger.error("Error getting value of " + sfEvaluateReturn
						+ " and convert to BigDecimal received [" + xPathValue + "]");
				logger.error(_ex.toString());
			}
		}else{
			
			if (sfEvaluateReturn != null && sfEvaluateReturn instanceof Double) {
				bd = new BigDecimal((Double) sfEvaluateReturn);
			} else {
				bd = (BigDecimal) sfEvaluateReturn;
			}
		}

		switch (SpecialFunction.valueOf(programName)) {
		case RoundUpDollar:
		case RoundDollar:
			functionReturn = bd.setScale(0, RoundingMode.HALF_UP);
			break;
		case RoundUpHundredth:
		case RoundHundredth:
			functionReturn = bd.setScale(2, RoundingMode.HALF_UP);
			break;
		case RoundUpThousandth:
		case RoundThousandth:
			functionReturn = bd.setScale(3, RoundingMode.HALF_UP);
			break;
		case RoundTenThousandth:
			functionReturn = bd.setScale(4, RoundingMode.HALF_UP);
			break;
		case RoundUpHundredThousandth:
		case RoundHundredThousandth:
			functionReturn = bd.setScale(6, RoundingMode.HALF_UP);
			break;
		}

		if (logger.isInfoEnabled()) {
			logger.info("SpecialFunction.evaluate returns value [" + functionReturn.toString() + "] for "
					+ sfEvaluateReturn);
		}

		return functionReturn;
	}

	private enum SpecialFunction {

		RoundUpDollar("RoundUpDollar"), RoundDollar("RoundDollar"), RoundUpHundredth(
				"RoundUpHundredth"), RoundHundredth("RoundHundredth"), RoundUpHundredThousandth(
				"RoundUpHundredThousandth"), RoundHundredThousandth(
				"RoundHundredThousandth"), RoundUpThousandth(
				"RoundUpThousandth"), RoundThousandth("RoundThousandth"),RoundTenThousandth("RoundTenThousandth");

		SpecialFunction(String value) {
			this.value = value;
		}

		@SuppressWarnings("unused")
		private String value;
	}
}
