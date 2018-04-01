package com.mmpnc.simlanguage.read;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.CommonTreeNodeStream;
//import org.antlr.runtime.tree.CommonTreeNodeStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mmpnc.simlanguage.Activity;
import com.mmpnc.simlanguage.exception.SyntaxRecognitionException;
import com.mmpnc.simlanguage.parse.SimLanguageGrammarLexer;
import com.mmpnc.simlanguage.parse.SimLanguageGrammarParser;
import com.mmpnc.simlanguage.parse.SimLanguageGrammarParser.block_return;

/**
 * Author : Shashikant Mhatre
 *
 * Created On : Oct 12, 2013
 *
 * Description :
 *
 * Note :
 */

public class LanguageReader implements Activity {
	private StringBuffer buffer;

	private static final Logger logger = LoggerFactory.getLogger(LanguageReader.class);
	
	public LanguageReader(StringBuffer buffer) {
		this.buffer = buffer;
	}
	
	@Override
	public Object execute() throws SyntaxRecognitionException {
		
		if(logger.isInfoEnabled()){
			logger.info("*******************************************************************************************");
			logger.info("***                         Sim Language Parser Version 1.0                             ***");
			logger.info("*******************************************************************************************");
			logger.info("***********************************Statement Begin*****************************************");
			logger.info(buffer.toString());
			logger.info("************************************Statement End******************************************");
		}
		
		CharStream stream = new ANTLRStringStream(buffer.toString());
		SimLanguageGrammarLexer asLexer = new SimLanguageGrammarLexer(stream);
		SimLanguageGrammarParser asParser = new SimLanguageGrammarParser(
				new CommonTokenStream(asLexer));
		block_return statementBlock;
		CommonTreeNodeStream commontreenode;
		try {
			statementBlock = asParser.block();
			commontreenode = new CommonTreeNodeStream(statementBlock.getTree());
			
			if(logger.isInfoEnabled())
			{
				logger.info("Parsed Tree - ");
				logger.info(((CommonTree) statementBlock.getTree()).toStringTree());
			}
			
			return commontreenode;
		} catch (RecognitionException e) {
			throw new SyntaxRecognitionException("Unable to Parse Statement Block");
		}

	}

}
