package com.mmpnc.rating.iso.content;

import java.util.ArrayList;
import java.util.List;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.TokenSource;
import org.antlr.runtime.TokenStream;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.CommonTreeNodeStream;
import org.dom4j.Comment;
import org.dom4j.Element;
import org.dom4j.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mmpnc.context.Context;
import com.mmpnc.context.ContextParam;
import com.mmpnc.rating.vo.Program;
import com.mmpnc.rating.vo.Step;
import com.mmpnc.rating.iso.algorithm.dataimport.isoalgorithmimportLexer;
import com.mmpnc.rating.iso.algorithm.dataimport.isoalgorithmimportParser;
import com.mmpnc.rating.iso.algorithm.dataimport.isoalgorithmimportParser.block_return;
import com.mmpnc.rating.iso.algorithm.dataimport.isoalgorithmwalker;
import com.mmpnc.rating.iso.content.dataimport.isodataimportLexer;
import com.mmpnc.rating.iso.content.dataimport.isodataimportParser;
import com.mmpnc.rating.iso.content.dataimport.isodataimportParser.data_return;
import com.mmpnc.rating.iso.content.dataimport.isodatawalker;

/**
 * Author : Shashikant Mhatre
 *
 * Created On : Oct 12, 2013
 *
 * Description :
 *
 * Note :
 */

public class AlgorithmContentParser {
	private static final Logger logger = LoggerFactory
			.getLogger(AlgorithmContentParser.class);
	
	public static Step createStep(Node statementNode, Context context) {
		Step step = new Step();
		StringBuffer algoStep = processStatement(statementNode, context);
		step.setBlock(algoStep.toString());
		return step;
	}

	public static Program createProgram(Node pchNode, Context context) {

		Program program = new Program();
		program.setName(pchNode.valueOf("@name").trim());

		// System.out.println("Processing : " + program.getName());
		logger.info("Processing : " + program.getName());

		List<Step> programSteps = processProgram(pchNode, context);
		
		for(Step programStep : programSteps){
			programStep.setProgram(program);
			programStep.setExecutionorder(program.getSteps().size() + 1);
			program.addStep(programStep);
		}
		
		return program;
	}

	@SuppressWarnings("unchecked")
	public static List<Step> processProgram(Node pchNode, Context context) {
		
		List<Step> programSteps = new ArrayList<Step>();
		// System.out.println("Processing : " + program.getName());
		logger.info("Processing : " + pchNode.valueOf("@name").trim());
		
		
//		System.out.println("Processing : " + pchNode.valueOf("@name").trim());

		((Element) pchNode).addAttribute("executed","true");
		context.putValue(ContextParam.CURRENTPROCESS, pchNode);
		
		for (Node statementNode : (List<Node>) pchNode.selectNodes("node()")) {

			// bypass the comment element during processing
			if (statementNode instanceof Comment) {
				continue;
			} else if (statementNode.getName() == null) {
				continue;
			} 

			// set the value of tabcount to 0
			context.putValue(ContextParam.LOOPSTATEMENTEXECUTION, false);			
			
			StringBuffer algorithm = processStatement(statementNode, context);
			//
			if (algorithm.length() > 0) {
				
				// add the local PCH Steps before current PCH statement execution
				List<Step> localPchAlgoSteps = (List<Step>) context.getValue(ContextParam.PCHSTEPS);
				
//				System.out.println("we have " + localPchAlgoSteps.size() + " in ContextParam.PCHSTEPS" );
				
				programSteps.addAll(localPchAlgoSteps);

				
//				System.out.println("reset the PCH steps" );
				context.putValue(ContextParam.PCHSTEPS, new ArrayList<Step>());
				
				Step programStep = new Step();
				programStep.setBlock(algorithm.toString());
				programSteps.add(programStep);
			}
			

		}

//		System.out.println("End Processing : " + pchNode.valueOf("@name").trim());
		
		return programSteps;
	}

	public static StringBuffer processStatement(Node statementNode, Context context) {
		StringBuffer buffer = new StringBuffer();

		if (statementNode instanceof Comment) {
			return buffer;
		}

		if (statementNode.getName() != null) {
			NodeData statement = new NodeData(statementNode);
			String statementString = statement.getStatementElement().toString();

			if (statementString.length() <= 0) {
				return buffer;
			}

			logger.info("Node Data -> " + statementString);
//			System.out.println("Node Data -> " + statementString);
			
			try {

				CharStream charinput = new ANTLRStringStream(statementString);
				TokenSource tokensource = new isoalgorithmimportLexer(charinput);
				TokenStream tstream = new CommonTokenStream(tokensource);
				isoalgorithmimportParser parser = new isoalgorithmimportParser(
						tstream);

				block_return block = parser.block();

//				System.out.println("tree -> " + ((CommonTree)block.getTree()).toStringTree());
				logger.info("tree -> " + ((CommonTree) block.getTree()).toStringTree());

				CommonTreeNodeStream commontreenode = new CommonTreeNodeStream(
						block.getTree());

				isoalgorithmwalker walker = new isoalgorithmwalker(
						commontreenode);
				walker.setContext(context);

				buffer = walker.block();

				// System.out.println(buffer);

			} catch (RecognitionException e) {
				// TODO Auto-generated catch block
				logger.error("error during processing", e);
			}
		}
		
		return buffer;
	}

	public static List<Step> searchAndProcess(String processName, Context context){
		List<Step> algoSteps = new ArrayList<Step>();
		
		Node parentProcess = (Node) context.getValue(ContextParam.PARENTPROCESS);
		
		Node sibling = parentProcess.selectSingleNode("following-sibling::*[@recordType=\"PCH\" and @name=\"" + processName + "\" ][1]");
		if(sibling != null){
//			System.out.println("we got the process " + processName + " after " + parentProcess.valueOf("@name"));
			
			algoSteps = processProgram(sibling,context);
			
		} else{
			logger.info("************ No Process block [ " + processName + " ] available after "+ parentProcess.valueOf("@name") +"*****************");
//			System.out.println("************ No Process block [ " + processName + " ] available after "+ parentProcess.valueOf("@name") +"*****************");
		}
		
		return algoSteps;
	}
	
	
	public static void processNode(StringBuffer sb, Context context){
		try {
			
			logger.info("input String -> " + sb);
			
			CharStream charinput = new ANTLRStringStream(sb.toString());
			TokenSource tokenSource = new isodataimportLexer(charinput);
			TokenStream tstream = new CommonTokenStream(tokenSource);
			isodataimportParser parser = new isodataimportParser(tstream);

			data_return data;
			CommonTreeNodeStream commontreenode;
			
			data = parser.data();
			commontreenode = new CommonTreeNodeStream(data.getTree());
			
			
			isodatawalker walker = new isodatawalker(commontreenode);
			walker.setContext(context);
			
			logger.info("tree -> " + ((CommonTree) data.getTree()).toStringTree());
			walker.data();
			
		} catch (RecognitionException e) {
			e.printStackTrace();
		}

	}

	public static void processExecutionFlow(Node classNode, StringBuffer buffer, Context context){
		NodeData classNodeData = new NodeData(classNode);
		String executionString = classNodeData.getClassElement().toString();
		
//		 buffer.append(executionString);
		
		try {

			CharStream charinput = new ANTLRStringStream(executionString);
			TokenSource tokensource = new isoalgorithmimportLexer(charinput);
			TokenStream tstream = new CommonTokenStream(tokensource);
			isoalgorithmimportParser parser = new isoalgorithmimportParser(
					tstream);

			block_return block = parser.block();

//			System.out.println("tree -> " + ((CommonTree)block.getTree()).toStringTree());
			
			logger.info("tree -> "
					+ ((CommonTree) block.getTree()).toStringTree());

			CommonTreeNodeStream commontreenode = new CommonTreeNodeStream(
					block.getTree());

			isoalgorithmwalker walker = new isoalgorithmwalker(
					commontreenode);
			walker.setContext(context);

			buffer.append(walker.block());

//			System.out.println("recieved buffer " + buffer);

		} catch (RecognitionException e) {
			// TODO Auto-generated catch block
			logger.error("error during processing", e);
		}
	}
	
}
