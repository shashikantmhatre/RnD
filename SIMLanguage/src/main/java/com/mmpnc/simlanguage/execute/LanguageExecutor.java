package com.mmpnc.simlanguage.execute;

import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.tree.CommonTreeNodeStream;
import org.antlr.runtime.tree.TreeNodeStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mmpnc.context.Context;
import com.mmpnc.context.ContextBase;
import com.mmpnc.simlanguage.Activity;
import com.mmpnc.simlanguage.parse.SimLanguageWalker;
import com.mmpnc.simlanguage.read.ReaderFactory;

/**
 * Author : Shashikant Mhatre
 *
 * Created On : Oct 12, 2013
 *
 * Description :
 *
 * Note :
 */

public abstract class LanguageExecutor implements Activity {
	private Context context;
	private StringBuffer buffer;
	private CommonTreeNodeStream nodeStream;
	
	private static final Logger logger = LoggerFactory
			.getLogger(LanguageExecutor.class);

	public LanguageExecutor(StringBuffer buffer) {
		this.buffer = buffer;
	}

	public LanguageExecutor(CommonTreeNodeStream nodeStream) {
		this.nodeStream = nodeStream;
	}

	@Override
	public final Object execute() throws Exception {
		SimLanguageWalker tree = null;
		if(this.nodeStream == null) {
			Activity reader = ReaderFactory.getLanguageReader(buffer);
			 tree = new SimLanguageWalker(
					(TreeNodeStream) reader.execute());
		} else{
			 tree = new SimLanguageWalker(this.nodeStream);
		}
		
		EvaluatorFactory factory = getExecutorFactory();
		
		//set the evaluator factory to default implementation
		if(factory == null){
			factory = EvaluatorFactoryBuilder.getInstance().getEvaluatorFactory();
		}
		
		tree.setEvaluatorFactory(factory);
		
		try {
			
			//set the context to the default contextbase
			if( this.context == null){
			
				Context tempContext = getContext();
				
				if(tempContext == null){
					this.context = new ContextBase();
				} else {
					this.context = tempContext;
				}

			}
			
			tree.block(context);			
		} catch (RecognitionException e) {
			logger.error("Error Occured during Execution : " , e);
		}
		return null;
	}
	
	public abstract EvaluatorFactory getExecutorFactory();
	public abstract Context getContext();

}
