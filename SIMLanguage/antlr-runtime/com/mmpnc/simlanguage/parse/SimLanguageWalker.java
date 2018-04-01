// $ANTLR 3.4 C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMLanguage\\src\\main\\resources\\com\\mmpnc\\simlanguage\\grammar\\SimLanguageWalker.g 2014-12-02 18:56:09

package com.mmpnc.simlanguage.parse;

import com.mmpnc.context.Context;
import com.mmpnc.simlanguage.Evaluator;
import com.mmpnc.simlanguage.execute.EvaluatorFactory;


import org.antlr.runtime.*;
import org.antlr.runtime.tree.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked"})
public class SimLanguageWalker extends TreeParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "AND", "COMMENTS", "DIV", "EQUALS", "EXPONENT", "IDENTS", "INTEGER", "MULTI_COMMENT", "NEGATION", "NOT", "NOTEQUALS", "OR", "SPECIALFUNCTION", "STARTS", "STRING", "SUBTRACT", "VARCONSTANT", "WS", "XPATHCONSTANT", "'!='", "'&&'", "'('", "')'", "'*'", "'+'", "','", "'-'", "'/'", "':'", "'<'", "'<='", "'='", "'=='", "'>'", "'>='", "'And'", "'Attach'", "'ELSE'", "'END IF'", "'END LOOP'", "'Execute'", "'IF'", "'Is Equal To'", "'LOOP THROUGH'", "'NOT'", "'Negative'", "'Not Equal To'", "'Or'", "'RateTable'", "'StartsWith'", "'Subtract'", "'THEN'", "'Using'", "'mod'", "'{'", "'||'", "'}'"
    };

    public static final int EOF=-1;
    public static final int T__23=23;
    public static final int T__24=24;
    public static final int T__25=25;
    public static final int T__26=26;
    public static final int T__27=27;
    public static final int T__28=28;
    public static final int T__29=29;
    public static final int T__30=30;
    public static final int T__31=31;
    public static final int T__32=32;
    public static final int T__33=33;
    public static final int T__34=34;
    public static final int T__35=35;
    public static final int T__36=36;
    public static final int T__37=37;
    public static final int T__38=38;
    public static final int T__39=39;
    public static final int T__40=40;
    public static final int T__41=41;
    public static final int T__42=42;
    public static final int T__43=43;
    public static final int T__44=44;
    public static final int T__45=45;
    public static final int T__46=46;
    public static final int T__47=47;
    public static final int T__48=48;
    public static final int T__49=49;
    public static final int T__50=50;
    public static final int T__51=51;
    public static final int T__52=52;
    public static final int T__53=53;
    public static final int T__54=54;
    public static final int T__55=55;
    public static final int T__56=56;
    public static final int T__57=57;
    public static final int T__58=58;
    public static final int T__59=59;
    public static final int T__60=60;
    public static final int AND=4;
    public static final int COMMENTS=5;
    public static final int DIV=6;
    public static final int EQUALS=7;
    public static final int EXPONENT=8;
    public static final int IDENTS=9;
    public static final int INTEGER=10;
    public static final int MULTI_COMMENT=11;
    public static final int NEGATION=12;
    public static final int NOT=13;
    public static final int NOTEQUALS=14;
    public static final int OR=15;
    public static final int SPECIALFUNCTION=16;
    public static final int STARTS=17;
    public static final int STRING=18;
    public static final int SUBTRACT=19;
    public static final int VARCONSTANT=20;
    public static final int WS=21;
    public static final int XPATHCONSTANT=22;

    // delegates
    public TreeParser[] getDelegates() {
        return new TreeParser[] {};
    }

    // delegators


    public SimLanguageWalker(TreeNodeStream input) {
        this(input, new RecognizerSharedState());
    }
    public SimLanguageWalker(TreeNodeStream input, RecognizerSharedState state) {
        super(input, state);
    }

    public String[] getTokenNames() { return SimLanguageWalker.tokenNames; }
    public String getGrammarFileName() { return "C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMLanguage\\src\\main\\resources\\com\\mmpnc\\simlanguage\\grammar\\SimLanguageWalker.g"; }


    private Context context = null;
    private EvaluatorFactory evaluatorFactory = null;
     
    public void setEvaluatorFactory(EvaluatorFactory evaluatorFactory){
      this.evaluatorFactory = evaluatorFactory;
    }



    // $ANTLR start "block"
    // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMLanguage\\src\\main\\resources\\com\\mmpnc\\simlanguage\\grammar\\SimLanguageWalker.g:37:1: block[Context context] :e1= statements ;
    public final void block(Context context) throws RecognitionException {
        List<Evaluator> e1 =null;


        try {
            // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMLanguage\\src\\main\\resources\\com\\mmpnc\\simlanguage\\grammar\\SimLanguageWalker.g:37:24: (e1= statements )
            // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMLanguage\\src\\main\\resources\\com\\mmpnc\\simlanguage\\grammar\\SimLanguageWalker.g:38:13: e1= statements
            {

                        this.context = context;
                        

            pushFollow(FOLLOW_statements_in_block85);
            e1=statements();

            state._fsp--;



                            for(Evaluator eval : e1)
                            {
                                eval.setContext(context);
                                eval.evaluate();
                            }
                        

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "block"



    // $ANTLR start "statements"
    // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMLanguage\\src\\main\\resources\\com\\mmpnc\\simlanguage\\grammar\\SimLanguageWalker.g:51:1: statements returns [List<Evaluator> eval] :e1= statement (e2= statement )* ;
    public final List<Evaluator> statements() throws RecognitionException {
        List<Evaluator> eval = null;


        Evaluator e1 =null;

        Evaluator e2 =null;


        try {
            // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMLanguage\\src\\main\\resources\\com\\mmpnc\\simlanguage\\grammar\\SimLanguageWalker.g:52:9: (e1= statement (e2= statement )* )
            // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMLanguage\\src\\main\\resources\\com\\mmpnc\\simlanguage\\grammar\\SimLanguageWalker.g:53:9: e1= statement (e2= statement )*
            {

                        eval = new ArrayList<Evaluator>();
                    

            pushFollow(FOLLOW_statement_in_statements150);
            e1=statement();

            state._fsp--;


            eval.add(e1);

            // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMLanguage\\src\\main\\resources\\com\\mmpnc\\simlanguage\\grammar\\SimLanguageWalker.g:56:45: (e2= statement )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==35||LA1_0==40||(LA1_0 >= 44 && LA1_0 <= 45)||LA1_0==47) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMLanguage\\src\\main\\resources\\com\\mmpnc\\simlanguage\\grammar\\SimLanguageWalker.g:56:46: e2= statement
            	    {
            	    pushFollow(FOLLOW_statement_in_statements157);
            	    e2=statement();

            	    state._fsp--;


            	    eval.add(e2);

            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return eval;
    }
    // $ANTLR end "statements"



    // $ANTLR start "statement"
    // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMLanguage\\src\\main\\resources\\com\\mmpnc\\simlanguage\\grammar\\SimLanguageWalker.g:58:1: statement returns [Evaluator eval] : ( assignment | ifStatement | loopStatement | executeProgram | attachProgram ) ;
    public final Evaluator statement() throws RecognitionException {
        Evaluator eval = null;


        Evaluator assignment1 =null;

        Evaluator ifStatement2 =null;

        Evaluator loopStatement3 =null;

        Evaluator executeProgram4 =null;

        Evaluator attachProgram5 =null;


        try {
            // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMLanguage\\src\\main\\resources\\com\\mmpnc\\simlanguage\\grammar\\SimLanguageWalker.g:58:35: ( ( assignment | ifStatement | loopStatement | executeProgram | attachProgram ) )
            // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMLanguage\\src\\main\\resources\\com\\mmpnc\\simlanguage\\grammar\\SimLanguageWalker.g:59:9: ( assignment | ifStatement | loopStatement | executeProgram | attachProgram )
            {
            // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMLanguage\\src\\main\\resources\\com\\mmpnc\\simlanguage\\grammar\\SimLanguageWalker.g:59:9: ( assignment | ifStatement | loopStatement | executeProgram | attachProgram )
            int alt2=5;
            switch ( input.LA(1) ) {
            case 35:
                {
                alt2=1;
                }
                break;
            case 45:
                {
                alt2=2;
                }
                break;
            case 47:
                {
                alt2=3;
                }
                break;
            case 44:
                {
                alt2=4;
                }
                break;
            case 40:
                {
                alt2=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;

            }

            switch (alt2) {
                case 1 :
                    // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMLanguage\\src\\main\\resources\\com\\mmpnc\\simlanguage\\grammar\\SimLanguageWalker.g:59:10: assignment
                    {
                    pushFollow(FOLLOW_assignment_in_statement181);
                    assignment1=assignment();

                    state._fsp--;


                    }
                    break;
                case 2 :
                    // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMLanguage\\src\\main\\resources\\com\\mmpnc\\simlanguage\\grammar\\SimLanguageWalker.g:59:23: ifStatement
                    {
                    pushFollow(FOLLOW_ifStatement_in_statement185);
                    ifStatement2=ifStatement();

                    state._fsp--;


                    }
                    break;
                case 3 :
                    // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMLanguage\\src\\main\\resources\\com\\mmpnc\\simlanguage\\grammar\\SimLanguageWalker.g:59:37: loopStatement
                    {
                    pushFollow(FOLLOW_loopStatement_in_statement189);
                    loopStatement3=loopStatement();

                    state._fsp--;


                    }
                    break;
                case 4 :
                    // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMLanguage\\src\\main\\resources\\com\\mmpnc\\simlanguage\\grammar\\SimLanguageWalker.g:59:53: executeProgram
                    {
                    pushFollow(FOLLOW_executeProgram_in_statement193);
                    executeProgram4=executeProgram();

                    state._fsp--;


                    }
                    break;
                case 5 :
                    // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMLanguage\\src\\main\\resources\\com\\mmpnc\\simlanguage\\grammar\\SimLanguageWalker.g:59:70: attachProgram
                    {
                    pushFollow(FOLLOW_attachProgram_in_statement197);
                    attachProgram5=attachProgram();

                    state._fsp--;


                    }
                    break;

            }



                            if(assignment1 != null)
                            {
                                eval = assignment1;
                            }
                            else if(ifStatement2 != null)
                            {
                                eval = ifStatement2;
                            }else if(loopStatement3 != null)
                            {
                                eval = loopStatement3;
                            }else if(executeProgram4 != null)
                            {
                                eval = executeProgram4;
                            }else if(attachProgram5 != null)
                            {
                                eval = attachProgram5;
                            }
                            
                        

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return eval;
    }
    // $ANTLR end "statement"



    // $ANTLR start "executeProgram"
    // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMLanguage\\src\\main\\resources\\com\\mmpnc\\simlanguage\\grammar\\SimLanguageWalker.g:82:1: executeProgram returns [Evaluator eval] : ^( 'Execute' (i= IDENTS |x= xpath ) ) ;
    public final Evaluator executeProgram() throws RecognitionException {
        Evaluator eval = null;


        CommonTree i=null;
        Evaluator x =null;


        try {
            // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMLanguage\\src\\main\\resources\\com\\mmpnc\\simlanguage\\grammar\\SimLanguageWalker.g:82:40: ( ^( 'Execute' (i= IDENTS |x= xpath ) ) )
            // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMLanguage\\src\\main\\resources\\com\\mmpnc\\simlanguage\\grammar\\SimLanguageWalker.g:83:5: ^( 'Execute' (i= IDENTS |x= xpath ) )
            {
            match(input,44,FOLLOW_44_in_executeProgram238); 

            match(input, Token.DOWN, null); 
            // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMLanguage\\src\\main\\resources\\com\\mmpnc\\simlanguage\\grammar\\SimLanguageWalker.g:83:17: (i= IDENTS |x= xpath )
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==IDENTS) ) {
                alt3=1;
            }
            else if ( (LA3_0==XPATHCONSTANT) ) {
                alt3=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;

            }
            switch (alt3) {
                case 1 :
                    // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMLanguage\\src\\main\\resources\\com\\mmpnc\\simlanguage\\grammar\\SimLanguageWalker.g:83:18: i= IDENTS
                    {
                    i=(CommonTree)match(input,IDENTS,FOLLOW_IDENTS_in_executeProgram243); 

                    }
                    break;
                case 2 :
                    // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMLanguage\\src\\main\\resources\\com\\mmpnc\\simlanguage\\grammar\\SimLanguageWalker.g:83:29: x= xpath
                    {
                    pushFollow(FOLLOW_xpath_in_executeProgram249);
                    x=xpath();

                    state._fsp--;


                    }
                    break;

            }


            match(input, Token.UP, null); 



                        if(i != null) {
                          eval = this.evaluatorFactory.getProgramEvaluator(context,(i!=null?i.getText():null), null);
                        } else {
                          eval = this.evaluatorFactory.getProgramEvaluator(context, null, x);
                        }
                    

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return eval;
    }
    // $ANTLR end "executeProgram"



    // $ANTLR start "attachProgram"
    // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMLanguage\\src\\main\\resources\\com\\mmpnc\\simlanguage\\grammar\\SimLanguageWalker.g:93:1: attachProgram returns [Evaluator eval] : ^( 'Attach' (i= IDENTS |x= xpath ) ) ;
    public final Evaluator attachProgram() throws RecognitionException {
        Evaluator eval = null;


        CommonTree i=null;
        Evaluator x =null;


        try {
            // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMLanguage\\src\\main\\resources\\com\\mmpnc\\simlanguage\\grammar\\SimLanguageWalker.g:93:39: ( ^( 'Attach' (i= IDENTS |x= xpath ) ) )
            // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMLanguage\\src\\main\\resources\\com\\mmpnc\\simlanguage\\grammar\\SimLanguageWalker.g:94:5: ^( 'Attach' (i= IDENTS |x= xpath ) )
            {
            match(input,40,FOLLOW_40_in_attachProgram283); 

            match(input, Token.DOWN, null); 
            // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMLanguage\\src\\main\\resources\\com\\mmpnc\\simlanguage\\grammar\\SimLanguageWalker.g:94:16: (i= IDENTS |x= xpath )
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==IDENTS) ) {
                alt4=1;
            }
            else if ( (LA4_0==XPATHCONSTANT) ) {
                alt4=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;

            }
            switch (alt4) {
                case 1 :
                    // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMLanguage\\src\\main\\resources\\com\\mmpnc\\simlanguage\\grammar\\SimLanguageWalker.g:94:17: i= IDENTS
                    {
                    i=(CommonTree)match(input,IDENTS,FOLLOW_IDENTS_in_attachProgram288); 

                    }
                    break;
                case 2 :
                    // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMLanguage\\src\\main\\resources\\com\\mmpnc\\simlanguage\\grammar\\SimLanguageWalker.g:94:28: x= xpath
                    {
                    pushFollow(FOLLOW_xpath_in_attachProgram294);
                    x=xpath();

                    state._fsp--;


                    }
                    break;

            }


            match(input, Token.UP, null); 



                        if(i != null) {
                          eval = this.evaluatorFactory.getAttachProgramEvaluator(context,(i!=null?i.getText():null), null);
                        } else {
                          eval = this.evaluatorFactory.getAttachProgramEvaluator(context, null, x);
                        }
                    

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return eval;
    }
    // $ANTLR end "attachProgram"



    // $ANTLR start "assignment"
    // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMLanguage\\src\\main\\resources\\com\\mmpnc\\simlanguage\\grammar\\SimLanguageWalker.g:104:1: assignment returns [Evaluator eval] : ^( '=' (i= IDENTS |v= varType |x= xpath ) e1= arithmeticExp ) ;
    public final Evaluator assignment() throws RecognitionException {
        Evaluator eval = null;


        CommonTree i=null;
        Evaluator v =null;

        Evaluator x =null;

        Evaluator e1 =null;


        try {
            // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMLanguage\\src\\main\\resources\\com\\mmpnc\\simlanguage\\grammar\\SimLanguageWalker.g:104:37: ( ^( '=' (i= IDENTS |v= varType |x= xpath ) e1= arithmeticExp ) )
            // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMLanguage\\src\\main\\resources\\com\\mmpnc\\simlanguage\\grammar\\SimLanguageWalker.g:105:5: ^( '=' (i= IDENTS |v= varType |x= xpath ) e1= arithmeticExp )
            {
            match(input,35,FOLLOW_35_in_assignment329); 

            match(input, Token.DOWN, null); 
            // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMLanguage\\src\\main\\resources\\com\\mmpnc\\simlanguage\\grammar\\SimLanguageWalker.g:105:11: (i= IDENTS |v= varType |x= xpath )
            int alt5=3;
            switch ( input.LA(1) ) {
            case IDENTS:
                {
                alt5=1;
                }
                break;
            case VARCONSTANT:
                {
                alt5=2;
                }
                break;
            case XPATHCONSTANT:
                {
                alt5=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;

            }

            switch (alt5) {
                case 1 :
                    // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMLanguage\\src\\main\\resources\\com\\mmpnc\\simlanguage\\grammar\\SimLanguageWalker.g:105:12: i= IDENTS
                    {
                    i=(CommonTree)match(input,IDENTS,FOLLOW_IDENTS_in_assignment334); 

                    }
                    break;
                case 2 :
                    // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMLanguage\\src\\main\\resources\\com\\mmpnc\\simlanguage\\grammar\\SimLanguageWalker.g:105:23: v= varType
                    {
                    pushFollow(FOLLOW_varType_in_assignment340);
                    v=varType();

                    state._fsp--;


                    }
                    break;
                case 3 :
                    // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMLanguage\\src\\main\\resources\\com\\mmpnc\\simlanguage\\grammar\\SimLanguageWalker.g:105:35: x= xpath
                    {
                    pushFollow(FOLLOW_xpath_in_assignment346);
                    x=xpath();

                    state._fsp--;


                    }
                    break;

            }


            pushFollow(FOLLOW_arithmeticExp_in_assignment351);
            e1=arithmeticExp();

            state._fsp--;


            match(input, Token.UP, null); 



                        if(i != null)
                        {
                            eval = this.evaluatorFactory.getAssignmentEvaluator(context, this.evaluatorFactory.getVarEvaluator(null, (i!=null?i.getText():null)), e1);
                        }
                        else {
                            if(v != null)
                            {
                                eval = this.evaluatorFactory.getAssignmentLocalEvaluator(context, v, e1);
                            } else {
                                eval = this.evaluatorFactory.getAssignmentXpathEvaluator(context, x, e1);
                            }
                        }
                    

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return eval;
    }
    // $ANTLR end "assignment"



    // $ANTLR start "function"
    // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMLanguage\\src\\main\\resources\\com\\mmpnc\\simlanguage\\grammar\\SimLanguageWalker.g:122:1: function[String constant] returns [Evaluator eval] : ^(e1= IDENTS (e2= arithmeticExp ( ',' e3= arithmeticExp )* )? ) ;
    public final Evaluator function(String constant) throws RecognitionException {
        Evaluator eval = null;


        CommonTree e1=null;
        Evaluator e2 =null;

        Evaluator e3 =null;


        try {
            // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMLanguage\\src\\main\\resources\\com\\mmpnc\\simlanguage\\grammar\\SimLanguageWalker.g:122:52: ( ^(e1= IDENTS (e2= arithmeticExp ( ',' e3= arithmeticExp )* )? ) )
            // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMLanguage\\src\\main\\resources\\com\\mmpnc\\simlanguage\\grammar\\SimLanguageWalker.g:123:6: ^(e1= IDENTS (e2= arithmeticExp ( ',' e3= arithmeticExp )* )? )
            {

            	        List<Evaluator> evallist = new ArrayList<Evaluator>();
            	    

            e1=(CommonTree)match(input,IDENTS,FOLLOW_IDENTS_in_function395); 

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); 
                // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMLanguage\\src\\main\\resources\\com\\mmpnc\\simlanguage\\grammar\\SimLanguageWalker.g:126:17: (e2= arithmeticExp ( ',' e3= arithmeticExp )* )?
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( ((LA7_0 >= IDENTS && LA7_0 <= INTEGER)||LA7_0==NEGATION||(LA7_0 >= SPECIALFUNCTION && LA7_0 <= VARCONSTANT)||LA7_0==XPATHCONSTANT||(LA7_0 >= 27 && LA7_0 <= 28)||LA7_0==31||LA7_0==35||LA7_0==52||LA7_0==57) ) {
                    alt7=1;
                }
                switch (alt7) {
                    case 1 :
                        // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMLanguage\\src\\main\\resources\\com\\mmpnc\\simlanguage\\grammar\\SimLanguageWalker.g:126:18: e2= arithmeticExp ( ',' e3= arithmeticExp )*
                        {
                        pushFollow(FOLLOW_arithmeticExp_in_function400);
                        e2=arithmeticExp();

                        state._fsp--;


                        evallist.add(e2);

                        // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMLanguage\\src\\main\\resources\\com\\mmpnc\\simlanguage\\grammar\\SimLanguageWalker.g:126:61: ( ',' e3= arithmeticExp )*
                        loop6:
                        do {
                            int alt6=2;
                            int LA6_0 = input.LA(1);

                            if ( (LA6_0==29) ) {
                                alt6=1;
                            }


                            switch (alt6) {
                        	case 1 :
                        	    // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMLanguage\\src\\main\\resources\\com\\mmpnc\\simlanguage\\grammar\\SimLanguageWalker.g:126:62: ',' e3= arithmeticExp
                        	    {
                        	    match(input,29,FOLLOW_29_in_function405); 

                        	    pushFollow(FOLLOW_arithmeticExp_in_function409);
                        	    e3=arithmeticExp();

                        	    state._fsp--;


                        	    evallist.add(e3);

                        	    }
                        	    break;

                        	default :
                        	    break loop6;
                            }
                        } while (true);


                        }
                        break;

                }


                match(input, Token.UP, null); 
            }



            	        eval = this.evaluatorFactory.getFunctionEvaluator(constant,(e1!=null?e1.getText():null), evallist);
            	    

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return eval;
    }
    // $ANTLR end "function"



    // $ANTLR start "specialFunction"
    // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMLanguage\\src\\main\\resources\\com\\mmpnc\\simlanguage\\grammar\\SimLanguageWalker.g:132:1: specialFunction returns [Evaluator eval] : ^(t= SPECIALFUNCTION e1= arithmeticExp ) ;
    public final Evaluator specialFunction() throws RecognitionException {
        Evaluator eval = null;


        CommonTree t=null;
        Evaluator e1 =null;


        try {
            // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMLanguage\\src\\main\\resources\\com\\mmpnc\\simlanguage\\grammar\\SimLanguageWalker.g:132:41: ( ^(t= SPECIALFUNCTION e1= arithmeticExp ) )
            // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMLanguage\\src\\main\\resources\\com\\mmpnc\\simlanguage\\grammar\\SimLanguageWalker.g:133:5: ^(t= SPECIALFUNCTION e1= arithmeticExp )
            {
            t=(CommonTree)match(input,SPECIALFUNCTION,FOLLOW_SPECIALFUNCTION_in_specialFunction447); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_arithmeticExp_in_specialFunction451);
            e1=arithmeticExp();

            state._fsp--;


            match(input, Token.UP, null); 



            	        eval = this.evaluatorFactory.getSpecialFunctionEvaluator(context,(t!=null?t.getText():null), e1);
            	    

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return eval;
    }
    // $ANTLR end "specialFunction"



    // $ANTLR start "rateFunction"
    // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMLanguage\\src\\main\\resources\\com\\mmpnc\\simlanguage\\grammar\\SimLanguageWalker.g:139:1: rateFunction returns [Evaluator eval] : ^(e1= 'RateTable' e2= function[$e1.text] ) ;
    public final Evaluator rateFunction() throws RecognitionException {
        Evaluator eval = null;


        CommonTree e1=null;
        Evaluator e2 =null;


        try {
            // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMLanguage\\src\\main\\resources\\com\\mmpnc\\simlanguage\\grammar\\SimLanguageWalker.g:139:38: ( ^(e1= 'RateTable' e2= function[$e1.text] ) )
            // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMLanguage\\src\\main\\resources\\com\\mmpnc\\simlanguage\\grammar\\SimLanguageWalker.g:140:5: ^(e1= 'RateTable' e2= function[$e1.text] )
            {
            e1=(CommonTree)match(input,52,FOLLOW_52_in_rateFunction486); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_function_in_rateFunction490);
            e2=function((e1!=null?e1.getText():null));

            state._fsp--;


            match(input, Token.UP, null); 



            	       eval = e2;
            //	       eval = this.evaluatorFactory.getRateTableEvaluator(context,(e1!=null?e1.getText():null));
            	    

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return eval;
    }
    // $ANTLR end "rateFunction"



    // $ANTLR start "ifStatement"
    // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMLanguage\\src\\main\\resources\\com\\mmpnc\\simlanguage\\grammar\\SimLanguageWalker.g:147:1: ifStatement returns [Evaluator eval] : ^( 'IF' (n= NOT )? bool= booleanExp s1= statements ( 'ELSE' s2= statements )? ) ;
    public final Evaluator ifStatement() throws RecognitionException {
        Evaluator eval = null;


        CommonTree n=null;
        Evaluator bool =null;

        List<Evaluator> s1 =null;

        List<Evaluator> s2 =null;


        try {
            // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMLanguage\\src\\main\\resources\\com\\mmpnc\\simlanguage\\grammar\\SimLanguageWalker.g:147:37: ( ^( 'IF' (n= NOT )? bool= booleanExp s1= statements ( 'ELSE' s2= statements )? ) )
            // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMLanguage\\src\\main\\resources\\com\\mmpnc\\simlanguage\\grammar\\SimLanguageWalker.g:148:5: ^( 'IF' (n= NOT )? bool= booleanExp s1= statements ( 'ELSE' s2= statements )? )
            {
            match(input,45,FOLLOW_45_in_ifStatement529); 

            match(input, Token.DOWN, null); 
            // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMLanguage\\src\\main\\resources\\com\\mmpnc\\simlanguage\\grammar\\SimLanguageWalker.g:148:12: (n= NOT )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==NOT) ) {
                int LA8_1 = input.LA(2);

                if ( (LA8_1==AND||LA8_1==EQUALS||(LA8_1 >= IDENTS && LA8_1 <= INTEGER)||(LA8_1 >= NEGATION && LA8_1 <= VARCONSTANT)||LA8_1==XPATHCONSTANT||(LA8_1 >= 27 && LA8_1 <= 28)||LA8_1==31||(LA8_1 >= 33 && LA8_1 <= 35)||(LA8_1 >= 37 && LA8_1 <= 38)||LA8_1==52||LA8_1==57) ) {
                    alt8=1;
                }
            }
            switch (alt8) {
                case 1 :
                    // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMLanguage\\src\\main\\resources\\com\\mmpnc\\simlanguage\\grammar\\SimLanguageWalker.g:148:13: n= NOT
                    {
                    n=(CommonTree)match(input,NOT,FOLLOW_NOT_in_ifStatement534); 

                    }
                    break;

            }


            pushFollow(FOLLOW_booleanExp_in_ifStatement540);
            bool=booleanExp();

            state._fsp--;


            pushFollow(FOLLOW_statements_in_ifStatement544);
            s1=statements();

            state._fsp--;


            // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMLanguage\\src\\main\\resources\\com\\mmpnc\\simlanguage\\grammar\\SimLanguageWalker.g:148:51: ( 'ELSE' s2= statements )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==41) ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMLanguage\\src\\main\\resources\\com\\mmpnc\\simlanguage\\grammar\\SimLanguageWalker.g:148:52: 'ELSE' s2= statements
                    {
                    match(input,41,FOLLOW_41_in_ifStatement547); 

                    pushFollow(FOLLOW_statements_in_ifStatement551);
                    s2=statements();

                    state._fsp--;


                    }
                    break;

            }


            match(input, Token.UP, null); 



                      if(n != null)
                      {
                        eval = this.evaluatorFactory.getIfEvaluator(context, this.evaluatorFactory.getNotBooleanEvaluator(bool), s1, s2);
                      } else {
                        eval = this.evaluatorFactory.getIfEvaluator(context, this.evaluatorFactory.getBooleanEvaluator(bool), s1, s2);
                      }
                    

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return eval;
    }
    // $ANTLR end "ifStatement"



    // $ANTLR start "loopStatement"
    // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMLanguage\\src\\main\\resources\\com\\mmpnc\\simlanguage\\grammar\\SimLanguageWalker.g:159:1: loopStatement returns [Evaluator eval] : ^( 'LOOP THROUGH' v1= STRING s1= statements ) ;
    public final Evaluator loopStatement() throws RecognitionException {
        Evaluator eval = null;


        CommonTree v1=null;
        List<Evaluator> s1 =null;


        try {
            // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMLanguage\\src\\main\\resources\\com\\mmpnc\\simlanguage\\grammar\\SimLanguageWalker.g:159:39: ( ^( 'LOOP THROUGH' v1= STRING s1= statements ) )
            // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMLanguage\\src\\main\\resources\\com\\mmpnc\\simlanguage\\grammar\\SimLanguageWalker.g:160:5: ^( 'LOOP THROUGH' v1= STRING s1= statements )
            {
            match(input,47,FOLLOW_47_in_loopStatement589); 

            match(input, Token.DOWN, null); 
            v1=(CommonTree)match(input,STRING,FOLLOW_STRING_in_loopStatement593); 

            pushFollow(FOLLOW_statements_in_loopStatement597);
            s1=statements();

            state._fsp--;


            match(input, Token.UP, null); 



                        eval = this.evaluatorFactory.getLoopEvaluator(context, (v1!=null?v1.getText():null), s1);
                    

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return eval;
    }
    // $ANTLR end "loopStatement"



    // $ANTLR start "xpath"
    // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMLanguage\\src\\main\\resources\\com\\mmpnc\\simlanguage\\grammar\\SimLanguageWalker.g:166:1: xpath returns [Evaluator eval] : XPATHCONSTANT ':' t= STRING ;
    public final Evaluator xpath() throws RecognitionException {
        Evaluator eval = null;


        CommonTree t=null;

        try {
            // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMLanguage\\src\\main\\resources\\com\\mmpnc\\simlanguage\\grammar\\SimLanguageWalker.g:167:5: ( XPATHCONSTANT ':' t= STRING )
            // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMLanguage\\src\\main\\resources\\com\\mmpnc\\simlanguage\\grammar\\SimLanguageWalker.g:167:7: XPATHCONSTANT ':' t= STRING
            {
            match(input,XPATHCONSTANT,FOLLOW_XPATHCONSTANT_in_xpath629); 

            match(input,32,FOLLOW_32_in_xpath631); 

            t=(CommonTree)match(input,STRING,FOLLOW_STRING_in_xpath635); 


                        eval = this.evaluatorFactory.getXpathEvaluator ((t!=null?t.getText():null));
                    

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return eval;
    }
    // $ANTLR end "xpath"



    // $ANTLR start "varType"
    // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMLanguage\\src\\main\\resources\\com\\mmpnc\\simlanguage\\grammar\\SimLanguageWalker.g:173:1: varType returns [Evaluator eval] : v= VARCONSTANT ':' t= IDENTS ;
    public final Evaluator varType() throws RecognitionException {
        Evaluator eval = null;


        CommonTree v=null;
        CommonTree t=null;

        try {
            // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMLanguage\\src\\main\\resources\\com\\mmpnc\\simlanguage\\grammar\\SimLanguageWalker.g:174:5: (v= VARCONSTANT ':' t= IDENTS )
            // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMLanguage\\src\\main\\resources\\com\\mmpnc\\simlanguage\\grammar\\SimLanguageWalker.g:174:7: v= VARCONSTANT ':' t= IDENTS
            {
            v=(CommonTree)match(input,VARCONSTANT,FOLLOW_VARCONSTANT_in_varType669); 

            match(input,32,FOLLOW_32_in_varType671); 

            t=(CommonTree)match(input,IDENTS,FOLLOW_IDENTS_in_varType675); 


                        eval = this.evaluatorFactory.getVarEvaluator ((v!=null?v.getText():null), (t!=null?t.getText():null));
                    

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return eval;
    }
    // $ANTLR end "varType"



    // $ANTLR start "atom"
    // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMLanguage\\src\\main\\resources\\com\\mmpnc\\simlanguage\\grammar\\SimLanguageWalker.g:180:1: atom returns [Evaluator eval] : ( (i= IDENTS |ti= INTEGER |ts= STRING ) |e= function[null] |e1= rateFunction |e2= xpath |e3= varType |e4= specialFunction );
    public final Evaluator atom() throws RecognitionException {
        Evaluator eval = null;


        CommonTree i=null;
        CommonTree ti=null;
        CommonTree ts=null;
        Evaluator e =null;

        Evaluator e1 =null;

        Evaluator e2 =null;

        Evaluator e3 =null;

        Evaluator e4 =null;


        try {
            // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMLanguage\\src\\main\\resources\\com\\mmpnc\\simlanguage\\grammar\\SimLanguageWalker.g:181:5: ( (i= IDENTS |ti= INTEGER |ts= STRING ) |e= function[null] |e1= rateFunction |e2= xpath |e3= varType |e4= specialFunction )
            int alt11=6;
            switch ( input.LA(1) ) {
            case IDENTS:
                {
                int LA11_1 = input.LA(2);

                if ( (LA11_1==DOWN) ) {
                    alt11=2;
                }
                else if ( ((LA11_1 >= UP && LA11_1 <= AND)||LA11_1==EQUALS||(LA11_1 >= IDENTS && LA11_1 <= INTEGER)||(LA11_1 >= NEGATION && LA11_1 <= VARCONSTANT)||LA11_1==XPATHCONSTANT||(LA11_1 >= 27 && LA11_1 <= 29)||LA11_1==31||(LA11_1 >= 33 && LA11_1 <= 35)||(LA11_1 >= 37 && LA11_1 <= 38)||LA11_1==40||(LA11_1 >= 44 && LA11_1 <= 45)||LA11_1==47||LA11_1==52||LA11_1==57) ) {
                    alt11=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 11, 1, input);

                    throw nvae;

                }
                }
                break;
            case INTEGER:
            case STRING:
                {
                alt11=1;
                }
                break;
            case 52:
                {
                alt11=3;
                }
                break;
            case XPATHCONSTANT:
                {
                alt11=4;
                }
                break;
            case VARCONSTANT:
                {
                alt11=5;
                }
                break;
            case SPECIALFUNCTION:
                {
                alt11=6;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 11, 0, input);

                throw nvae;

            }

            switch (alt11) {
                case 1 :
                    // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMLanguage\\src\\main\\resources\\com\\mmpnc\\simlanguage\\grammar\\SimLanguageWalker.g:181:8: (i= IDENTS |ti= INTEGER |ts= STRING )
                    {
                    // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMLanguage\\src\\main\\resources\\com\\mmpnc\\simlanguage\\grammar\\SimLanguageWalker.g:181:8: (i= IDENTS |ti= INTEGER |ts= STRING )
                    int alt10=3;
                    switch ( input.LA(1) ) {
                    case IDENTS:
                        {
                        alt10=1;
                        }
                        break;
                    case INTEGER:
                        {
                        alt10=2;
                        }
                        break;
                    case STRING:
                        {
                        alt10=3;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 10, 0, input);

                        throw nvae;

                    }

                    switch (alt10) {
                        case 1 :
                            // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMLanguage\\src\\main\\resources\\com\\mmpnc\\simlanguage\\grammar\\SimLanguageWalker.g:181:10: i= IDENTS
                            {
                            i=(CommonTree)match(input,IDENTS,FOLLOW_IDENTS_in_atom716); 

                            }
                            break;
                        case 2 :
                            // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMLanguage\\src\\main\\resources\\com\\mmpnc\\simlanguage\\grammar\\SimLanguageWalker.g:181:21: ti= INTEGER
                            {
                            ti=(CommonTree)match(input,INTEGER,FOLLOW_INTEGER_in_atom722); 

                            }
                            break;
                        case 3 :
                            // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMLanguage\\src\\main\\resources\\com\\mmpnc\\simlanguage\\grammar\\SimLanguageWalker.g:181:34: ts= STRING
                            {
                            ts=(CommonTree)match(input,STRING,FOLLOW_STRING_in_atom728); 

                            }
                            break;

                    }



                                    if(i != null)
                                    {
                                        eval = this.evaluatorFactory.getVarEvaluator(null, (i!=null?i.getText():null));
                                    }
                                    else
                                    {
                                        if(ti == null){
                                            eval = this.evaluatorFactory.getStringEvaluator ((ts!=null?ts.getText():null));
                                        }else{
                                            eval = this.evaluatorFactory.getIntegerEvaluator ((ti!=null?ti.getText():null));
                                        }
                                    }
                                

                    }
                    break;
                case 2 :
                    // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMLanguage\\src\\main\\resources\\com\\mmpnc\\simlanguage\\grammar\\SimLanguageWalker.g:196:10: e= function[null]
                    {
                    pushFollow(FOLLOW_function_in_atom757);
                    e=function(null);

                    state._fsp--;



                                    eval = e;
                                

                    }
                    break;
                case 3 :
                    // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMLanguage\\src\\main\\resources\\com\\mmpnc\\simlanguage\\grammar\\SimLanguageWalker.g:200:10: e1= rateFunction
                    {
                    pushFollow(FOLLOW_rateFunction_in_atom785);
                    e1=rateFunction();

                    state._fsp--;



                                    eval = e1;
                                

                    }
                    break;
                case 4 :
                    // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMLanguage\\src\\main\\resources\\com\\mmpnc\\simlanguage\\grammar\\SimLanguageWalker.g:204:10: e2= xpath
                    {
                    pushFollow(FOLLOW_xpath_in_atom812);
                    e2=xpath();

                    state._fsp--;



                                  eval = e2;  
                                

                    }
                    break;
                case 5 :
                    // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMLanguage\\src\\main\\resources\\com\\mmpnc\\simlanguage\\grammar\\SimLanguageWalker.g:208:10: e3= varType
                    {
                    pushFollow(FOLLOW_varType_in_atom843);
                    e3=varType();

                    state._fsp--;



                                  eval = e3;
                                

                    }
                    break;
                case 6 :
                    // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMLanguage\\src\\main\\resources\\com\\mmpnc\\simlanguage\\grammar\\SimLanguageWalker.g:212:10: e4= specialFunction
                    {
                    pushFollow(FOLLOW_specialFunction_in_atom870);
                    e4=specialFunction();

                    state._fsp--;



                                  eval = e4;
                                

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return eval;
    }
    // $ANTLR end "atom"



    // $ANTLR start "arithmeticExp"
    // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMLanguage\\src\\main\\resources\\com\\mmpnc\\simlanguage\\grammar\\SimLanguageWalker.g:218:1: arithmeticExp returns [Evaluator eval] : ( ^( '+' e1= arithmeticExp e2= arithmeticExp ) | ^( SUBTRACT e1= arithmeticExp e2= arithmeticExp ) | ^( '*' e1= arithmeticExp e2= arithmeticExp ) | ^( '/' e1= arithmeticExp e2= arithmeticExp ) | ^( 'mod' e1= arithmeticExp e2= arithmeticExp ) | ^( STARTS e1= arithmeticExp e2= arithmeticExp ) | ^( NEGATION e1= atom ) |e= atom |e= assignment );
    public final Evaluator arithmeticExp() throws RecognitionException {
        Evaluator eval = null;


        Evaluator e1 =null;

        Evaluator e2 =null;

        Evaluator e =null;


        try {
            // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMLanguage\\src\\main\\resources\\com\\mmpnc\\simlanguage\\grammar\\SimLanguageWalker.g:219:5: ( ^( '+' e1= arithmeticExp e2= arithmeticExp ) | ^( SUBTRACT e1= arithmeticExp e2= arithmeticExp ) | ^( '*' e1= arithmeticExp e2= arithmeticExp ) | ^( '/' e1= arithmeticExp e2= arithmeticExp ) | ^( 'mod' e1= arithmeticExp e2= arithmeticExp ) | ^( STARTS e1= arithmeticExp e2= arithmeticExp ) | ^( NEGATION e1= atom ) |e= atom |e= assignment )
            int alt12=9;
            switch ( input.LA(1) ) {
            case 28:
                {
                alt12=1;
                }
                break;
            case SUBTRACT:
                {
                alt12=2;
                }
                break;
            case 27:
                {
                alt12=3;
                }
                break;
            case 31:
                {
                alt12=4;
                }
                break;
            case 57:
                {
                alt12=5;
                }
                break;
            case STARTS:
                {
                alt12=6;
                }
                break;
            case NEGATION:
                {
                alt12=7;
                }
                break;
            case IDENTS:
            case INTEGER:
            case SPECIALFUNCTION:
            case STRING:
            case VARCONSTANT:
            case XPATHCONSTANT:
            case 52:
                {
                alt12=8;
                }
                break;
            case 35:
                {
                alt12=9;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 12, 0, input);

                throw nvae;

            }

            switch (alt12) {
                case 1 :
                    // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMLanguage\\src\\main\\resources\\com\\mmpnc\\simlanguage\\grammar\\SimLanguageWalker.g:219:7: ^( '+' e1= arithmeticExp e2= arithmeticExp )
                    {
                    match(input,28,FOLLOW_28_in_arithmeticExp906); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_arithmeticExp_in_arithmeticExp910);
                    e1=arithmeticExp();

                    state._fsp--;


                    pushFollow(FOLLOW_arithmeticExp_in_arithmeticExp914);
                    e2=arithmeticExp();

                    state._fsp--;


                    match(input, Token.UP, null); 


                    eval = this.evaluatorFactory.getPlusEvaluator (e1, e2);

                    }
                    break;
                case 2 :
                    // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMLanguage\\src\\main\\resources\\com\\mmpnc\\simlanguage\\grammar\\SimLanguageWalker.g:220:7: ^( SUBTRACT e1= arithmeticExp e2= arithmeticExp )
                    {
                    match(input,SUBTRACT,FOLLOW_SUBTRACT_in_arithmeticExp925); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_arithmeticExp_in_arithmeticExp929);
                    e1=arithmeticExp();

                    state._fsp--;


                    pushFollow(FOLLOW_arithmeticExp_in_arithmeticExp933);
                    e2=arithmeticExp();

                    state._fsp--;


                    match(input, Token.UP, null); 


                    eval = this.evaluatorFactory.getMinusEvaluator (e1, e2);

                    }
                    break;
                case 3 :
                    // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMLanguage\\src\\main\\resources\\com\\mmpnc\\simlanguage\\grammar\\SimLanguageWalker.g:221:7: ^( '*' e1= arithmeticExp e2= arithmeticExp )
                    {
                    match(input,27,FOLLOW_27_in_arithmeticExp944); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_arithmeticExp_in_arithmeticExp948);
                    e1=arithmeticExp();

                    state._fsp--;


                    pushFollow(FOLLOW_arithmeticExp_in_arithmeticExp952);
                    e2=arithmeticExp();

                    state._fsp--;


                    match(input, Token.UP, null); 


                    eval = this.evaluatorFactory.getMultiplyEvaluator (e1, e2);

                    }
                    break;
                case 4 :
                    // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMLanguage\\src\\main\\resources\\com\\mmpnc\\simlanguage\\grammar\\SimLanguageWalker.g:222:7: ^( '/' e1= arithmeticExp e2= arithmeticExp )
                    {
                    match(input,31,FOLLOW_31_in_arithmeticExp963); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_arithmeticExp_in_arithmeticExp967);
                    e1=arithmeticExp();

                    state._fsp--;


                    pushFollow(FOLLOW_arithmeticExp_in_arithmeticExp971);
                    e2=arithmeticExp();

                    state._fsp--;


                    match(input, Token.UP, null); 


                    eval = this.evaluatorFactory.getDivideEvaluator (e1, e2);

                    }
                    break;
                case 5 :
                    // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMLanguage\\src\\main\\resources\\com\\mmpnc\\simlanguage\\grammar\\SimLanguageWalker.g:223:7: ^( 'mod' e1= arithmeticExp e2= arithmeticExp )
                    {
                    match(input,57,FOLLOW_57_in_arithmeticExp982); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_arithmeticExp_in_arithmeticExp986);
                    e1=arithmeticExp();

                    state._fsp--;


                    pushFollow(FOLLOW_arithmeticExp_in_arithmeticExp990);
                    e2=arithmeticExp();

                    state._fsp--;


                    match(input, Token.UP, null); 


                    eval = this.evaluatorFactory.getModEvaluator (e1, e2);

                    }
                    break;
                case 6 :
                    // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMLanguage\\src\\main\\resources\\com\\mmpnc\\simlanguage\\grammar\\SimLanguageWalker.g:224:7: ^( STARTS e1= arithmeticExp e2= arithmeticExp )
                    {
                    match(input,STARTS,FOLLOW_STARTS_in_arithmeticExp1001); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_arithmeticExp_in_arithmeticExp1005);
                    e1=arithmeticExp();

                    state._fsp--;


                    pushFollow(FOLLOW_arithmeticExp_in_arithmeticExp1009);
                    e2=arithmeticExp();

                    state._fsp--;


                    match(input, Token.UP, null); 


                    eval = this.evaluatorFactory.getCustomOperatorEvaluator (e1, e2);

                    }
                    break;
                case 7 :
                    // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMLanguage\\src\\main\\resources\\com\\mmpnc\\simlanguage\\grammar\\SimLanguageWalker.g:225:7: ^( NEGATION e1= atom )
                    {
                    match(input,NEGATION,FOLLOW_NEGATION_in_arithmeticExp1021); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_atom_in_arithmeticExp1025);
                    e1=atom();

                    state._fsp--;


                    match(input, Token.UP, null); 


                    eval = this.evaluatorFactory.getUnaryNegationEvaluator(e1);

                    }
                    break;
                case 8 :
                    // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMLanguage\\src\\main\\resources\\com\\mmpnc\\simlanguage\\grammar\\SimLanguageWalker.g:226:7: e= atom
                    {
                    pushFollow(FOLLOW_atom_in_arithmeticExp1037);
                    e=atom();

                    state._fsp--;


                     eval = e;

                    }
                    break;
                case 9 :
                    // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMLanguage\\src\\main\\resources\\com\\mmpnc\\simlanguage\\grammar\\SimLanguageWalker.g:227:7: e= assignment
                    {
                    pushFollow(FOLLOW_assignment_in_arithmeticExp1056);
                    e=assignment();

                    state._fsp--;


                     eval = e;

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return eval;
    }
    // $ANTLR end "arithmeticExp"



    // $ANTLR start "booleanExp"
    // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMLanguage\\src\\main\\resources\\com\\mmpnc\\simlanguage\\grammar\\SimLanguageWalker.g:230:1: booleanExp returns [Evaluator eval] : ( ^( AND e1= booleanExp e2= booleanExp ) | ^( OR e1= booleanExp e2= booleanExp ) | ^( EQUALS e1= booleanExp e2= booleanExp ) | ^( NOTEQUALS e1= booleanExp e2= booleanExp ) | ^( '<' e1= booleanExp e2= booleanExp ) | ^( '<=' e1= booleanExp e2= booleanExp ) | ^( '>' e1= booleanExp e2= booleanExp ) | ^( '>=' e1= booleanExp e2= booleanExp ) | ^( NOT e1= booleanExp ) | arithmeticExp );
    public final Evaluator booleanExp() throws RecognitionException {
        Evaluator eval = null;


        Evaluator e1 =null;

        Evaluator e2 =null;

        Evaluator arithmeticExp6 =null;


        try {
            // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMLanguage\\src\\main\\resources\\com\\mmpnc\\simlanguage\\grammar\\SimLanguageWalker.g:231:5: ( ^( AND e1= booleanExp e2= booleanExp ) | ^( OR e1= booleanExp e2= booleanExp ) | ^( EQUALS e1= booleanExp e2= booleanExp ) | ^( NOTEQUALS e1= booleanExp e2= booleanExp ) | ^( '<' e1= booleanExp e2= booleanExp ) | ^( '<=' e1= booleanExp e2= booleanExp ) | ^( '>' e1= booleanExp e2= booleanExp ) | ^( '>=' e1= booleanExp e2= booleanExp ) | ^( NOT e1= booleanExp ) | arithmeticExp )
            int alt13=10;
            switch ( input.LA(1) ) {
            case AND:
                {
                alt13=1;
                }
                break;
            case OR:
                {
                alt13=2;
                }
                break;
            case EQUALS:
                {
                alt13=3;
                }
                break;
            case NOTEQUALS:
                {
                alt13=4;
                }
                break;
            case 33:
                {
                alt13=5;
                }
                break;
            case 34:
                {
                alt13=6;
                }
                break;
            case 37:
                {
                alt13=7;
                }
                break;
            case 38:
                {
                alt13=8;
                }
                break;
            case NOT:
                {
                alt13=9;
                }
                break;
            case IDENTS:
            case INTEGER:
            case NEGATION:
            case SPECIALFUNCTION:
            case STARTS:
            case STRING:
            case SUBTRACT:
            case VARCONSTANT:
            case XPATHCONSTANT:
            case 27:
            case 28:
            case 31:
            case 35:
            case 52:
            case 57:
                {
                alt13=10;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 13, 0, input);

                throw nvae;

            }

            switch (alt13) {
                case 1 :
                    // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMLanguage\\src\\main\\resources\\com\\mmpnc\\simlanguage\\grammar\\SimLanguageWalker.g:231:7: ^( AND e1= booleanExp e2= booleanExp )
                    {
                    match(input,AND,FOLLOW_AND_in_booleanExp1080); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_booleanExp_in_booleanExp1084);
                    e1=booleanExp();

                    state._fsp--;


                    pushFollow(FOLLOW_booleanExp_in_booleanExp1088);
                    e2=booleanExp();

                    state._fsp--;


                    match(input, Token.UP, null); 


                    eval = this.evaluatorFactory.getAndEvaluator (e1, e2);

                    }
                    break;
                case 2 :
                    // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMLanguage\\src\\main\\resources\\com\\mmpnc\\simlanguage\\grammar\\SimLanguageWalker.g:232:7: ^( OR e1= booleanExp e2= booleanExp )
                    {
                    match(input,OR,FOLLOW_OR_in_booleanExp1100); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_booleanExp_in_booleanExp1104);
                    e1=booleanExp();

                    state._fsp--;


                    pushFollow(FOLLOW_booleanExp_in_booleanExp1108);
                    e2=booleanExp();

                    state._fsp--;


                    match(input, Token.UP, null); 


                    eval = this.evaluatorFactory.getOrEvaluator (e1, e2);

                    }
                    break;
                case 3 :
                    // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMLanguage\\src\\main\\resources\\com\\mmpnc\\simlanguage\\grammar\\SimLanguageWalker.g:233:7: ^( EQUALS e1= booleanExp e2= booleanExp )
                    {
                    match(input,EQUALS,FOLLOW_EQUALS_in_booleanExp1119); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_booleanExp_in_booleanExp1123);
                    e1=booleanExp();

                    state._fsp--;


                    pushFollow(FOLLOW_booleanExp_in_booleanExp1127);
                    e2=booleanExp();

                    state._fsp--;


                    match(input, Token.UP, null); 


                    eval = this.evaluatorFactory.getEqualsEvaluator (e1, e2);

                    }
                    break;
                case 4 :
                    // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMLanguage\\src\\main\\resources\\com\\mmpnc\\simlanguage\\grammar\\SimLanguageWalker.g:234:7: ^( NOTEQUALS e1= booleanExp e2= booleanExp )
                    {
                    match(input,NOTEQUALS,FOLLOW_NOTEQUALS_in_booleanExp1138); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_booleanExp_in_booleanExp1142);
                    e1=booleanExp();

                    state._fsp--;


                    pushFollow(FOLLOW_booleanExp_in_booleanExp1146);
                    e2=booleanExp();

                    state._fsp--;


                    match(input, Token.UP, null); 


                    eval = this.evaluatorFactory.getNotEqualsEvaluator (e1, e2);

                    }
                    break;
                case 5 :
                    // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMLanguage\\src\\main\\resources\\com\\mmpnc\\simlanguage\\grammar\\SimLanguageWalker.g:235:7: ^( '<' e1= booleanExp e2= booleanExp )
                    {
                    match(input,33,FOLLOW_33_in_booleanExp1157); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_booleanExp_in_booleanExp1161);
                    e1=booleanExp();

                    state._fsp--;


                    pushFollow(FOLLOW_booleanExp_in_booleanExp1165);
                    e2=booleanExp();

                    state._fsp--;


                    match(input, Token.UP, null); 


                    eval = this.evaluatorFactory.getLessThanEvaluator (e1, e2);

                    }
                    break;
                case 6 :
                    // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMLanguage\\src\\main\\resources\\com\\mmpnc\\simlanguage\\grammar\\SimLanguageWalker.g:236:7: ^( '<=' e1= booleanExp e2= booleanExp )
                    {
                    match(input,34,FOLLOW_34_in_booleanExp1176); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_booleanExp_in_booleanExp1180);
                    e1=booleanExp();

                    state._fsp--;


                    pushFollow(FOLLOW_booleanExp_in_booleanExp1184);
                    e2=booleanExp();

                    state._fsp--;


                    match(input, Token.UP, null); 


                    eval = this.evaluatorFactory.getLessThanEqualsEvaluator (e1, e2);

                    }
                    break;
                case 7 :
                    // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMLanguage\\src\\main\\resources\\com\\mmpnc\\simlanguage\\grammar\\SimLanguageWalker.g:237:7: ^( '>' e1= booleanExp e2= booleanExp )
                    {
                    match(input,37,FOLLOW_37_in_booleanExp1195); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_booleanExp_in_booleanExp1199);
                    e1=booleanExp();

                    state._fsp--;


                    pushFollow(FOLLOW_booleanExp_in_booleanExp1203);
                    e2=booleanExp();

                    state._fsp--;


                    match(input, Token.UP, null); 


                    eval = this.evaluatorFactory.getGreaterThanEvaluator (e1, e2);

                    }
                    break;
                case 8 :
                    // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMLanguage\\src\\main\\resources\\com\\mmpnc\\simlanguage\\grammar\\SimLanguageWalker.g:238:7: ^( '>=' e1= booleanExp e2= booleanExp )
                    {
                    match(input,38,FOLLOW_38_in_booleanExp1214); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_booleanExp_in_booleanExp1218);
                    e1=booleanExp();

                    state._fsp--;


                    pushFollow(FOLLOW_booleanExp_in_booleanExp1222);
                    e2=booleanExp();

                    state._fsp--;


                    match(input, Token.UP, null); 


                    eval = this.evaluatorFactory.getGreaterThanEqualsEvaluator (e1, e2);

                    }
                    break;
                case 9 :
                    // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMLanguage\\src\\main\\resources\\com\\mmpnc\\simlanguage\\grammar\\SimLanguageWalker.g:239:7: ^( NOT e1= booleanExp )
                    {
                    match(input,NOT,FOLLOW_NOT_in_booleanExp1233); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_booleanExp_in_booleanExp1237);
                    e1=booleanExp();

                    state._fsp--;


                    match(input, Token.UP, null); 


                    eval = this.evaluatorFactory.getNotBooleanEvaluator (e1);

                    }
                    break;
                case 10 :
                    // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMLanguage\\src\\main\\resources\\com\\mmpnc\\simlanguage\\grammar\\SimLanguageWalker.g:240:7: arithmeticExp
                    {
                    pushFollow(FOLLOW_arithmeticExp_in_booleanExp1247);
                    arithmeticExp6=arithmeticExp();

                    state._fsp--;


                    eval = arithmeticExp6;

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return eval;
    }
    // $ANTLR end "booleanExp"

    // Delegated rules


 

    public static final BitSet FOLLOW_statements_in_block85 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_statement_in_statements150 = new BitSet(new long[]{0x0000B10800000002L});
    public static final BitSet FOLLOW_statement_in_statements157 = new BitSet(new long[]{0x0000B10800000002L});
    public static final BitSet FOLLOW_assignment_in_statement181 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ifStatement_in_statement185 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_loopStatement_in_statement189 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_executeProgram_in_statement193 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_attachProgram_in_statement197 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_44_in_executeProgram238 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_IDENTS_in_executeProgram243 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_xpath_in_executeProgram249 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_40_in_attachProgram283 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_IDENTS_in_attachProgram288 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_xpath_in_attachProgram294 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_35_in_assignment329 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_IDENTS_in_assignment334 = new BitSet(new long[]{0x02100008985F1600L});
    public static final BitSet FOLLOW_varType_in_assignment340 = new BitSet(new long[]{0x02100008985F1600L});
    public static final BitSet FOLLOW_xpath_in_assignment346 = new BitSet(new long[]{0x02100008985F1600L});
    public static final BitSet FOLLOW_arithmeticExp_in_assignment351 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_IDENTS_in_function395 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_arithmeticExp_in_function400 = new BitSet(new long[]{0x0000000020000008L});
    public static final BitSet FOLLOW_29_in_function405 = new BitSet(new long[]{0x02100008985F1600L});
    public static final BitSet FOLLOW_arithmeticExp_in_function409 = new BitSet(new long[]{0x0000000020000008L});
    public static final BitSet FOLLOW_SPECIALFUNCTION_in_specialFunction447 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_arithmeticExp_in_specialFunction451 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_52_in_rateFunction486 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_function_in_rateFunction490 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_45_in_ifStatement529 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_NOT_in_ifStatement534 = new BitSet(new long[]{0x0210006E985FF690L});
    public static final BitSet FOLLOW_booleanExp_in_ifStatement540 = new BitSet(new long[]{0x0000B10800000000L});
    public static final BitSet FOLLOW_statements_in_ifStatement544 = new BitSet(new long[]{0x0000020000000008L});
    public static final BitSet FOLLOW_41_in_ifStatement547 = new BitSet(new long[]{0x0000B10800000000L});
    public static final BitSet FOLLOW_statements_in_ifStatement551 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_47_in_loopStatement589 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_STRING_in_loopStatement593 = new BitSet(new long[]{0x0000B10800000000L});
    public static final BitSet FOLLOW_statements_in_loopStatement597 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_XPATHCONSTANT_in_xpath629 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_32_in_xpath631 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_STRING_in_xpath635 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VARCONSTANT_in_varType669 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_32_in_varType671 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_IDENTS_in_varType675 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENTS_in_atom716 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INTEGER_in_atom722 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_in_atom728 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_function_in_atom757 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rateFunction_in_atom785 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_xpath_in_atom812 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_varType_in_atom843 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_specialFunction_in_atom870 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_28_in_arithmeticExp906 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_arithmeticExp_in_arithmeticExp910 = new BitSet(new long[]{0x02100008985F1600L});
    public static final BitSet FOLLOW_arithmeticExp_in_arithmeticExp914 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_SUBTRACT_in_arithmeticExp925 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_arithmeticExp_in_arithmeticExp929 = new BitSet(new long[]{0x02100008985F1600L});
    public static final BitSet FOLLOW_arithmeticExp_in_arithmeticExp933 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_27_in_arithmeticExp944 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_arithmeticExp_in_arithmeticExp948 = new BitSet(new long[]{0x02100008985F1600L});
    public static final BitSet FOLLOW_arithmeticExp_in_arithmeticExp952 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_31_in_arithmeticExp963 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_arithmeticExp_in_arithmeticExp967 = new BitSet(new long[]{0x02100008985F1600L});
    public static final BitSet FOLLOW_arithmeticExp_in_arithmeticExp971 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_57_in_arithmeticExp982 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_arithmeticExp_in_arithmeticExp986 = new BitSet(new long[]{0x02100008985F1600L});
    public static final BitSet FOLLOW_arithmeticExp_in_arithmeticExp990 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_STARTS_in_arithmeticExp1001 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_arithmeticExp_in_arithmeticExp1005 = new BitSet(new long[]{0x02100008985F1600L});
    public static final BitSet FOLLOW_arithmeticExp_in_arithmeticExp1009 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_NEGATION_in_arithmeticExp1021 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_atom_in_arithmeticExp1025 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_atom_in_arithmeticExp1037 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_assignment_in_arithmeticExp1056 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_AND_in_booleanExp1080 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_booleanExp_in_booleanExp1084 = new BitSet(new long[]{0x0210006E985FF690L});
    public static final BitSet FOLLOW_booleanExp_in_booleanExp1088 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_OR_in_booleanExp1100 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_booleanExp_in_booleanExp1104 = new BitSet(new long[]{0x0210006E985FF690L});
    public static final BitSet FOLLOW_booleanExp_in_booleanExp1108 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_EQUALS_in_booleanExp1119 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_booleanExp_in_booleanExp1123 = new BitSet(new long[]{0x0210006E985FF690L});
    public static final BitSet FOLLOW_booleanExp_in_booleanExp1127 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_NOTEQUALS_in_booleanExp1138 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_booleanExp_in_booleanExp1142 = new BitSet(new long[]{0x0210006E985FF690L});
    public static final BitSet FOLLOW_booleanExp_in_booleanExp1146 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_33_in_booleanExp1157 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_booleanExp_in_booleanExp1161 = new BitSet(new long[]{0x0210006E985FF690L});
    public static final BitSet FOLLOW_booleanExp_in_booleanExp1165 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_34_in_booleanExp1176 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_booleanExp_in_booleanExp1180 = new BitSet(new long[]{0x0210006E985FF690L});
    public static final BitSet FOLLOW_booleanExp_in_booleanExp1184 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_37_in_booleanExp1195 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_booleanExp_in_booleanExp1199 = new BitSet(new long[]{0x0210006E985FF690L});
    public static final BitSet FOLLOW_booleanExp_in_booleanExp1203 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_38_in_booleanExp1214 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_booleanExp_in_booleanExp1218 = new BitSet(new long[]{0x0210006E985FF690L});
    public static final BitSet FOLLOW_booleanExp_in_booleanExp1222 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_NOT_in_booleanExp1233 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_booleanExp_in_booleanExp1237 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_arithmeticExp_in_booleanExp1247 = new BitSet(new long[]{0x0000000000000002L});

}