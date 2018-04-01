package com.mmpnc.rating.algorithm.execute;

import org.dom4j.Document;
import org.dom4j.Node;
import org.jaxen.SimpleNamespaceContext;
import org.jaxen.XPathFunctionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mmpnc.context.Context;
//import com.mmpnc.context.ContextBase;
import com.mmpnc.context.ContextParam;
import com.mmpnc.rating.xpath.custom.functions.XpathDaysFunction;
import com.mmpnc.rating.xpath.custom.functions.XpathYearFunction;
import com.mmpnc.simlanguage.execute.EvaluatorFactory;
import com.mmpnc.simlanguage.execute.LanguageExecutor;

/**
 * Author : Shashikant Mhatre
 *
 * Created On : Oct 12, 2013
 *
 * Description :
 *
 * Note :
 */

public class AlgorithmExecutor extends LanguageExecutor {
	Context context;
	StringBuffer buffer;
	private static final Logger logger = LoggerFactory
			.getLogger(AlgorithmExecutor.class);

	public AlgorithmExecutor(StringBuffer buffer){
		super(buffer);
	}
	
//	public AlgorithmExecutor(Document doc, StringBuffer buffer) {
//		super(buffer);
//		this.context = new ContextBase();
//		this.context.putValue(ContextParam.XMLDOCUMENT, doc);
//	}

	public AlgorithmExecutor(Context context, StringBuffer buffer) {
		super(buffer);
		this.context = context;
		this.buffer = buffer;
	}

	public AlgorithmExecutor(Document doc, Context context,
			StringBuffer buffer,Node rootNode) {
		
		super(buffer);
		
		SimpleNamespaceContext nc = new SimpleNamespaceContext();
        nc.addNamespace("rating", "http://mmpnc.com/rating");
                
		XPathFunctionContext fc =  (XPathFunctionContext) XPathFunctionContext.getInstance();
        fc.registerFunction("http://mmpnc.com/rating", "get-year", new XpathYearFunction());

        fc.registerFunction("http://mmpnc.com/rating", "get-days", new XpathDaysFunction());
        
		this.context = context;
		this.buffer = buffer;
		
		if(doc != null){
			this.context.putValue(ContextParam.XMLDOCUMENT, doc);
		} else {
			if(this.context.getValue(ContextParam.XMLDOCUMENT) == null){
				this.context.putValue(ContextParam.XMLDOCUMENT, null);
			} else {
				doc = (Document) this.context.getValue(ContextParam.XMLDOCUMENT);
			}
		}		
		
		if(rootNode == null){
			//This was changed to get the RootElement rather than 0 th node
//			rootNode = doc.node(0); 
			rootNode = doc.getRootElement(); 
		} 
		
		this.context.putValue(ContextParam.CURRENTNODE, rootNode);
		this.context.putValue(ContextParam.PREVIOUSNODE, null);
		
	}
	
	public AlgorithmExecutor(Document doc, Context context,
			StringBuffer buffer) {
		this(doc,context,buffer,null);
		
	}


	@Override
	public EvaluatorFactory getExecutorFactory() {
		if(logger.isInfoEnabled()){
			logger.info("We set the Evaluator factory to AlgorithmEvaluatorFactory");
		}
		return new AlgorithmEvaluatorFactory();
	}

	@Override
	public Context getContext() {
		return this.context;
	}

}
