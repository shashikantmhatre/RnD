package com.mmpnc.rating.algorithm.execute.evaluators;

import java.math.BigDecimal;

import org.dom4j.Node;

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

public class MultiplyEvaluator implements Evaluator {

	private Evaluator left;
	private Evaluator right;
	private Context context;

	public MultiplyEvaluator(Evaluator left, Evaluator right) {
		this.left = left;
		this.right = right;
	}

	@Override
	public void setContext(Context context) {
		this.context = context;
	}

	@Override
	public Object evaluate() {

		BigDecimal leftBig;
		BigDecimal rightBig;

		Node currentNode = (Node) context.getValue(ContextParam.CURRENTNODE);
		
//		StringBuffer sb = new StringBuffer();
//		sb.append("(");
	
		left.setContext(context);
		String value = currentNode.valueOf(left.evaluate().toString()); 

		if(value == null || value.equals("")){
//			sb.append(0);
			leftBig = new BigDecimal(0);
		} else{
//			sb.append(value);
			leftBig = new BigDecimal(value);
		}
		
//		sb.append(" * ");
		
		right.setContext(context);		
		value = currentNode.valueOf(right.evaluate().toString()); 

		if(value == null || value.equals("") ){
//			sb.append(0);
			rightBig = new BigDecimal(0);
		} else{
//			sb.append(value);
			rightBig = new BigDecimal(value);
		}
		
//		sb.append(")");

//		return sb.toString();
		return leftBig.multiply(rightBig).toString();

	}

}
