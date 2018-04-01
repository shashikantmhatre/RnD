package com.mmpnc.rating.algorithm.util;

import org.dom4j.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Author : Shashikant Mhatre
 *
 * Created On : Oct 12, 2013
 *
 * Description :
 *
 * Note :
 */

public class XPathResolver implements Cloneable{

	private String xPath;
	private String iteratorXPath;
	private String referenceXPath;
	private static final Logger logger = LoggerFactory.getLogger(XPathResolver.class);

	public String getxPath() {
		return xPath;
	}

	public void setxPath(String xPath) {
		this.xPath = xPath;
	}

	public String getIteratorXPath() {
		return iteratorXPath;
	}

	public void setIteratorXPath(String iteratorXPath) {
		this.iteratorXPath = iteratorXPath;
	}

	public String getReferenceXPath() {
		return referenceXPath;
	}

	public void setReferenceXPath(String referenceXPath) {
		this.referenceXPath = referenceXPath;
	}

	@Override
	public XPathResolver clone() throws CloneNotSupportedException {
		return (XPathResolver)super.clone();
	}
	
	
	public StringBuffer getUpdatedXpath(String relativeXPath, Node currentNode) {
		StringBuffer updatedXPath = new StringBuffer();

		if(relativeXPath.equals(this.iteratorXPath)){			
			//xpath string matches with the updatable xpath string
			if(logger.isInfoEnabled()){
				logger.info("Get the same XPath as Loop XPath");
			}			
			updatedXPath.append(this.referenceXPath);			
		} else if (relativeXPath.contains(this.iteratorXPath+"/")) {  
			//portion of updatable xpath string matches with the xpath string
			if(logger.isInfoEnabled()){
				logger.info("1. [Complete Match] " + this.iteratorXPath+"/" + " is present in " + relativeXPath+"/");
			}	
			updatedXPath.append(relativeXPath.replace(this.iteratorXPath+"/", this.referenceXPath+"/"));
		} else {
			//Check if we found partial match, if yes then lets replace one node at a time
			
			String[] iteratorXPathArray = iteratorXPath.split("/");
			String[] updatableXPathArray = relativeXPath.split("/");
			
			StringBuffer partialMatchedStringBuffer = new StringBuffer();
			
			for(int x=0 ; x<updatableXPathArray.length; x++){
				if(updatableXPathArray[x].equals(iteratorXPathArray[x])){
					if(x!=0){
//						System.out.println("/");
						partialMatchedStringBuffer.append("/");
					}
//					System.out.println(updatableXPathArray[x]);
					partialMatchedStringBuffer.append(updatableXPathArray[x]);
				}else{
					break;
				}
			}
			
			if(partialMatchedStringBuffer.length()==0){
				if(logger.isInfoEnabled()){
					logger.info("Did not find the matching xml for replacement");
				}
				updatedXPath.append(relativeXPath);
				
			} else {
				if(logger.isInfoEnabled()){
					logger.info("Found partial matching xml for replacement : " + partialMatchedStringBuffer);
				}
				
				Node refNode = currentNode.selectSingleNode(partialMatchedStringBuffer.toString());
				
				if(refNode != null){
				
					String[] baseXpath = xPath.split("/");
					String[] refXpath = referenceXPath.split("/");					
					String[] upXpath = refNode.getPath().split("/");
					
					StringBuffer partialReplacableXPath = new StringBuffer();
					
					for (int x = 0; x < upXpath.length; x++) {
						if (!upXpath[x].equals("")) {
							if (upXpath[x].equals(baseXpath[x])) {
								partialReplacableXPath.append("/").append(refXpath[x]);
							} else {
								partialReplacableXPath.append("/").append(upXpath[x]);
							}
						}
					}
					
					updatedXPath.append(relativeXPath.replace(partialMatchedStringBuffer.toString(), partialReplacableXPath.toString()));
					
					if(logger.isInfoEnabled()){
						logger.info("replaces XML Path is : " + updatedXPath);
					}					
					
				} else{
					if(logger.isInfoEnabled()){
						logger.info("XML element not present in memory hence returing the same xpath : " + relativeXPath);
					}
					updatedXPath.append(relativeXPath);
				}
				
				
			}			
		}
		return (updatedXPath);
	}
	
}
