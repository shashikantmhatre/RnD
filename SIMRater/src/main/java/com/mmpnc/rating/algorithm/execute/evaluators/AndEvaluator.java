package com.mmpnc.rating.algorithm.execute.evaluators;

//import org.dom4j.Node;
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

public class AndEvaluator implements Evaluator {

	private Context context;
	private Evaluator left;
	private Evaluator right;
	
	private static final Logger log = LoggerFactory
			.getLogger(AndEvaluator.class);
	
	public AndEvaluator(Evaluator e1, Evaluator e2) {
		super();
		this.left = e1;
		this.right = e2;
	}

	@Override
	public void setContext(Context context) {
		this.context = context;
	}

	@Override
	public String evaluate() {
		
		
		StringBuffer sb = new StringBuffer();
		sb.append("(");
		left.setContext(context);
		sb.append(left.evaluate());
		sb.append(" and ");
		right.setContext(context);
		sb.append(right.evaluate());
		sb.append(")");

		if (log.isInfoEnabled()) {
			log.info("AndEvaluator.evaluatr returns " + sb);
		}

		return sb.toString();

//	boolean leftValue = false;
//	boolean rightValue = false;
//	
//	Node currentNode = (Node) context.getValue(ContextParam.CURRENTNODE);
//	
//	left.setContext(context);
//	String value = currentNode.valueOf(left.evaluate().toString()); 
//
//	if(value == null || value.equals("") || value.equals("false")){
//		leftValue = false;
//	} else{
//		leftValue = true;
//	}
//	
//	right.setContext(context);		
//	value = currentNode.valueOf(right.evaluate().toString()); 
//
//	if(value == null || value.equals("")|| value.equals("false") ){
//		rightValue = false;
//	} else{
//		rightValue = true;
//	}
//	
//	
//	
//	if(!leftValue){
//		if (log.isInfoEnabled()) {
//			log.info("AndEvaluator.evaluatr returns false() ");
//		}
//		return "false()";
//	} else if(!rightValue) {
//		if (log.isInfoEnabled()) {
//			log.info("AndEvaluator.evaluatr returns false() ");
//		}
//		return "false()";
//	} else{
//		if (log.isInfoEnabled()) {
//			log.info("AndEvaluator.evaluatr returns true() ");
//		}
//		return "true()";
//	}
	
	}

}
