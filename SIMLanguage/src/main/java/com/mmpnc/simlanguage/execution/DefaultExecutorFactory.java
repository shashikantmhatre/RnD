package com.mmpnc.simlanguage.execution;

import java.util.List;

import com.mmpnc.context.Context;
import com.mmpnc.simlanguage.Evaluator;
import com.mmpnc.simlanguage.execute.EvaluatorFactory;
import com.mmpnc.simlanguage.execution.evaluators.AndEvaluator;
import com.mmpnc.simlanguage.execution.evaluators.AssignmentEvaluator;
import com.mmpnc.simlanguage.execution.evaluators.AssignmentLocalEvaluator;
import com.mmpnc.simlanguage.execution.evaluators.AssignmentXpathEvaluator;
import com.mmpnc.simlanguage.execution.evaluators.AttachProgramEvaluator;
import com.mmpnc.simlanguage.execution.evaluators.BooleanEvaluator;
import com.mmpnc.simlanguage.execution.evaluators.CustomOperatorEvaluator;
import com.mmpnc.simlanguage.execution.evaluators.DivideEvaluator;
import com.mmpnc.simlanguage.execution.evaluators.EqualsEvaluator;
import com.mmpnc.simlanguage.execution.evaluators.FunctionEvaluator;
import com.mmpnc.simlanguage.execution.evaluators.GreaterThanEqualsEvaluator;
import com.mmpnc.simlanguage.execution.evaluators.GreaterThanEvaluator;
import com.mmpnc.simlanguage.execution.evaluators.IfEvaluator;
import com.mmpnc.simlanguage.execution.evaluators.IntegerEvaluator;
import com.mmpnc.simlanguage.execution.evaluators.LessThanEqualsEvaluator;
import com.mmpnc.simlanguage.execution.evaluators.LessThanEvaluator;
import com.mmpnc.simlanguage.execution.evaluators.LoopEvaluator;
import com.mmpnc.simlanguage.execution.evaluators.MinusEvaluator;
import com.mmpnc.simlanguage.execution.evaluators.ModEvaluator;
import com.mmpnc.simlanguage.execution.evaluators.MultiplyEvaluator;
import com.mmpnc.simlanguage.execution.evaluators.NotBooleanEvaluator;
import com.mmpnc.simlanguage.execution.evaluators.NotEqualsEvaluator;
import com.mmpnc.simlanguage.execution.evaluators.OrEvaluator;
import com.mmpnc.simlanguage.execution.evaluators.PlusEvaluator;
import com.mmpnc.simlanguage.execution.evaluators.ProgramEvaluator;
import com.mmpnc.simlanguage.execution.evaluators.SpecialFunctionEvaluator;
import com.mmpnc.simlanguage.execution.evaluators.StringEvaluator;
import com.mmpnc.simlanguage.execution.evaluators.UnaryNegationEvaluator;
import com.mmpnc.simlanguage.execution.evaluators.VarEvaluator;
import com.mmpnc.simlanguage.execution.evaluators.XpathEvaluator;

/**
 * Author : Shashikant Mhatre
 *
 * Created On : Oct 12, 2013
 *
 * Description :
 *
 * Note :
 */


public class DefaultExecutorFactory extends EvaluatorFactory {

	@Override
	public Evaluator getGreaterThanEqualsEvaluator(Evaluator left,
			Evaluator right) {
		return new GreaterThanEqualsEvaluator(left, right);
	}

	@Override
	public Evaluator getGreaterThanEvaluator(Evaluator left, Evaluator right) {
		return new GreaterThanEvaluator(left, right);
	}

	@Override
	public Evaluator getLessThanEqualsEvaluator(Evaluator left, Evaluator right) {
		return new LessThanEqualsEvaluator(left, right);
	}

	@Override
	public Evaluator getLessThanEvaluator(Evaluator left, Evaluator right) {
		return new LessThanEvaluator(left, right);
	}

	@Override
	public Evaluator getNotEqualsEvaluator(Evaluator left, Evaluator right) {
		return new NotEqualsEvaluator(left,right);
	}

	@Override
	public Evaluator getEqualsEvaluator(Evaluator left, Evaluator right) {
		return new EqualsEvaluator(left , right);
	}

	@Override
	public Evaluator getOrEvaluator(Evaluator left, Evaluator right) {
		return new OrEvaluator(left, right);
	}

	@Override
	public Evaluator getAndEvaluator(Evaluator left, Evaluator right) {
		return new AndEvaluator(left, right);
	}

	@Override
	public Evaluator getUnaryNegationEvaluator(Evaluator right) {
		return new UnaryNegationEvaluator(right);
	}

	@Override
	public Evaluator getModEvaluator(Evaluator left, Evaluator right) {
		return new ModEvaluator(left, right);
	}

	@Override
	public Evaluator getDivideEvaluator(Evaluator left, Evaluator right) {
		return new DivideEvaluator(left, right);
	}

	@Override
	public Evaluator getMultiplyEvaluator(Evaluator left, Evaluator right) {
		return new MultiplyEvaluator(left, right);
	}

	@Override
	public Evaluator getMinusEvaluator(Evaluator left, Evaluator right) {
		return new MinusEvaluator(left, right);
	}

	@Override
	public Evaluator getPlusEvaluator(Evaluator left, Evaluator right) {
		return new PlusEvaluator(left, right);
	}

	@Override
	public Evaluator getIntegerEvaluator(String value) {
		return new IntegerEvaluator(value);
	}

	@Override
	public Evaluator getStringEvaluator(String string) {
		return new StringEvaluator(string);
	}

	@Override
	public Evaluator getVarEvaluator(String constant, String variable) {
		return new VarEvaluator(constant, variable);
	}

	@Override
	public Evaluator getXpathEvaluator(String xpathString) {
		return new XpathEvaluator(xpathString);
	}

	@Override
	public Evaluator getLoopEvaluator(Context context, String string,
			List<Evaluator> evalList) {
		return new LoopEvaluator(context, string, evalList);
	}

	@Override
	public Evaluator getBooleanEvaluator(Evaluator bool) {
		return new BooleanEvaluator(bool);
	}

	@Override
	public Evaluator getIfEvaluator(Context context,
			Evaluator booleanEvaluator, List<Evaluator> thenEvalList,
			List<Evaluator> elseEvalList) {
		return new IfEvaluator(context, booleanEvaluator, thenEvalList, elseEvalList);
	}

	@Override
	public Evaluator getSpecialFunctionEvaluator(Context context,
			String functionName, Evaluator parameter) {
		return new SpecialFunctionEvaluator(context, functionName, parameter);
	}

	@Override
	public Evaluator getFunctionEvaluator(String constant, String functionName,
			List<Evaluator> evallist) {
		return new FunctionEvaluator(constant, functionName, evallist);
	}

	@Override
	public Evaluator getAssignmentXpathEvaluator(Context context,
			Evaluator left, Evaluator right) {
		return new AssignmentXpathEvaluator(context, left, right);
	}

	@Override
	public Evaluator getAssignmentLocalEvaluator(Context context,
			Evaluator left, Evaluator right) {
		return new AssignmentLocalEvaluator(context, left, right);
	}

	@Override
	public Evaluator getAssignmentEvaluator(Context context, Evaluator left,
			Evaluator right) {
		return new AssignmentEvaluator(context, left, right);
	}

	@Override
	public Evaluator getProgramEvaluator(Context context, String programName, Evaluator xpathEvaluator) {
		return new ProgramEvaluator(context, programName, xpathEvaluator);
	}

	@Override
	public Evaluator getNotBooleanEvaluator(Evaluator right) {
		return new NotBooleanEvaluator(right);
	}

	@Override
	public Evaluator getCustomOperatorEvaluator(Evaluator left, Evaluator right) {
		return new CustomOperatorEvaluator(left, right);
	}

	@Override
	public Evaluator getAttachProgramEvaluator(Context context, String programName,
			Evaluator xpathEvaluator) {
		return new AttachProgramEvaluator(context, programName, xpathEvaluator);
	}

}
