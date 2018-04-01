package com.mmpnc.rating.algorithm.execute.evaluators;

import org.dom4j.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mmpnc.context.Context;
import com.mmpnc.context.ContextParam;
import com.mmpnc.rating.algorithm.util.ProcessBlockExecutor;
//import com.mmpnc.rating.algorithm.util.ProcessBlockExecutor;
//import com.mmpnc.rating.algorithm.util.SubProcessExecutor;
//import com.mmpnc.rating.algorithm.util.SubProcessExecutorImpl;
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

public class XpathEvaluator implements Evaluator {

	private Context context;
	private String xpath;
//	private String loopXPath;
//	private String relativeXPath;
	
	private Node currentNode;
	
	private static final Logger logger = LoggerFactory
			.getLogger(XpathEvaluator.class);

	public XpathEvaluator(String xpath) {
		this(xpath, null);
	}

	public XpathEvaluator(String xpath, Context context) {
		super();
		this.xpath = xpath;
		this.context = context;
	}

	@Override
	public void setContext(Context context) {
		// System.out.println("setContext called in XpathEvaluator");
		this.context = context;
	}

	@Override
	public String evaluate(){
		StringBuffer correctedXpath = new StringBuffer();
		
		if (logger.isInfoEnabled()) {
			logger.info("We received the xPath String as " + this.xpath);
		}

		
		if (this.xpath != null) {
			//remove the quotations from the xpath string
			this.xpath = this.xpath.replaceAll("^\"|\"$", "");
			
			//get the current Node pointer from the context
			this.currentNode = (Node) this.context.getValue(ContextParam.CURRENTNODE);
			
			//get the currentXpathResolver setup in context
			XPathResolver currentXPathResolver = (XPathResolver) this.context.getValue(ContextParam.XPATHRESOLVER);
			
			
			if(currentXPathResolver != null){
				
				correctedXpath.append(currentXPathResolver.getUpdatedXpath(this.xpath, this.currentNode));
				
				if(logger.isInfoEnabled()){
					logger.info("xpath in XpathEvaluator [with loop enables] " + this.xpath + " @ " + this.currentNode.getPath());
					logger.info("unique xpath in XpathEvaluator [with loop enables] " + correctedXpath + " @ " + this.currentNode.getUniquePath());
				}	
				
				executeSubProcess(this.xpath, correctedXpath.toString());		
				
			} else{
				
				if(logger.isInfoEnabled()){
					logger.info("xpath in XpathEvaluator " + this.xpath + " @ " + this.currentNode.getPath());
					
				}				
				
				String basePath = "";
				String nodePath = "";
				
				if(xpath.replaceAll("(\\[).[^\\]]*(\\])", "").contains("/")){
					basePath = xpath.substring(0, xpath.lastIndexOf("/"));
					nodePath = xpath.substring(xpath.lastIndexOf("/"));
				} 
				
				
				if(dateFunction(this.xpath) != "")
				{
					if(logger.isInfoEnabled()){
						logger.info("We have a Date Function variable");
					}
					return dateFunction(this.xpath);
				}
					
				correctedXpath.append(this.xpath);
				
				if(logger.isInfoEnabled()){
					logger.info("unique xpath in XpathEvaluator " + correctedXpath + " @ " + this.currentNode.getUniquePath());
				}
				
				Node refNode = null;
				
				try {
					if(!basePath.equals("")){
						refNode = this.currentNode.selectSingleNode(basePath);
					}
				} catch (Exception _ex) {
					logger.error("error occured to get the xpath pointer " + _ex.toString());
					refNode = null;
				}
				
				if (refNode != null) {
					executeSubProcess(this.xpath, refNode.getUniquePath() + nodePath);
				}
				
			}

		}
		
		if (logger.isInfoEnabled()) {
			logger.info("XPathEvaluator.evaluate returns corrected XPath : " + correctedXpath);
		}
		
		return correctedXpath.toString();
	}
	
	/*
	public String evaluate1() {
		StringBuffer returnString = new StringBuffer();
		// System.out.println("XPATH before replacement -> " + this.xpath);

		if (logger.isInfoEnabled()) {
			logger.info("xPath String is " + this.xpath);
		}

		if (this.xpath != null) {
			this.xpath = this.xpath.replaceAll("^\"|\"$", "");

			XPathResolver pXPathResolver = (XPathResolver) this.context
					.getValue(ContextParam.XPATHRESOLVER);

			if (pXPathResolver != null) {
				this.loopXPath = pXPathResolver.getxPath();
				this.relativeXPath = pXPathResolver.getReferenceXPath();

				if (this.loopXPath != null && this.relativeXPath != null) {

					StringBuffer sb = new StringBuffer();

					if ((!this.xpath.matches("^\\.\\./.*"))
							&& (this.xpath.matches("^\\..*|^/.*"))) {
						sb.append("/");
						sb.append(this.loopXPath);
						if (logger.isInfoEnabled()) {
							logger.info("In If to create XPATH -> " + sb);
						}
					} else {
						sb.append(this.loopXPath);

						if (logger.isInfoEnabled()) {
							logger.info("In Else to create XPATH -> " + sb);
						}
					}
					
					//append the sb stringBuffer with "/"
					sb.append("/");
					
					if (logger.isInfoEnabled()) {
						logger.info("base XPath -> " + this.xpath);
						logger.info("replacable XPath -> " + sb.toString());
						logger.info("updated XPath -> " + this.relativeXPath);
					}

					if(dateFunction(this.xpath) != "")
					{
						if(logger.isInfoEnabled()){
							logger.info("We have a .Year variable with pathResolver");
						}
						return dateFunction(this.xpath);
					}
					
					
					executeSubProcess(this.xpath, this.xpath.replace(sb.toString(), this.relativeXPath + "/"));
					// }

					// below statement added to match the condition as in
					// LoopEvaluator
					returnString.append(this.xpath.replace(sb.toString(), this.relativeXPath + "/"));

					if(logger.isInfoEnabled()){
						logger.info("we are setting xpath after first program as " +  this.xpath.replace(sb.toString(), this.relativeXPath + "/"));
						logger.info("we are setting xpath after first program as [returnString]" +  returnString.toString());
					}
					

				}
			} else {
				Node currentNode = (Node) this.context.getValue(ContextParam.CURRENTNODE);
				
				logger.info("xpath in XpathEvaluator " + this.xpath + " @ " + currentNode.getPath());
				logger.info("unique xpath in XpathEvaluator " + this.xpath + " @ " + currentNode.getUniquePath());

				Node refNode = null;
				
				String basePath = "";
				String nodePath = "";
				
				if(xpath.replaceAll("(\\[).[^\\]]*(\\])", "").contains("/")){
					basePath = xpath.substring(0, xpath.lastIndexOf("/"));
					nodePath = xpath.substring(xpath.lastIndexOf("/"));
				} 
				
				
				if(dateFunction(this.xpath) != "")
				{
					if(logger.isInfoEnabled()){
						logger.info("We have a .Year variable");
					}
					return dateFunction(this.xpath);
				}
					
				returnString.append(this.xpath);
				
				try {
					if(!basePath.equals("")){
						refNode = currentNode.selectSingleNode(basePath);
					}
				} catch (Exception _ex) {
					logger.error("error occured to get the xpath pointer "
							+ _ex.toString());
					refNode = null;
					
					//below return statement added to return the function call if object is not available in xpath
					return returnString.toString();
				}
				
//				StringBuffer updatedXpath = new StringBuffer();
				
				
				if (refNode != null) {
					executeSubProcess(this.xpath, refNode.getUniquePath() + nodePath);
//					updatedXpath.append(refPointer.asPath()).append(nodePath);
				}
//				else{
//					executeSubProcess(this.xpath, currentNode.getUniquePath() + "/" + nodePath);
//				}
			
//				if(logger.isInfoEnabled()){
//					logger.info("we are setting xpath after second program as " + updatedXpath);
//				}
				
//				returnString.append(this.xpath);
				
			}
		}

		if (logger.isInfoEnabled()) {
			logger.info("XPathEvaluator.evaluate returns String "
					+ returnString);
		}
		
		return returnString.toString();

	}
	*/
	
	private String dateFunction(String xpath){
		StringBuffer retString = new StringBuffer();
		if(this.xpath.matches(".*\\.Year$")){
			
//			retString.append("datefunction:year(");
			retString.append("rating:get-year(");
			retString.append(this.xpath.replaceAll("\\.Year$", ""));
			retString.append(")");
			
			return retString.toString();
		} else if(this.xpath.matches(".*\\.Days$")){
			retString.append("rating:get-days(");
			retString.append(this.xpath.replaceAll("\\.Days$", ""));
			retString.append(")");
			
			return retString.toString();
		}
		
		return "";
	}
	
	private void executeSubProcess(String actual, String reference) {

		if (logger.isInfoEnabled()) {
			logger.info("Call the subprocess for " + actual
					+ " with reference value as " + reference);
		}
	
		/**
		 * Uncomment below statement once you implement the SubProcessExecutor
		 */
		

		 ProcessBlockExecutor subProc = new ProcessBlockExecutor(this.context);
		 subProc.executeBlockSteps(actual, reference);

	}

}
