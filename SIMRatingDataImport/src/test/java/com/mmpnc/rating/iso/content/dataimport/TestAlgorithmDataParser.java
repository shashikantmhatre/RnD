package com.mmpnc.rating.iso.content.dataimport;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.TokenSource;
import org.antlr.runtime.TokenStream;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.CommonTreeNodeStream;
import org.junit.Test;

import com.mmpnc.context.Context;
import com.mmpnc.context.ContextBase;
import com.mmpnc.context.ContextParam;
import com.mmpnc.rating.iso.algorithm.dataimport.isoalgorithmimportLexer;
import com.mmpnc.rating.iso.algorithm.dataimport.isoalgorithmimportParser;
import com.mmpnc.rating.iso.algorithm.dataimport.isoalgorithmwalker;
import com.mmpnc.rating.iso.algorithm.dataimport.isoalgorithmimportParser.block_return;

/**
 * Author : Shashikant Mhatre
 *
 * Created On : Jan 3, 2014
 *
 * Description :
 *
 * Note :
 */

public class TestAlgorithmDataParser {

	@Test
	public void test() {

//		String statementString = "LOOP { through:\"../../BOPClassification\" ASSIGN { variable:\"RatingLimit\" EXPRESSION { op:\"/\", side:\"\", variableType:\"\", ruleReference:\"\", value:\"\" BRACKET { EXPRESSION { op:\"+\", side:\"left\", variableType:\"\", ruleReference:\"\", value:\"\" EXPRESSION { op:\"\", side:\"left\", variableType:\"XPATH_NUMERIC\", ruleReference:\"\", value:\"../../BOPClassification/BusnPrsnlPropLimit\"  } EXPRESSION { op:\"\", side:\"right\", variableType:\"LV_DOUBLE\", ruleReference:\"\", value:\"functBPPTotalLimit\"  }  }  } EXPRESSION { op:\"\", side:\"right\", variableType:\"CONSTANT\", ruleReference:\"\", value:\"100.0\"  }  }  }  }";
		String statementString = "LOOP { through:\"../../BOPClassification\" ASSIGN { variable:\"RatingLimit\" EXPRESSION { op:\"/\", side:\"\", variableType:\"\", ruleReference:\"\", value:\"\" EXPRESSION { op:\"bracket\", side:\"\", variableType:\"\", ruleReference:\"\", value:\"\" EXPRESSION { op:\"+\", side:\"left\", variableType:\"\", ruleReference:\"\", value:\"\" EXPRESSION { op:\"\", side:\"left\", variableType:\"XPATH_NUMERIC\", ruleReference:\"\", value:\"../../BOPClassification/BusnPrsnlPropLimit\"  } EXPRESSION { op:\"\", side:\"right\", variableType:\"LV_DOUBLE\", ruleReference:\"\", value:\"functBPPTotalLimit\"  }  }  } EXPRESSION { op:\"\", side:\"right\", variableType:\"CONSTANT\", ruleReference:\"\", value:\"100.0\"  }  }  }  }";
//		String statementString = "LOOP { through:\"../../BOPClassification\" ASSIGN { variable:\"RatingLimit\" EXPRESSION { op:\"/\", side:\"\", variableType:\"\", ruleReference:\"\", value:\"\" EXPRESSION { op:\"+\", side:\"left\", variableType:\"\", ruleReference:\"\", value:\"\" EXPRESSION { op:\"\", side:\"left\", variableType:\"XPATH_NUMERIC\", ruleReference:\"\", value:\"../../BOPClassification/BusnPrsnlPropLimit\"  } EXPRESSION { op:\"\", side:\"right\", variableType:\"LV_DOUBLE\", ruleReference:\"\", value:\"functBPPTotalLimit\"  }  }  EXPRESSION { op:\"\", side:\"right\", variableType:\"CONSTANT\", ruleReference:\"\", value:\"100.0\"  }  }  }  }";
		
		try {
			Context context = new ContextBase();
			context.putValue(ContextParam.SPACECOUNT , 0 );
			context.putValue(ContextParam.FORMAT,false);
			
			CharStream charinput = new ANTLRStringStream(statementString);
			TokenSource tokensource = new isoalgorithmimportLexer(charinput);
			TokenStream tstream = new CommonTokenStream(tokensource);
			isoalgorithmimportParser parser = new isoalgorithmimportParser(
					tstream);

			block_return block = parser.block();

			System.out.println("tree -> " + ((CommonTree)block.getTree()).toStringTree());
			
			CommonTreeNodeStream commontreenode = new CommonTreeNodeStream(
					block.getTree());

			isoalgorithmwalker walker = new isoalgorithmwalker(
					commontreenode);
			walker.setContext(context);
			
			walker.block();
			
			System.out.println(walker.getClass());
			
		} catch (RecognitionException e) {
			System.err.println(e.toString());
		}
	}

}
