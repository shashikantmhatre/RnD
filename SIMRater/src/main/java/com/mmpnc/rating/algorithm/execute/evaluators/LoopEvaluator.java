package com.mmpnc.rating.algorithm.execute.evaluators;

import java.util.List;

import org.dom4j.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mmpnc.context.Context;
import com.mmpnc.context.ContextBase;
import com.mmpnc.context.ContextParam;
import com.mmpnc.rating.algorithm.util.XPathResolver;
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

public class LoopEvaluator implements Evaluator {

	private Context context;
	private String loopString; // added this
	private List<Evaluator> loopStatements;
	private Node currentNode;
	
	private static final Logger logger = LoggerFactory
			.getLogger(LoopEvaluator.class);

	// created new
	public LoopEvaluator(Context context, String loopString,
			List<Evaluator> loopStatements) {
		super();
		this.context = context;
		this.loopString = loopString.replaceAll("^\"|\"$", "");
		this.loopStatements = loopStatements;
	}

	@Override
	public void setContext(Context context) {
		this.context = context;
	}

	@Override
	public String evaluate(){
		
		if(loopString != null){
			
			if(logger.isInfoEnabled()){
				logger.info("We are in loop execution for statement " + loopString);
			}
			
			currentNode = (Node) this.context
					.getValue(ContextParam.CURRENTNODE);

			//get the XPathResolver from the currentContext and keep it for future use
			XPathResolver priorXPathResolver = (XPathResolver) this.context.getValue(ContextParam.XPATHRESOLVER);
			
			//create the XpathResolver for the current Loop execution
			XPathResolver currentXPathResolver = new XPathResolver();
			currentXPathResolver.setxPath(this.loopString);
			currentXPathResolver.setIteratorXPath(this.loopString);
			
			if(priorXPathResolver != null){
				//if we are in a loop within loop statement
				currentXPathResolver.setReferenceXPath(priorXPathResolver.getUpdatedXpath(currentXPathResolver.getIteratorXPath(), currentNode).toString());			
				
				if(logger.isInfoEnabled()){
					logger.info("Xpath Reference Updated to [with prior xpath reference]: " + currentXPathResolver.getReferenceXPath());
				}
				
			} else {
				//else we are in a single loop statement				
				currentXPathResolver.setReferenceXPath(currentXPathResolver.getIteratorXPath());				

				if(logger.isInfoEnabled()){
					logger.info("Xpath Reference not Updated as no prior xpath reference available : " + currentXPathResolver.getReferenceXPath());
				}
			}
			
			
			@SuppressWarnings("unchecked")
			List<Node> pointers = currentNode.selectNodes(currentXPathResolver.getReferenceXPath());

			for (Node node : pointers) {

				for (Evaluator eval : loopStatements) {

					currentXPathResolver.setxPath(node.getPath());
					currentXPathResolver.setReferenceXPath(node.getUniquePath());
					
					this.context.putValue(ContextParam.XPATHRESOLVER,currentXPathResolver);

					if (logger.isInfoEnabled()) {
						logger.info("XpathResolver IteratorXpath set to : "
								+ currentXPathResolver.getIteratorXPath());
						logger.info("XpathResolver XPath set to : "
								+ currentXPathResolver.getxPath());
						logger.info("XpathResolver Reference XPath set to -> "
								+ currentXPathResolver.getReferenceXPath());
					}
					
					eval.setContext(this.context);
					eval.evaluate();
				}
			}

			
			//reset the xpathresolver to the prior object reference
			this.context.putValue(ContextParam.XPATHRESOLVER,priorXPathResolver);
			
		}
		
		
		return null;
	}
	
	public String evaluate1() {

		if (loopString != null) {

			currentNode = (Node) this.context
					.getValue(ContextParam.CURRENTNODE);

			Context loopcontext = cloneContext(this.context);

			// verify if there is any prior loopxpath setup
			XPathResolver pXPathResolver = (XPathResolver) this.context
					.getValue(ContextParam.XPATHRESOLVER);

			// create new xpathresolver instance
			XPathResolver cXPathResolver = new XPathResolver();

			cXPathResolver.setxPath(loopString);
			cXPathResolver.setIteratorXPath(cXPathResolver.getxPath());

			if (logger.isInfoEnabled()) {
				logger.info("Replace the loopXPath with correct XPATH");
				logger.info("Loop XPATH String " + cXPathResolver.getxPath());
			}

			if (pXPathResolver != null) {
				// reset the cXPathResolver iterator xpath value

				if (logger.isInfoEnabled()) {
					logger.info("got the prior xPath entry "
							+ pXPathResolver.getxPath());
					logger.info("got the prior ReferenceXPath entry "
							+ pXPathResolver.getReferenceXPath());
				}

				if (cXPathResolver.getxPath().startsWith("../")) {
					if (logger.isInfoEnabled()) {
						logger.info("Special Case of ../PriorObject loop case");
					}
					cXPathResolver.setIteratorXPath(cXPathResolver
							.getIteratorXPath().replace(
									"../" + pXPathResolver.getxPath() ,
									pXPathResolver.getReferenceXPath() ));
				} else {
					cXPathResolver.setIteratorXPath(cXPathResolver
							.getIteratorXPath().replace(
									pXPathResolver.getxPath(),
									pXPathResolver.getReferenceXPath()));
				}
			}

			if (logger.isInfoEnabled()) {
				logger.info("set XPATH (IteratorXPath) pointer to "
						+ cXPathResolver.getIteratorXPath() + " at "
						+ currentNode.getUniquePath());
			}

			@SuppressWarnings("unchecked")
			List<Node> pointers = currentNode.selectNodes(cXPathResolver
					.getIteratorXPath());

			for (Node node : pointers) {

				for (Evaluator eval : loopStatements) {

					if (logger.isInfoEnabled()) {
						logger.info("run the loop for "
								+ cXPathResolver.getIteratorXPath());
						logger.info("Current XPath set to -> "
								+ cXPathResolver.getxPath());
						logger.info("Current Iterator XPath set to -> "
								+ cXPathResolver.getIteratorXPath());
						logger.info("Current Reference XPath set to -> "
								+ cXPathResolver.getReferenceXPath());
					}

					if(logger.isInfoEnabled()){
						logger.info("LOOP XPATH : " + node.getPath());
						logger.info("LOOP Unique XPATH : " + node.getUniquePath());
					}
					
					cXPathResolver.setReferenceXPath(node.getUniquePath());
					loopcontext.putValue(ContextParam.XPATHRESOLVER,
							cXPathResolver);
					eval.setContext(loopcontext);
					eval.evaluate();
				}
			}

		}

		return null;
	}

	private Context cloneContext(Context rootContext) {

		if (logger.isInfoEnabled()) {
			logger.info("LoopEvaluator cloned the Context object");
		}

		Context context = new ContextBase();

		// please comment below block if its not working correctly.
		try {
			if (rootContext.getValue(ContextParam.XPATHRESOLVER) != null) {
				context.putValue(ContextParam.XPATHRESOLVER,
						((XPathResolver) rootContext
								.getValue(ContextParam.XPATHRESOLVER)).clone());
			}
		} catch (Exception e) {
			logger.error("Can not clone XPathResolver as no XPathResolver found in context");
		}

//		context.putValue(ContextParam.JXPATHCONTEXT,
//				rootContext.getValue(ContextParam.JXPATHCONTEXT));
		context.putValue(ContextParam.SESSION,
				rootContext.getValue(ContextParam.SESSION));
		context.putValue(ContextParam.ENTITY,
				rootContext.getValue(ContextParam.ENTITY));
		context.putValue(ContextParam.PROCESS,
				rootContext.getValue(ContextParam.PROCESS));
		context.putValue(ContextParam.RATEBOOK,
				rootContext.getValue(ContextParam.RATEBOOK));
		context.putValue(ContextParam.LOCALVARIABLES,
				rootContext.getValue(ContextParam.LOCALVARIABLES));
		context.putValue(ContextParam.PROGRAMCALLLOG,
				rootContext.getValue(ContextParam.PROGRAMCALLLOG));

		// below changes are for the dom4j implementation
		context.putValue(ContextParam.CURRENTNODE,
				rootContext.getValue(ContextParam.CURRENTNODE));
		context.putValue(ContextParam.PREVIOUSNODE,
				rootContext.getValue(ContextParam.PREVIOUSNODE));
		context.putValue(ContextParam.XMLDOCUMENT,
				rootContext.getValue(ContextParam.XMLDOCUMENT));

		// below changes are done after SIMRater implementation
		context.putValue(ContextParam.ROOTNODENAME, rootContext.getValue(ContextParam.ROOTNODENAME));
		context.putValue(ContextParam.PRODUCTIDNODENAME, rootContext.getValue(ContextParam.PRODUCTIDNODENAME));

		return context;
	}
}
