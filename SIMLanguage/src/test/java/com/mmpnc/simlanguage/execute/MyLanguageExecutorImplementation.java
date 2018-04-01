package com.mmpnc.simlanguage.execute;

import org.antlr.runtime.tree.CommonTreeNodeStream;

import com.mmpnc.context.Context;

public class MyLanguageExecutorImplementation extends LanguageExecutor {

	public MyLanguageExecutorImplementation(StringBuffer buffer) {
		super(buffer);
	}

	public MyLanguageExecutorImplementation(CommonTreeNodeStream nodeStream) {
		super(nodeStream);
	}

	@Override
	public EvaluatorFactory getExecutorFactory() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Context getContext() {
		// TODO Auto-generated method stub
		return null;
	}

}
