package com.mmpnc.simlanguage;

import com.mmpnc.context.Context;

/**
 * Author : Shashikant Mhatre
 *
 * Created On : Oct 12, 2013
 *
 * Description :
 *
 * Note :
 */

public interface Evaluator {
	
//	public static enum Type{
//		PATH,
//		ATTRIBUTE,
//		CONSTANT,
//		OPERATION
//	}
//	
//	public static class Operator{
//		public static final String plus = " + ";
//		public static final String minus = " - ";
//		public static final String multiply = " * ";
//		public static final String divide = " / ";
//		public static final String div = " div ";
//		public static final String mod = " mod ";
//		public static final String and = " and ";
//		public static final String or = " or ";
//		public static final String union = " | ";
//		public static final String lessThan = " < ";
//		public static final String greaterThan = " > ";
//		public static final String lessThanEquals = " <= ";
//		public static final String greateThanEquals = " >= ";
//		public static final String equals = " = ";
//		public static final String notEquals = " != ";
//		public static final String not = " ! ";
//	}
	
	void setContext(Context context);

	Object evaluate();

}
