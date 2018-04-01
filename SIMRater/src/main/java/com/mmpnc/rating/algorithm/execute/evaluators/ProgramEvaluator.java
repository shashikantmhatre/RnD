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

public class ProgramEvaluator implements Evaluator {

	private Context context;
	private String programName;
	private Evaluator xpathEvaluator;
	
	private static final Logger logger = LoggerFactory
			.getLogger(ProgramEvaluator.class);

	public ProgramEvaluator(Context context, String programName, Evaluator xpathEvaluator) {
		this.context = context;
		this.programName = programName;
		this.xpathEvaluator = xpathEvaluator;
	}

	@Override
	public void setContext(Context context) {
		this.context = context;
	}

	@Override
	public String evaluate() {
		
		System.out.println("We are in to Program Evaluator");
		
		if(logger.isInfoEnabled()){
			logger.info("XXXXXXXXXXXX ---- We are in program call -----XXXXXXXXX");
		}
		
		if(this.programName != null){
			
			if (logger.isInfoEnabled()) {
				logger.info("ProgramEvaluator.evaluate Call for "
						+ this.programName);
			}

			logger.error("Missing Implementation **************************************");
		
		} else {
			this.xpathEvaluator.setContext(context);
			String xpathValue = (String) this.xpathEvaluator.evaluate();
			
			if(logger.isInfoEnabled()){
				logger.info("Execute the program from the given path [ " + xpathValue + " ]" );
			}
		}
		
		
		return null;
	}

}
