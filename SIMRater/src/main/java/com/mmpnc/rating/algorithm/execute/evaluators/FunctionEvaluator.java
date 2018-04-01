package com.mmpnc.rating.algorithm.execute.evaluators;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mmpnc.context.Context;
import com.mmpnc.context.ContextParam;
import com.mmpnc.rating.algorithm.util.CustomFunctions;
import com.mmpnc.rating.util.DomainTableExecutor;
import com.mmpnc.rating.util.RateTableExecutor;
import com.mmpnc.rating.util.TableExecutor;
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

public class FunctionEvaluator implements Evaluator {

	private Context context;
	private String constant;
	private String op1;
	private List<Evaluator> op2;

//	private boolean recall;
	private static final Logger logger = LoggerFactory
			.getLogger(FunctionEvaluator.class);

	public FunctionEvaluator(String constant, String op1, List<Evaluator> op2) {
		super();

		this.constant = constant;
		this.op1 = op1;
		this.op2 = op2;
//		this.recall = false;
	}

	@Override
	public void setContext(Context context) {
		this.context = context;
	}

	@Override
	public Object evaluate() {
		StringBuffer sb = new StringBuffer();
		String returnString = "";
//		if (this.recall == false) {
//			this.recall = true;

			if (this.constant == null) {

				if (isXPathFunction(this.op1)) {
					//XPath Function implementation
					
//					System.out.println("xpath function call " + this.op1);
					
					
					sb.append(updateFunctionName(this.op1));
					sb.append(" ( ");
					int count = 0;
					for (Evaluator eval : this.op2) {
						if (count != 0) {
							sb.append(" , ");
						}
						eval.setContext(this.context);
						sb.append(eval.evaluate());
						count++;
					}
					sb.append(" ) ");

					returnString = sb.toString();

					if (logger.isInfoEnabled()) {
						logger.info("FunctionEvaluator.evaluate return XPATH Function String "
								+ returnString);
					}

				} else if (isCustomFunction(this.op1)) {
					
//					System.out.println("Custom function call " + this.op1);
					
					//Custom Function implementation
					CustomFunction function = CustomFunction.valueOf(this.op1);
					
					Object[] values = new Object[20];
					int count = 0;
					String param;
					for (Evaluator eval : this.op2) {
						eval.setContext(this.context);
						param = (String) eval.evaluate();
						values[count] = getValue(param);
						count++;
					}
					
					switch (function) {
					case GetDeductible:						
						return CustomFunctions.getDeductible(values);
					default:
						logger.error("Need to implement " + this.op1);
						break;
					}

				} else {

					if (logger.isInfoEnabled()) {
						logger.info("FunctionEvaluator.evaluate evaluates Custome Function "
								+ this.op1);
					}
					
//					System.out.println("domain table function call " + this.op1);
					
					String domainTable = this.op1;

					Object[] values = new Object[20];
					int count = 0;
					Object param;
					for (Evaluator eval : this.op2) {
						eval.setContext(this.context);
						param = eval.evaluate();

						if (domainTable.equals("domainTableLookup")) {							
							domainTable = ((String)getValue(param)).replace("^'|'$", "");
							continue;
						}
						param = getValue(param);
						if (param != null) {
							values[count] = ((String)param).replace("^'|'$", "");
						} else {
							values[count] = "";
						}
						count++;
					}

					TableExecutor tExec = new DomainTableExecutor(this.context);
					tExec.execute(domainTable, values);

					sb.append("'").append(tExec.getValue()).append("'");
					returnString = sb.toString();

					if (logger.isInfoEnabled()) {
						logger.info("FunctionEvaluator.evaluate return Custome Function String "
								+ returnString);
					}

				}

			} else if (this.constant.equals("RateTable")) {

				if (logger.isInfoEnabled()) {
					logger.info("FunctionEvaluator.evaluate evaluates RateTable values for "
							+ this.op1);
				}

//				System.out.println("rate table function call " + this.op1);
				
				
				Object[] values = new Object[20];
				int count = 0;
				Object param;
				for (Evaluator eval : this.op2) {
					logger.info("Start getting param values");
					
					eval.setContext(this.context);
					param = eval.evaluate();
					values[count] = getValue(param);
					
					logger.info("we stored " + values[count] + " @ index " + count);
					
					count++;
				}

				TableExecutor tExec = new RateTableExecutor(this.context);
				tExec.execute(this.op1, values);

				if (tExec.getValueType().equals("String")) {
					return sb.append("'").append(tExec.getValue()).append("'")
							.toString();
				} else {
					return new BigDecimal(((Double)tExec.getValue()).toString());
				}

			} else {

				// TODO We have to define the else part of the function call
				if (logger.isErrorEnabled()) {
					logger.error("FunctionEvaluator.evaluate function is missing with this function implementation "
							+ this.op1);
				}
				returnString = "";
			}
//		}

		if (logger.isDebugEnabled()) {
			logger.debug("FunctionEvaluator.evaluate returns value as "
					+ returnString);
		}

		return returnString;
	}

	private Object getValue(Object param) {
		Node currentNode = (Node) context
				.getValue(ContextParam.CURRENTNODE);

		logger.info("in getValue function to get value of " + param);
		
//		System.out.println("Function param " + param);
		
		Object object = null;
		if(param instanceof String){
			object = currentNode.valueOf((String)param).replace("'", "''");
//			System.out.println("Param  is turned to object of " + object.getClass());
		}
		else {
			return param;
		}
		String retString = "";

		if (object != null) {
			if (object instanceof BigDecimal) {
				retString = ((BigDecimal) object).toString().replaceAll("\\.0$", "");
				if(logger.isInfoEnabled()){
					logger.info("Value for " + param + " is [BigDecimal] " + retString);
				}
			} else {
				if(logger.isInfoEnabled()){
					logger.info(" Parameter " + param + " is of type " + object.getClass().getSimpleName() + " value is " + object);
				}
				retString = (String) object;
				if(logger.isInfoEnabled()){
					logger.info("Value for " + param + " is " + retString + " @ " + currentNode.getPath());
				}
			}
		}

		return retString;
	}

	private boolean isXPathFunction(String functionName) {
		try {
			Function.valueOf(functionName);
		} catch (IllegalArgumentException _ex) {
			return false;
		}

		return true;
	}

	private boolean isCustomFunction(String functionName) {
		try {
			CustomFunction.valueOf(functionName);
		} catch (IllegalArgumentException _ex) {
			return false;
		}

		return true;
	}

	private String updateFunctionName(String functionName) {
		Function func = null;
		try {
			func = Function.valueOf(functionName);
		} catch (IllegalArgumentException _ex) {
			func = Function.NA;
		}

		if (func != Function.NA) {
			functionName = StringUtils.uncapitalize(functionName);
		}

		return functionName;
	}

	private enum CustomFunction {
		GetDeductible;
	}

	private enum Function {

		Sum("Sum"), Count("Count"), Min("Min"), Max("Max"), NA("NA");

		Function(String value) {
			this.value = value;
		}

		@SuppressWarnings("unused")
		String getValue() {
			return value;
		}

		private String value;
	}
}
