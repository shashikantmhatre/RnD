package com.mmpnc.rating.algorithm.execute;

import java.util.List;

import com.mmpnc.context.Context;
import com.mmpnc.simlanguage.Evaluator;
import com.mmpnc.simlanguage.execute.EvaluatorFactory;

/**
 * Author : Shashikant Mhatre
 *
 * Created On : Oct 12, 2013
 *
 * Description :
 *
 * Note :
 */

public class AlgorithmEvaluatorFactory extends EvaluatorFactory {

	public Evaluator getGreaterThanEqualsEvaluator(Evaluator left,
			Evaluator right) {
		return new com.mmpnc.rating.algorithm.execute.evaluators.GreaterThanEqualsEvaluator(left, right);
	}

	public Evaluator getGreaterThanEvaluator(Evaluator left, Evaluator right) {
		return new com.mmpnc.rating.algorithm.execute.evaluators.GreaterThanEvaluator(
				left, right);
	}

	public Evaluator getLessThanEqualsEvaluator(Evaluator left, Evaluator right) {
		return new com.mmpnc.rating.algorithm.execute.evaluators.LessThanEqualsEvaluator(
				left, right);
	}

	public Evaluator getLessThanEvaluator(Evaluator left, Evaluator right) {
		return new com.mmpnc.rating.algorithm.execute.evaluators.LessThanEvaluator(
				left, right);
	}

	public Evaluator getNotEqualsEvaluator(Evaluator left, Evaluator right) {
		return new com.mmpnc.rating.algorithm.execute.evaluators.NotEqualsEvaluator(
				left, right);
	}

	public Evaluator getEqualsEvaluator(Evaluator left, Evaluator right) {
		return new com.mmpnc.rating.algorithm.execute.evaluators.EqualsEvaluator(
				left, right);
	}

	public Evaluator getOrEvaluator(Evaluator left, Evaluator right) {
		return new com.mmpnc.rating.algorithm.execute.evaluators.OrEvaluator(
				left, right);
	}

	public Evaluator getAndEvaluator(Evaluator left, Evaluator right) {
		return new com.mmpnc.rating.algorithm.execute.evaluators.AndEvaluator(
				left, right);
	}

	public Evaluator getUnaryNegationEvaluator(Evaluator right) {
		return new com.mmpnc.rating.algorithm.execute.evaluators.UnaryNegationEvaluator(
				right);
	}

	public Evaluator getModEvaluator(Evaluator left, Evaluator right) {
		return new com.mmpnc.rating.algorithm.execute.evaluators.ModEvaluator(
				left, right);
	}

	public Evaluator getDivideEvaluator(Evaluator left, Evaluator right) {
		return new com.mmpnc.rating.algorithm.execute.evaluators.DivideEvaluator(
				left, right);
	}

	public Evaluator getMultiplyEvaluator(Evaluator left, Evaluator right) {
		return new com.mmpnc.rating.algorithm.execute.evaluators.MultiplyEvaluator(
				left, right);
	}

	public Evaluator getMinusEvaluator(Evaluator left, Evaluator right) {
		return new com.mmpnc.rating.algorithm.execute.evaluators.MinusEvaluator(
				left, right);
	}

	public Evaluator getPlusEvaluator(Evaluator left, Evaluator right) {
		return new com.mmpnc.rating.algorithm.execute.evaluators.PlusEvaluator(
				left, right);
	}

	public Evaluator getIntegerEvaluator(String string) {
		return new com.mmpnc.rating.algorithm.execute.evaluators.IntegerEvaluator(
				string);
	}

	public Evaluator getStringEvaluator(String string) {
		return new com.mmpnc.rating.algorithm.execute.evaluators.StringEvaluator(
				string);
	}

	public Evaluator getVarEvaluator(String constant, String string) {
		return new com.mmpnc.rating.algorithm.execute.evaluators.VarEvaluator(
				constant, string);
	}

	public Evaluator getXpathEvaluator(String string) {
		return new com.mmpnc.rating.algorithm.execute.evaluators.XpathEvaluator(
				string);
	}

	public Evaluator getLoopEvaluator(Context context, String string,
			List<Evaluator> evalList) {
		return new com.mmpnc.rating.algorithm.execute.evaluators.LoopEvaluator(
				context, string, evalList);
	}

	public Evaluator getBooleanEvaluator(Evaluator bool) {
		return new com.mmpnc.rating.algorithm.execute.evaluators.BooleanEvaluator(
				bool);
	}

	public Evaluator getIfEvaluator(Context context,
			Evaluator booleanEvaluator, List<Evaluator> thenEvalList,
			List<Evaluator> elseEvalList) {
		return new com.mmpnc.rating.algorithm.execute.evaluators.IfEvaluator(
				context, booleanEvaluator, thenEvalList, elseEvalList);
	}

	public Evaluator getSpecialFunctionEvaluator(Context context,
			String functionName, Evaluator parameter) {
		return new com.mmpnc.rating.algorithm.execute.evaluators.SpecialFunctionEvaluator(
				context, functionName, parameter);
	}

	public Evaluator getFunctionEvaluator(String constant, String functionName,
			List<Evaluator> evallist) {
		return new com.mmpnc.rating.algorithm.execute.evaluators.FunctionEvaluator(
				constant, functionName, evallist);
	}

	public Evaluator getAssignmentXpathEvaluator(Context context,
			Evaluator left, Evaluator right) {
		return new com.mmpnc.rating.algorithm.execute.evaluators.AssignmentXpathEvaluator(
				context, left, right);
	}

	public Evaluator getAssignmentLocalEvaluator(Context context,
			Evaluator left, Evaluator right) {
		return new com.mmpnc.rating.algorithm.execute.evaluators.AssignmentLocalEvaluator(
				context, left, right);
	}

	public Evaluator getAssignmentEvaluator(Context context, Evaluator left,
			Evaluator right) {
		return new com.mmpnc.rating.algorithm.execute.evaluators.AssignmentEvaluator(
				context, left, right);
	}

	public Evaluator getNotBooleanEvaluator(Evaluator left) {
		return new com.mmpnc.rating.algorithm.execute.evaluators.NotBooleanEvaluator(left);
	}

	public Evaluator getCustomOperatorEvaluator(Evaluator left, Evaluator right) {
		return new com.mmpnc.rating.algorithm.execute.evaluators.CustomOperatorEvaluator(
				left, right);
	}

	@Override
	public Evaluator getProgramEvaluator(Context context, String programName,
			Evaluator xpathEvaluator) {
		return new com.mmpnc.rating.algorithm.execute.evaluators.ProgramEvaluator(
				context, programName, xpathEvaluator);
	}

	@Override
	public Evaluator getAttachProgramEvaluator(Context context, String programName,
			Evaluator xpathEvaluator) {
		
//		Evaluator eval = new com.mmpnc.rating.algorithm.execute.evaluators.AttachProgramEvaluator(
//				context, programName, xpathEvaluator);
//		
//		System.out.println("we got the instance " + eval);
//
//		return eval;
		
		return new com.mmpnc.rating.algorithm.execute.evaluators.AttachProgramEvaluator(
				context, programName, xpathEvaluator);
	}

}
