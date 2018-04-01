package com.mmpnc.rating.algorithm.util;

import java.util.HashMap;
import java.util.Map;

import org.dom4j.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mmpnc.context.Context;
import com.mmpnc.context.ContextParam;

/**
 * Author : Shashikant Mhatre
 *
 * Created On : Oct 12, 2013
 *
 * Description :
 *
 * Note :
 */

public class ProgramCallLog {
	private Map<String, Boolean> callLog;
	private static final Logger logger = LoggerFactory
			.getLogger(ProgramCallLog.class);

	public ProgramCallLog() {
		this.callLog = new HashMap<String, Boolean>();
	}

	public void setCallLog(Map<String, Boolean> callLog) {
		this.callLog = callLog;
	}

	public Map<String, Boolean> getCallLog() {
		if (this.callLog == null) {
			this.callLog = new HashMap<String, Boolean>();
		}
		return callLog;
	}

	public void logCall(Context context, String program) {
		Node currentNode = (Node) context
				.getValue(ContextParam.CURRENTNODE);

		XPathResolver xpath = (XPathResolver) context
				.getValue(ContextParam.XPATHRESOLVER);

		if (xpath != null && program.contains(xpath.getxPath() + "/")) {

			logger.info("XPath -> " + xpath.getxPath());
			logger.info("Iterator XPath -> " + xpath.getIteratorXPath());
			logger.info("Refarence XPath -> " + xpath.getReferenceXPath());

			logCall("",
					program.replace(xpath.getxPath() + "/",
							xpath.getReferenceXPath() + "/"));

		} else {
			logCall(currentNode.getUniquePath(), program);

		}

	}

	public void logCall(String nodeRef, String program) {
		StringBuffer sb = new StringBuffer();

		if (nodeRef.equals("")) {
			sb.append(program.replaceAll("'", "\""));
		} else {
			sb.append(nodeRef.replaceAll("/$", "")).append("/")
					.append(program.replaceAll("'", "\""));
		}
		if (logger.isInfoEnabled()) {
			logger.info("log call of the Program -> " + sb);
		}
		callLog.put(sb.toString(), true);
	}

	public boolean isCalled(Context context, String program) {
		Node currentNode = (Node) context
				.getValue(ContextParam.CURRENTNODE);

		XPathResolver xpath = (XPathResolver) context
				.getValue(ContextParam.XPATHRESOLVER);

		if (xpath != null && program.contains(xpath.getxPath() + "/")) {
			return isCalled(
					"",
					program.replace(xpath.getxPath() + "/",
							xpath.getReferenceXPath() + "/"));
		} else {
			return isCalled(currentNode.getUniquePath(), program);
		}
	}

	public boolean isCalled(String nodeRef, String program) {
		StringBuffer sb = new StringBuffer();
		if(nodeRef.equals("")){
			sb.append(program);
		}else{
			sb.append(nodeRef.replaceAll("/$", "")).append("/").append(program);
		}
		return isCalled(sb.toString());
	}

	public boolean isCalled(String programRef) {

		if (callLog.containsKey(programRef.replaceAll("'", "\""))) {
			if (logger.isInfoEnabled()) {
				logger.info("Program is called again"
						+ programRef.replaceAll("'", "\""));
			}
			return true;
		} else {
			return false;
		}
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		for (String str : callLog.keySet()) {
			sb.append(str).append("\n");
		}
		return sb.toString();
	}
}
