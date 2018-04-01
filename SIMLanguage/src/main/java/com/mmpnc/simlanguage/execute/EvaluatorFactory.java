package com.mmpnc.simlanguage.execute;

import java.util.List;

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


public abstract class EvaluatorFactory {

	public abstract Evaluator getGreaterThanEqualsEvaluator(Evaluator left,
			Evaluator right);

	public abstract Evaluator getGreaterThanEvaluator(Evaluator left, Evaluator right);

	public abstract Evaluator getLessThanEqualsEvaluator(Evaluator left, Evaluator right);

	public abstract Evaluator getLessThanEvaluator(Evaluator left, Evaluator right);

	public abstract Evaluator getNotEqualsEvaluator(Evaluator left, Evaluator right);

	public abstract Evaluator getEqualsEvaluator(Evaluator left, Evaluator right) ;

	public abstract Evaluator getOrEvaluator(Evaluator left, Evaluator right);

	public abstract Evaluator getAndEvaluator(Evaluator left, Evaluator right);

	public abstract Evaluator getUnaryNegationEvaluator(Evaluator right);

	public abstract Evaluator getModEvaluator(Evaluator left, Evaluator right);

	public abstract Evaluator getDivideEvaluator(Evaluator left, Evaluator right);

	public abstract Evaluator getMultiplyEvaluator(Evaluator left, Evaluator right);

	public abstract Evaluator getMinusEvaluator(Evaluator left, Evaluator right);

	public abstract Evaluator getPlusEvaluator(Evaluator left, Evaluator right);

	public abstract Evaluator getIntegerEvaluator(String string);

	public abstract Evaluator getStringEvaluator(String string);

	public abstract Evaluator getVarEvaluator(String constant, String string);

	public abstract Evaluator getXpathEvaluator(String string);

	public abstract Evaluator getLoopEvaluator(Context context, String string,
			List<Evaluator> evalList);

	public abstract Evaluator getBooleanEvaluator(Evaluator bool);

	public abstract Evaluator getIfEvaluator(Context context,
			Evaluator booleanEvaluator, List<Evaluator> thenEvalList,
			List<Evaluator> elseEvalList);

	public abstract Evaluator getSpecialFunctionEvaluator(Context context,
			String functionName, Evaluator parameter);

	public abstract Evaluator getFunctionEvaluator(String constant, String functionName,
			List<Evaluator> evallist);

	public abstract Evaluator getAssignmentXpathEvaluator(Context context,
			Evaluator left, Evaluator right);

	public abstract Evaluator getAssignmentLocalEvaluator(Context context,
			Evaluator left, Evaluator right);

	public abstract Evaluator getAssignmentEvaluator(Context context, Evaluator left,
			Evaluator right);

	public abstract Evaluator getProgramEvaluator(Context context, String programName, Evaluator xpathEvaluator);

	public abstract Evaluator getNotBooleanEvaluator(Evaluator left);

	public abstract Evaluator getCustomOperatorEvaluator(Evaluator left, Evaluator right);

	public abstract Evaluator getAttachProgramEvaluator(Context context, String string,
			Evaluator xpathEvaluator);


}
