// $ANTLR 3.4 C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRatingDataImport\\src\\main\\resources\\com\\mmpnc\\rating\\iso\\algorithm\\dataimport\\isoalgorithmwalker.g 2014-12-02 18:56:26

  package com.mmpnc.rating.iso.algorithm.dataimport;
  
  import com.mmpnc.context.Context;
  import com.mmpnc.rating.icm.algorithm.AlgorithmEvaluator;
  import com.mmpnc.rating.icm.algorithm.evaluator.*;
  import com.mmpnc.rating.icm.algorithm.vo.*;


import org.antlr.runtime.*;
import org.antlr.runtime.tree.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked"})
public class isoalgorithmwalker extends TreeParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "COMMENTS", "EXPONENT", "IDENTS", "INTEGER", "MULTI_COMMENT", "STRING", "WS", "','", "':'", "'ARG'", "'ASSIGN'", "'BRACKET'", "'CLASS'", "'CONDITION'", "'ELSE'", "'ERROR'", "'EXPRESSION'", "'FORMATTACHMENT'", "'FUNCTION'", "'IF'", "'LOOP'", "'RATETABLE'", "'RATING'", "'STAT'", "'THEN'", "'program'", "'{'", "'}'"
    };

    public static final int EOF=-1;
    public static final int T__11=11;
    public static final int T__12=12;
    public static final int T__13=13;
    public static final int T__14=14;
    public static final int T__15=15;
    public static final int T__16=16;
    public static final int T__17=17;
    public static final int T__18=18;
    public static final int T__19=19;
    public static final int T__20=20;
    public static final int T__21=21;
    public static final int T__22=22;
    public static final int T__23=23;
    public static final int T__24=24;
    public static final int T__25=25;
    public static final int T__26=26;
    public static final int T__27=27;
    public static final int T__28=28;
    public static final int T__29=29;
    public static final int T__30=30;
    public static final int T__31=31;
    public static final int COMMENTS=4;
    public static final int EXPONENT=5;
    public static final int IDENTS=6;
    public static final int INTEGER=7;
    public static final int MULTI_COMMENT=8;
    public static final int STRING=9;
    public static final int WS=10;

    // delegates
    public TreeParser[] getDelegates() {
        return new TreeParser[] {};
    }

    // delegators


    public isoalgorithmwalker(TreeNodeStream input) {
        this(input, new RecognizerSharedState());
    }
    public isoalgorithmwalker(TreeNodeStream input, RecognizerSharedState state) {
        super(input, state);
    }

    public String[] getTokenNames() { return isoalgorithmwalker.tokenNames; }
    public String getGrammarFileName() { return "C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRatingDataImport\\src\\main\\resources\\com\\mmpnc\\rating\\iso\\algorithm\\dataimport\\isoalgorithmwalker.g"; }


      private Context context;
      private List<AlgorithmEvaluator> evals;
      
      public void setContext(Context context) {
        this.context = context;
      }



    // $ANTLR start "block"
    // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRatingDataImport\\src\\main\\resources\\com\\mmpnc\\rating\\iso\\algorithm\\dataimport\\isoalgorithmwalker.g:38:2: block returns [StringBuffer buffer] : (p= program |s= statement |c= classnode ) ;
    public final StringBuffer block() throws RecognitionException {
        StringBuffer buffer = null;


        AlgorithmEvaluator p =null;

        AlgorithmEvaluator s =null;

        AlgorithmEvaluator c =null;


        try {
            // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRatingDataImport\\src\\main\\resources\\com\\mmpnc\\rating\\iso\\algorithm\\dataimport\\isoalgorithmwalker.g:38:37: ( (p= program |s= statement |c= classnode ) )
            // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRatingDataImport\\src\\main\\resources\\com\\mmpnc\\rating\\iso\\algorithm\\dataimport\\isoalgorithmwalker.g:38:39: (p= program |s= statement |c= classnode )
            {
             buffer = new StringBuffer();

            // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRatingDataImport\\src\\main\\resources\\com\\mmpnc\\rating\\iso\\algorithm\\dataimport\\isoalgorithmwalker.g:38:71: (p= program |s= statement |c= classnode )
            int alt1=3;
            switch ( input.LA(1) ) {
            case 29:
                {
                alt1=1;
                }
                break;
            case 14:
            case 23:
            case 24:
                {
                alt1=2;
                }
                break;
            case 16:
                {
                alt1=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 1, 0, input);

                throw nvae;

            }

            switch (alt1) {
                case 1 :
                    // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRatingDataImport\\src\\main\\resources\\com\\mmpnc\\rating\\iso\\algorithm\\dataimport\\isoalgorithmwalker.g:38:72: p= program
                    {
                    pushFollow(FOLLOW_program_in_block69);
                    p=program();

                    state._fsp--;


                    }
                    break;
                case 2 :
                    // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRatingDataImport\\src\\main\\resources\\com\\mmpnc\\rating\\iso\\algorithm\\dataimport\\isoalgorithmwalker.g:38:84: s= statement
                    {
                    pushFollow(FOLLOW_statement_in_block75);
                    s=statement();

                    state._fsp--;


                    }
                    break;
                case 3 :
                    // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRatingDataImport\\src\\main\\resources\\com\\mmpnc\\rating\\iso\\algorithm\\dataimport\\isoalgorithmwalker.g:38:98: c= classnode
                    {
                    pushFollow(FOLLOW_classnode_in_block81);
                    c=classnode();

                    state._fsp--;


                    }
                    break;

            }



                      evals = new ArrayList<AlgorithmEvaluator>();
                      
                      if(p != null){
                        p.setContext(context);
                        evals.add(p);
                        buffer.append(p.evaluate());
                      } else if ( s != null) {
                        s.setContext(context);
                        evals.add(s);
                        buffer.append(s.evaluate());
                      } else {
                        c.setContext(context);
                        evals.add(c);
                        buffer.append(c.evaluate());
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
        return buffer;
    }
    // $ANTLR end "block"



    // $ANTLR start "classnode"
    // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRatingDataImport\\src\\main\\resources\\com\\mmpnc\\rating\\iso\\algorithm\\dataimport\\isoalgorithmwalker.g:58:2: classnode returns [AlgorithmEvaluator eval] : ^( 'CLASS' k= keyvalues c= childnodes ) ;
    public final AlgorithmEvaluator classnode() throws RecognitionException {
        AlgorithmEvaluator eval = null;


        List<AlgorithmEvaluator> k =null;

        List<AlgorithmEvaluator> c =null;


        try {
            // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRatingDataImport\\src\\main\\resources\\com\\mmpnc\\rating\\iso\\algorithm\\dataimport\\isoalgorithmwalker.g:58:45: ( ^( 'CLASS' k= keyvalues c= childnodes ) )
            // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRatingDataImport\\src\\main\\resources\\com\\mmpnc\\rating\\iso\\algorithm\\dataimport\\isoalgorithmwalker.g:58:47: ^( 'CLASS' k= keyvalues c= childnodes )
            {
            match(input,16,FOLLOW_16_in_classnode114); 

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); 
                pushFollow(FOLLOW_keyvalues_in_classnode118);
                k=keyvalues();

                state._fsp--;


                pushFollow(FOLLOW_childnodes_in_classnode122);
                c=childnodes();

                state._fsp--;


                 eval = new ClassEvaluator(k, c); 

                match(input, Token.UP, null); 
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
    // $ANTLR end "classnode"



    // $ANTLR start "childnodes"
    // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRatingDataImport\\src\\main\\resources\\com\\mmpnc\\rating\\iso\\algorithm\\dataimport\\isoalgorithmwalker.g:60:2: childnodes returns [List<AlgorithmEvaluator> evals] : (c= childnode )* ;
    public final List<AlgorithmEvaluator> childnodes() throws RecognitionException {
        List<AlgorithmEvaluator> evals = null;


        AlgorithmEvaluator c =null;


        try {
            // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRatingDataImport\\src\\main\\resources\\com\\mmpnc\\rating\\iso\\algorithm\\dataimport\\isoalgorithmwalker.g:60:53: ( (c= childnode )* )
            // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRatingDataImport\\src\\main\\resources\\com\\mmpnc\\rating\\iso\\algorithm\\dataimport\\isoalgorithmwalker.g:60:55: (c= childnode )*
            {
             evals = new ArrayList<AlgorithmEvaluator>(); 

            // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRatingDataImport\\src\\main\\resources\\com\\mmpnc\\rating\\iso\\algorithm\\dataimport\\isoalgorithmwalker.g:60:103: (c= childnode )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==16||LA2_0==19||LA2_0==21||(LA2_0 >= 26 && LA2_0 <= 27)) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRatingDataImport\\src\\main\\resources\\com\\mmpnc\\rating\\iso\\algorithm\\dataimport\\isoalgorithmwalker.g:60:104: c= childnode
            	    {
            	    pushFollow(FOLLOW_childnode_in_childnodes142);
            	    c=childnode();

            	    state._fsp--;


            	     evals.add(c);

            	    }
            	    break;

            	default :
            	    break loop2;
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
        return evals;
    }
    // $ANTLR end "childnodes"



    // $ANTLR start "childnode"
    // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRatingDataImport\\src\\main\\resources\\com\\mmpnc\\rating\\iso\\algorithm\\dataimport\\isoalgorithmwalker.g:62:2: childnode returns [AlgorithmEvaluator eval] : (s= stat |r= rating |e= error |f= formattach |c= classnode ) ;
    public final AlgorithmEvaluator childnode() throws RecognitionException {
        AlgorithmEvaluator eval = null;


        AlgorithmEvaluator s =null;

        AlgorithmEvaluator r =null;

        AlgorithmEvaluator e =null;

        AlgorithmEvaluator f =null;

        AlgorithmEvaluator c =null;


        try {
            // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRatingDataImport\\src\\main\\resources\\com\\mmpnc\\rating\\iso\\algorithm\\dataimport\\isoalgorithmwalker.g:62:45: ( (s= stat |r= rating |e= error |f= formattach |c= classnode ) )
            // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRatingDataImport\\src\\main\\resources\\com\\mmpnc\\rating\\iso\\algorithm\\dataimport\\isoalgorithmwalker.g:62:47: (s= stat |r= rating |e= error |f= formattach |c= classnode )
            {
            // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRatingDataImport\\src\\main\\resources\\com\\mmpnc\\rating\\iso\\algorithm\\dataimport\\isoalgorithmwalker.g:62:47: (s= stat |r= rating |e= error |f= formattach |c= classnode )
            int alt3=5;
            switch ( input.LA(1) ) {
            case 27:
                {
                alt3=1;
                }
                break;
            case 26:
                {
                alt3=2;
                }
                break;
            case 19:
                {
                alt3=3;
                }
                break;
            case 21:
                {
                alt3=4;
                }
                break;
            case 16:
                {
                alt3=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;

            }

            switch (alt3) {
                case 1 :
                    // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRatingDataImport\\src\\main\\resources\\com\\mmpnc\\rating\\iso\\algorithm\\dataimport\\isoalgorithmwalker.g:62:48: s= stat
                    {
                    pushFollow(FOLLOW_stat_in_childnode162);
                    s=stat();

                    state._fsp--;


                    }
                    break;
                case 2 :
                    // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRatingDataImport\\src\\main\\resources\\com\\mmpnc\\rating\\iso\\algorithm\\dataimport\\isoalgorithmwalker.g:62:57: r= rating
                    {
                    pushFollow(FOLLOW_rating_in_childnode168);
                    r=rating();

                    state._fsp--;


                    }
                    break;
                case 3 :
                    // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRatingDataImport\\src\\main\\resources\\com\\mmpnc\\rating\\iso\\algorithm\\dataimport\\isoalgorithmwalker.g:62:68: e= error
                    {
                    pushFollow(FOLLOW_error_in_childnode174);
                    e=error();

                    state._fsp--;


                    }
                    break;
                case 4 :
                    // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRatingDataImport\\src\\main\\resources\\com\\mmpnc\\rating\\iso\\algorithm\\dataimport\\isoalgorithmwalker.g:62:78: f= formattach
                    {
                    pushFollow(FOLLOW_formattach_in_childnode180);
                    f=formattach();

                    state._fsp--;


                    }
                    break;
                case 5 :
                    // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRatingDataImport\\src\\main\\resources\\com\\mmpnc\\rating\\iso\\algorithm\\dataimport\\isoalgorithmwalker.g:62:93: c= classnode
                    {
                    pushFollow(FOLLOW_classnode_in_childnode186);
                    c=classnode();

                    state._fsp--;


                    }
                    break;

            }



                        if(s != null){
                          eval = s;
                        } else if ( r != null){
                          eval = r;
                        } else if ( e != null){
                          eval = e;
                        } else if ( f != null){
                          eval = f;
                        } else {
                          eval = c;
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
    // $ANTLR end "childnode"



    // $ANTLR start "stat"
    // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRatingDataImport\\src\\main\\resources\\com\\mmpnc\\rating\\iso\\algorithm\\dataimport\\isoalgorithmwalker.g:77:2: stat returns [AlgorithmEvaluator eval] : ^( 'STAT' k= keyvalues ) ;
    public final AlgorithmEvaluator stat() throws RecognitionException {
        AlgorithmEvaluator eval = null;


        List<AlgorithmEvaluator> k =null;


        try {
            // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRatingDataImport\\src\\main\\resources\\com\\mmpnc\\rating\\iso\\algorithm\\dataimport\\isoalgorithmwalker.g:77:40: ( ^( 'STAT' k= keyvalues ) )
            // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRatingDataImport\\src\\main\\resources\\com\\mmpnc\\rating\\iso\\algorithm\\dataimport\\isoalgorithmwalker.g:77:42: ^( 'STAT' k= keyvalues )
            {
            match(input,27,FOLLOW_27_in_stat213); 

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); 
                pushFollow(FOLLOW_keyvalues_in_stat217);
                k=keyvalues();

                state._fsp--;


                 eval = new StatEvaluator(k); 

                match(input, Token.UP, null); 
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
    // $ANTLR end "stat"



    // $ANTLR start "rating"
    // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRatingDataImport\\src\\main\\resources\\com\\mmpnc\\rating\\iso\\algorithm\\dataimport\\isoalgorithmwalker.g:79:2: rating returns [AlgorithmEvaluator eval] : ^( 'RATING' k= keyvalues ) ;
    public final AlgorithmEvaluator rating() throws RecognitionException {
        AlgorithmEvaluator eval = null;


        List<AlgorithmEvaluator> k =null;


        try {
            // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRatingDataImport\\src\\main\\resources\\com\\mmpnc\\rating\\iso\\algorithm\\dataimport\\isoalgorithmwalker.g:79:42: ( ^( 'RATING' k= keyvalues ) )
            // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRatingDataImport\\src\\main\\resources\\com\\mmpnc\\rating\\iso\\algorithm\\dataimport\\isoalgorithmwalker.g:79:44: ^( 'RATING' k= keyvalues )
            {
            match(input,26,FOLLOW_26_in_rating233); 

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); 
                pushFollow(FOLLOW_keyvalues_in_rating237);
                k=keyvalues();

                state._fsp--;


                 eval = new RatingEvaluator(k); 

                match(input, Token.UP, null); 
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
    // $ANTLR end "rating"



    // $ANTLR start "error"
    // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRatingDataImport\\src\\main\\resources\\com\\mmpnc\\rating\\iso\\algorithm\\dataimport\\isoalgorithmwalker.g:81:2: error returns [AlgorithmEvaluator eval] : ^( 'ERROR' k= keyvalues ) ;
    public final AlgorithmEvaluator error() throws RecognitionException {
        AlgorithmEvaluator eval = null;


        List<AlgorithmEvaluator> k =null;


        try {
            // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRatingDataImport\\src\\main\\resources\\com\\mmpnc\\rating\\iso\\algorithm\\dataimport\\isoalgorithmwalker.g:81:41: ( ^( 'ERROR' k= keyvalues ) )
            // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRatingDataImport\\src\\main\\resources\\com\\mmpnc\\rating\\iso\\algorithm\\dataimport\\isoalgorithmwalker.g:81:43: ^( 'ERROR' k= keyvalues )
            {
            match(input,19,FOLLOW_19_in_error253); 

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); 
                pushFollow(FOLLOW_keyvalues_in_error257);
                k=keyvalues();

                state._fsp--;


                 eval = new ErrorEvaluator(k); 

                match(input, Token.UP, null); 
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
    // $ANTLR end "error"



    // $ANTLR start "formattach"
    // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRatingDataImport\\src\\main\\resources\\com\\mmpnc\\rating\\iso\\algorithm\\dataimport\\isoalgorithmwalker.g:83:2: formattach returns [AlgorithmEvaluator eval] : ^( 'FORMATTACHMENT' k= keyvalues ) ;
    public final AlgorithmEvaluator formattach() throws RecognitionException {
        AlgorithmEvaluator eval = null;


        List<AlgorithmEvaluator> k =null;


        try {
            // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRatingDataImport\\src\\main\\resources\\com\\mmpnc\\rating\\iso\\algorithm\\dataimport\\isoalgorithmwalker.g:83:46: ( ^( 'FORMATTACHMENT' k= keyvalues ) )
            // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRatingDataImport\\src\\main\\resources\\com\\mmpnc\\rating\\iso\\algorithm\\dataimport\\isoalgorithmwalker.g:83:48: ^( 'FORMATTACHMENT' k= keyvalues )
            {
            match(input,21,FOLLOW_21_in_formattach273); 

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); 
                pushFollow(FOLLOW_keyvalues_in_formattach277);
                k=keyvalues();

                state._fsp--;


                 eval = new FormAttachEvaluator(k); 

                match(input, Token.UP, null); 
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
    // $ANTLR end "formattach"



    // $ANTLR start "program"
    // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRatingDataImport\\src\\main\\resources\\com\\mmpnc\\rating\\iso\\algorithm\\dataimport\\isoalgorithmwalker.g:85:2: program returns [AlgorithmEvaluator eval] : ^( 'program' k= keyvalues s= statements ) ;
    public final AlgorithmEvaluator program() throws RecognitionException {
        AlgorithmEvaluator eval = null;


        List<AlgorithmEvaluator> k =null;

        List<AlgorithmEvaluator> s =null;


        try {
            // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRatingDataImport\\src\\main\\resources\\com\\mmpnc\\rating\\iso\\algorithm\\dataimport\\isoalgorithmwalker.g:85:43: ( ^( 'program' k= keyvalues s= statements ) )
            // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRatingDataImport\\src\\main\\resources\\com\\mmpnc\\rating\\iso\\algorithm\\dataimport\\isoalgorithmwalker.g:85:45: ^( 'program' k= keyvalues s= statements )
            {
            match(input,29,FOLLOW_29_in_program294); 

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); 
                pushFollow(FOLLOW_keyvalues_in_program298);
                k=keyvalues();

                state._fsp--;


                pushFollow(FOLLOW_statements_in_program302);
                s=statements();

                state._fsp--;


                 eval = new ProgramAlgorithmEvaluator(k, s); 

                match(input, Token.UP, null); 
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
    // $ANTLR end "program"



    // $ANTLR start "statements"
    // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRatingDataImport\\src\\main\\resources\\com\\mmpnc\\rating\\iso\\algorithm\\dataimport\\isoalgorithmwalker.g:87:2: statements returns [List<AlgorithmEvaluator> evals] : (s= statement )* ;
    public final List<AlgorithmEvaluator> statements() throws RecognitionException {
        List<AlgorithmEvaluator> evals = null;


        AlgorithmEvaluator s =null;


        try {
            // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRatingDataImport\\src\\main\\resources\\com\\mmpnc\\rating\\iso\\algorithm\\dataimport\\isoalgorithmwalker.g:87:53: ( (s= statement )* )
            // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRatingDataImport\\src\\main\\resources\\com\\mmpnc\\rating\\iso\\algorithm\\dataimport\\isoalgorithmwalker.g:87:55: (s= statement )*
            {
             evals = new ArrayList<AlgorithmEvaluator>(); 

            // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRatingDataImport\\src\\main\\resources\\com\\mmpnc\\rating\\iso\\algorithm\\dataimport\\isoalgorithmwalker.g:87:103: (s= statement )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0==14||(LA4_0 >= 23 && LA4_0 <= 24)) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRatingDataImport\\src\\main\\resources\\com\\mmpnc\\rating\\iso\\algorithm\\dataimport\\isoalgorithmwalker.g:87:104: s= statement
            	    {
            	    pushFollow(FOLLOW_statement_in_statements322);
            	    s=statement();

            	    state._fsp--;


            	     evals.add(s);

            	    }
            	    break;

            	default :
            	    break loop4;
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
        return evals;
    }
    // $ANTLR end "statements"



    // $ANTLR start "statement"
    // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRatingDataImport\\src\\main\\resources\\com\\mmpnc\\rating\\iso\\algorithm\\dataimport\\isoalgorithmwalker.g:89:2: statement returns [AlgorithmEvaluator eval] : (i= iif |l= loop |a= assign ) ;
    public final AlgorithmEvaluator statement() throws RecognitionException {
        AlgorithmEvaluator eval = null;


        AlgorithmEvaluator i =null;

        AlgorithmEvaluator l =null;

        AlgorithmEvaluator a =null;


        try {
            // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRatingDataImport\\src\\main\\resources\\com\\mmpnc\\rating\\iso\\algorithm\\dataimport\\isoalgorithmwalker.g:89:45: ( (i= iif |l= loop |a= assign ) )
            // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRatingDataImport\\src\\main\\resources\\com\\mmpnc\\rating\\iso\\algorithm\\dataimport\\isoalgorithmwalker.g:89:47: (i= iif |l= loop |a= assign )
            {
            // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRatingDataImport\\src\\main\\resources\\com\\mmpnc\\rating\\iso\\algorithm\\dataimport\\isoalgorithmwalker.g:89:47: (i= iif |l= loop |a= assign )
            int alt5=3;
            switch ( input.LA(1) ) {
            case 23:
                {
                alt5=1;
                }
                break;
            case 24:
                {
                alt5=2;
                }
                break;
            case 14:
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
                    // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRatingDataImport\\src\\main\\resources\\com\\mmpnc\\rating\\iso\\algorithm\\dataimport\\isoalgorithmwalker.g:89:48: i= iif
                    {
                    pushFollow(FOLLOW_iif_in_statement342);
                    i=iif();

                    state._fsp--;


                    }
                    break;
                case 2 :
                    // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRatingDataImport\\src\\main\\resources\\com\\mmpnc\\rating\\iso\\algorithm\\dataimport\\isoalgorithmwalker.g:89:56: l= loop
                    {
                    pushFollow(FOLLOW_loop_in_statement348);
                    l=loop();

                    state._fsp--;


                    }
                    break;
                case 3 :
                    // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRatingDataImport\\src\\main\\resources\\com\\mmpnc\\rating\\iso\\algorithm\\dataimport\\isoalgorithmwalker.g:89:65: a= assign
                    {
                    pushFollow(FOLLOW_assign_in_statement354);
                    a=assign();

                    state._fsp--;


                    }
                    break;

            }



                                        if(i != null){
                                          eval = i;
                                        } else if ( l != null){
                                          eval = l;
                                        } else {
                                          eval = a;
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



    // $ANTLR start "iif"
    // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRatingDataImport\\src\\main\\resources\\com\\mmpnc\\rating\\iso\\algorithm\\dataimport\\isoalgorithmwalker.g:100:2: iif returns [AlgorithmEvaluator eval] : ^( 'IF' c= condition t= then (e= eelse )? ) ;
    public final AlgorithmEvaluator iif() throws RecognitionException {
        AlgorithmEvaluator eval = null;


        AlgorithmEvaluator c =null;

        AlgorithmEvaluator t =null;

        AlgorithmEvaluator e =null;


        try {
            // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRatingDataImport\\src\\main\\resources\\com\\mmpnc\\rating\\iso\\algorithm\\dataimport\\isoalgorithmwalker.g:100:38: ( ^( 'IF' c= condition t= then (e= eelse )? ) )
            // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRatingDataImport\\src\\main\\resources\\com\\mmpnc\\rating\\iso\\algorithm\\dataimport\\isoalgorithmwalker.g:100:40: ^( 'IF' c= condition t= then (e= eelse )? )
            {
            match(input,23,FOLLOW_23_in_iif396); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_condition_in_iif400);
            c=condition();

            state._fsp--;


            pushFollow(FOLLOW_then_in_iif404);
            t=then();

            state._fsp--;


            // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRatingDataImport\\src\\main\\resources\\com\\mmpnc\\rating\\iso\\algorithm\\dataimport\\isoalgorithmwalker.g:100:67: (e= eelse )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==18) ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRatingDataImport\\src\\main\\resources\\com\\mmpnc\\rating\\iso\\algorithm\\dataimport\\isoalgorithmwalker.g:100:67: e= eelse
                    {
                    pushFollow(FOLLOW_eelse_in_iif408);
                    e=eelse();

                    state._fsp--;


                    }
                    break;

            }


            match(input, Token.UP, null); 



                                        eval = new IfAlgorithmEvaluator(c,t,e);
                                      

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
    // $ANTLR end "iif"



    // $ANTLR start "condition"
    // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRatingDataImport\\src\\main\\resources\\com\\mmpnc\\rating\\iso\\algorithm\\dataimport\\isoalgorithmwalker.g:105:2: condition returns [AlgorithmEvaluator eval] : ^( 'CONDITION' e= expression ) ;
    public final AlgorithmEvaluator condition() throws RecognitionException {
        AlgorithmEvaluator eval = null;


        AlgorithmEvaluator e =null;


        try {
            // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRatingDataImport\\src\\main\\resources\\com\\mmpnc\\rating\\iso\\algorithm\\dataimport\\isoalgorithmwalker.g:105:45: ( ^( 'CONDITION' e= expression ) )
            // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRatingDataImport\\src\\main\\resources\\com\\mmpnc\\rating\\iso\\algorithm\\dataimport\\isoalgorithmwalker.g:105:47: ^( 'CONDITION' e= expression )
            {
            match(input,17,FOLLOW_17_in_condition452); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_expression_in_condition456);
            e=expression();

            state._fsp--;


            match(input, Token.UP, null); 



                                        eval = new ConditionAlgorithmEvaluator(e);
                                      

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
    // $ANTLR end "condition"



    // $ANTLR start "then"
    // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRatingDataImport\\src\\main\\resources\\com\\mmpnc\\rating\\iso\\algorithm\\dataimport\\isoalgorithmwalker.g:110:2: then returns [AlgorithmEvaluator eval] : ^( 'THEN' s= statements ) ;
    public final AlgorithmEvaluator then() throws RecognitionException {
        AlgorithmEvaluator eval = null;


        List<AlgorithmEvaluator> s =null;


        try {
            // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRatingDataImport\\src\\main\\resources\\com\\mmpnc\\rating\\iso\\algorithm\\dataimport\\isoalgorithmwalker.g:110:40: ( ^( 'THEN' s= statements ) )
            // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRatingDataImport\\src\\main\\resources\\com\\mmpnc\\rating\\iso\\algorithm\\dataimport\\isoalgorithmwalker.g:110:42: ^( 'THEN' s= statements )
            {
            match(input,28,FOLLOW_28_in_then499); 

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); 
                pushFollow(FOLLOW_statements_in_then503);
                s=statements();

                state._fsp--;


                match(input, Token.UP, null); 
            }



                                        eval = new ThenAlgorithmEvaluator(s);
                                      

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
    // $ANTLR end "then"



    // $ANTLR start "eelse"
    // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRatingDataImport\\src\\main\\resources\\com\\mmpnc\\rating\\iso\\algorithm\\dataimport\\isoalgorithmwalker.g:115:2: eelse returns [AlgorithmEvaluator eval] : ^( 'ELSE' s= statements ) ;
    public final AlgorithmEvaluator eelse() throws RecognitionException {
        AlgorithmEvaluator eval = null;


        List<AlgorithmEvaluator> s =null;


        try {
            // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRatingDataImport\\src\\main\\resources\\com\\mmpnc\\rating\\iso\\algorithm\\dataimport\\isoalgorithmwalker.g:115:41: ( ^( 'ELSE' s= statements ) )
            // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRatingDataImport\\src\\main\\resources\\com\\mmpnc\\rating\\iso\\algorithm\\dataimport\\isoalgorithmwalker.g:115:43: ^( 'ELSE' s= statements )
            {
            match(input,18,FOLLOW_18_in_eelse546); 

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); 
                pushFollow(FOLLOW_statements_in_eelse550);
                s=statements();

                state._fsp--;


                match(input, Token.UP, null); 
            }



                                        eval = new ElseAlgorithmEvaluator(s);
                                      

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
    // $ANTLR end "eelse"



    // $ANTLR start "loop"
    // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRatingDataImport\\src\\main\\resources\\com\\mmpnc\\rating\\iso\\algorithm\\dataimport\\isoalgorithmwalker.g:120:2: loop returns [AlgorithmEvaluator eval] : ^( 'LOOP' k= keyvalue s= statements ) ;
    public final AlgorithmEvaluator loop() throws RecognitionException {
        AlgorithmEvaluator eval = null;


        AlgorithmEvaluator k =null;

        List<AlgorithmEvaluator> s =null;


        try {
            // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRatingDataImport\\src\\main\\resources\\com\\mmpnc\\rating\\iso\\algorithm\\dataimport\\isoalgorithmwalker.g:120:40: ( ^( 'LOOP' k= keyvalue s= statements ) )
            // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRatingDataImport\\src\\main\\resources\\com\\mmpnc\\rating\\iso\\algorithm\\dataimport\\isoalgorithmwalker.g:120:42: ^( 'LOOP' k= keyvalue s= statements )
            {
            match(input,24,FOLLOW_24_in_loop593); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_keyvalue_in_loop597);
            k=keyvalue();

            state._fsp--;


            pushFollow(FOLLOW_statements_in_loop601);
            s=statements();

            state._fsp--;


            match(input, Token.UP, null); 



                                        eval = new LoopAlgorithmEvaluator(k,s);
                                      

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
    // $ANTLR end "loop"



    // $ANTLR start "assign"
    // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRatingDataImport\\src\\main\\resources\\com\\mmpnc\\rating\\iso\\algorithm\\dataimport\\isoalgorithmwalker.g:126:2: assign returns [AlgorithmEvaluator eval] : ^( 'ASSIGN' k= keyvalues e= expression ) ;
    public final AlgorithmEvaluator assign() throws RecognitionException {
        AlgorithmEvaluator eval = null;


        List<AlgorithmEvaluator> k =null;

        AlgorithmEvaluator e =null;


        try {
            // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRatingDataImport\\src\\main\\resources\\com\\mmpnc\\rating\\iso\\algorithm\\dataimport\\isoalgorithmwalker.g:126:42: ( ^( 'ASSIGN' k= keyvalues e= expression ) )
            // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRatingDataImport\\src\\main\\resources\\com\\mmpnc\\rating\\iso\\algorithm\\dataimport\\isoalgorithmwalker.g:126:44: ^( 'ASSIGN' k= keyvalues e= expression )
            {
            match(input,14,FOLLOW_14_in_assign671); 

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); 
                pushFollow(FOLLOW_keyvalues_in_assign675);
                k=keyvalues();

                state._fsp--;


                pushFollow(FOLLOW_expression_in_assign679);
                e=expression();

                state._fsp--;


                match(input, Token.UP, null); 
            }



                                        eval = new AssignAlgorithmEvaluator(k,e);
                                      

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
    // $ANTLR end "assign"



    // $ANTLR start "expression"
    // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRatingDataImport\\src\\main\\resources\\com\\mmpnc\\rating\\iso\\algorithm\\dataimport\\isoalgorithmwalker.g:132:2: expression returns [AlgorithmEvaluator eval] : ^( 'EXPRESSION' k= keyvalues e= expressions ) ;
    public final AlgorithmEvaluator expression() throws RecognitionException {
        AlgorithmEvaluator eval = null;


        List<AlgorithmEvaluator> k =null;

        List<AlgorithmEvaluator> e =null;


        try {
            // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRatingDataImport\\src\\main\\resources\\com\\mmpnc\\rating\\iso\\algorithm\\dataimport\\isoalgorithmwalker.g:132:46: ( ^( 'EXPRESSION' k= keyvalues e= expressions ) )
            // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRatingDataImport\\src\\main\\resources\\com\\mmpnc\\rating\\iso\\algorithm\\dataimport\\isoalgorithmwalker.g:132:48: ^( 'EXPRESSION' k= keyvalues e= expressions )
            {
            match(input,20,FOLLOW_20_in_expression751); 

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); 
                pushFollow(FOLLOW_keyvalues_in_expression755);
                k=keyvalues();

                state._fsp--;


                pushFollow(FOLLOW_expressions_in_expression759);
                e=expressions();

                state._fsp--;


                match(input, Token.UP, null); 
            }


            eval = new ExpressionAlgorithmEvaluator(k, e); eval.setContext(this.context);

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
    // $ANTLR end "expression"



    // $ANTLR start "expressions"
    // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRatingDataImport\\src\\main\\resources\\com\\mmpnc\\rating\\iso\\algorithm\\dataimport\\isoalgorithmwalker.g:136:2: expressions returns [List<AlgorithmEvaluator> evals] : ( (e= expression |b= bracket |r= ratetable |f= function ) )* ;
    public final List<AlgorithmEvaluator> expressions() throws RecognitionException {
        List<AlgorithmEvaluator> evals = null;


        AlgorithmEvaluator e =null;

        AlgorithmEvaluator b =null;

        AlgorithmEvaluator r =null;

        AlgorithmEvaluator f =null;


        try {
            // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRatingDataImport\\src\\main\\resources\\com\\mmpnc\\rating\\iso\\algorithm\\dataimport\\isoalgorithmwalker.g:136:54: ( ( (e= expression |b= bracket |r= ratetable |f= function ) )* )
            // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRatingDataImport\\src\\main\\resources\\com\\mmpnc\\rating\\iso\\algorithm\\dataimport\\isoalgorithmwalker.g:136:56: ( (e= expression |b= bracket |r= ratetable |f= function ) )*
            {
            evals = new ArrayList<AlgorithmEvaluator>(); 

            // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRatingDataImport\\src\\main\\resources\\com\\mmpnc\\rating\\iso\\algorithm\\dataimport\\isoalgorithmwalker.g:137:28: ( (e= expression |b= bracket |r= ratetable |f= function ) )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0==15||LA8_0==20||LA8_0==22||LA8_0==25) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRatingDataImport\\src\\main\\resources\\com\\mmpnc\\rating\\iso\\algorithm\\dataimport\\isoalgorithmwalker.g:138:28: (e= expression |b= bracket |r= ratetable |f= function )
            	    {
            	    // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRatingDataImport\\src\\main\\resources\\com\\mmpnc\\rating\\iso\\algorithm\\dataimport\\isoalgorithmwalker.g:138:28: (e= expression |b= bracket |r= ratetable |f= function )
            	    int alt7=4;
            	    switch ( input.LA(1) ) {
            	    case 20:
            	        {
            	        alt7=1;
            	        }
            	        break;
            	    case 15:
            	        {
            	        alt7=2;
            	        }
            	        break;
            	    case 25:
            	        {
            	        alt7=3;
            	        }
            	        break;
            	    case 22:
            	        {
            	        alt7=4;
            	        }
            	        break;
            	    default:
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 7, 0, input);

            	        throw nvae;

            	    }

            	    switch (alt7) {
            	        case 1 :
            	            // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRatingDataImport\\src\\main\\resources\\com\\mmpnc\\rating\\iso\\algorithm\\dataimport\\isoalgorithmwalker.g:138:30: e= expression
            	            {
            	            pushFollow(FOLLOW_expression_in_expressions895);
            	            e=expression();

            	            state._fsp--;


            	            }
            	            break;
            	        case 2 :
            	            // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRatingDataImport\\src\\main\\resources\\com\\mmpnc\\rating\\iso\\algorithm\\dataimport\\isoalgorithmwalker.g:138:45: b= bracket
            	            {
            	            pushFollow(FOLLOW_bracket_in_expressions901);
            	            b=bracket();

            	            state._fsp--;


            	            }
            	            break;
            	        case 3 :
            	            // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRatingDataImport\\src\\main\\resources\\com\\mmpnc\\rating\\iso\\algorithm\\dataimport\\isoalgorithmwalker.g:138:57: r= ratetable
            	            {
            	            pushFollow(FOLLOW_ratetable_in_expressions907);
            	            r=ratetable();

            	            state._fsp--;


            	            }
            	            break;
            	        case 4 :
            	            // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRatingDataImport\\src\\main\\resources\\com\\mmpnc\\rating\\iso\\algorithm\\dataimport\\isoalgorithmwalker.g:138:71: f= function
            	            {
            	            pushFollow(FOLLOW_function_in_expressions913);
            	            f=function();

            	            state._fsp--;


            	            }
            	            break;

            	    }



            	                                if(b!=null){
            	                                  evals.add(b);
            	                                } else if (e!=null){
            	                                  evals.add(e);
            	                                } else if (r!=null){
            	                                  evals.add(r);
            	                                } else if (f!=null){
            	                                  evals.add(f);
            	                                } 
            	                               

            	    }
            	    break;

            	default :
            	    break loop8;
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
        return evals;
    }
    // $ANTLR end "expressions"



    // $ANTLR start "bracket"
    // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRatingDataImport\\src\\main\\resources\\com\\mmpnc\\rating\\iso\\algorithm\\dataimport\\isoalgorithmwalker.g:153:2: bracket returns [AlgorithmEvaluator eval] : ^( 'BRACKET' e= expression ) ;
    public final AlgorithmEvaluator bracket() throws RecognitionException {
        AlgorithmEvaluator eval = null;


        AlgorithmEvaluator e =null;


        try {
            // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRatingDataImport\\src\\main\\resources\\com\\mmpnc\\rating\\iso\\algorithm\\dataimport\\isoalgorithmwalker.g:153:43: ( ^( 'BRACKET' e= expression ) )
            // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRatingDataImport\\src\\main\\resources\\com\\mmpnc\\rating\\iso\\algorithm\\dataimport\\isoalgorithmwalker.g:153:45: ^( 'BRACKET' e= expression )
            {
            match(input,15,FOLLOW_15_in_bracket1016); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_expression_in_bracket1020);
            e=expression();

            state._fsp--;


            match(input, Token.UP, null); 


            eval = new BracketAlgorithmEvaluator(e); eval.setContext(this.context);

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
    // $ANTLR end "bracket"



    // $ANTLR start "ratetable"
    // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRatingDataImport\\src\\main\\resources\\com\\mmpnc\\rating\\iso\\algorithm\\dataimport\\isoalgorithmwalker.g:155:2: ratetable returns [AlgorithmEvaluator eval] : ^( 'RATETABLE' k= keyvalue a= args ) ;
    public final AlgorithmEvaluator ratetable() throws RecognitionException {
        AlgorithmEvaluator eval = null;


        AlgorithmEvaluator k =null;

        List<AlgorithmEvaluator> a =null;


        try {
            // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRatingDataImport\\src\\main\\resources\\com\\mmpnc\\rating\\iso\\algorithm\\dataimport\\isoalgorithmwalker.g:155:44: ( ^( 'RATETABLE' k= keyvalue a= args ) )
            // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRatingDataImport\\src\\main\\resources\\com\\mmpnc\\rating\\iso\\algorithm\\dataimport\\isoalgorithmwalker.g:155:46: ^( 'RATETABLE' k= keyvalue a= args )
            {
            match(input,25,FOLLOW_25_in_ratetable1035); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_keyvalue_in_ratetable1039);
            k=keyvalue();

            state._fsp--;


            pushFollow(FOLLOW_args_in_ratetable1043);
            a=args();

            state._fsp--;


            eval = new RateTableAlgorithmEvaluator(k,a);

            match(input, Token.UP, null); 


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
    // $ANTLR end "ratetable"



    // $ANTLR start "function"
    // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRatingDataImport\\src\\main\\resources\\com\\mmpnc\\rating\\iso\\algorithm\\dataimport\\isoalgorithmwalker.g:157:2: function returns [AlgorithmEvaluator eval] : ^( 'FUNCTION' k= keyvalue a= args ) ;
    public final AlgorithmEvaluator function() throws RecognitionException {
        AlgorithmEvaluator eval = null;


        AlgorithmEvaluator k =null;

        List<AlgorithmEvaluator> a =null;


        try {
            // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRatingDataImport\\src\\main\\resources\\com\\mmpnc\\rating\\iso\\algorithm\\dataimport\\isoalgorithmwalker.g:157:43: ( ^( 'FUNCTION' k= keyvalue a= args ) )
            // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRatingDataImport\\src\\main\\resources\\com\\mmpnc\\rating\\iso\\algorithm\\dataimport\\isoalgorithmwalker.g:157:45: ^( 'FUNCTION' k= keyvalue a= args )
            {
            match(input,22,FOLLOW_22_in_function1058); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_keyvalue_in_function1062);
            k=keyvalue();

            state._fsp--;


            pushFollow(FOLLOW_args_in_function1066);
            a=args();

            state._fsp--;


            eval = new FunctionAlgorithmEvaluator(k,a);

            match(input, Token.UP, null); 


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



    // $ANTLR start "args"
    // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRatingDataImport\\src\\main\\resources\\com\\mmpnc\\rating\\iso\\algorithm\\dataimport\\isoalgorithmwalker.g:159:2: args returns [List<AlgorithmEvaluator> evals] : (eval= arg )* ;
    public final List<AlgorithmEvaluator> args() throws RecognitionException {
        List<AlgorithmEvaluator> evals = null;


        AlgorithmEvaluator eval =null;


        try {
            // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRatingDataImport\\src\\main\\resources\\com\\mmpnc\\rating\\iso\\algorithm\\dataimport\\isoalgorithmwalker.g:159:46: ( (eval= arg )* )
            // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRatingDataImport\\src\\main\\resources\\com\\mmpnc\\rating\\iso\\algorithm\\dataimport\\isoalgorithmwalker.g:159:48: (eval= arg )*
            {
            evals = new ArrayList<AlgorithmEvaluator>();

            // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRatingDataImport\\src\\main\\resources\\com\\mmpnc\\rating\\iso\\algorithm\\dataimport\\isoalgorithmwalker.g:159:94: (eval= arg )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0==13) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRatingDataImport\\src\\main\\resources\\com\\mmpnc\\rating\\iso\\algorithm\\dataimport\\isoalgorithmwalker.g:159:95: eval= arg
            	    {
            	    pushFollow(FOLLOW_arg_in_args1085);
            	    eval=arg();

            	    state._fsp--;


            	     evals.add(eval);

            	    }
            	    break;

            	default :
            	    break loop9;
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
        return evals;
    }
    // $ANTLR end "args"



    // $ANTLR start "arg"
    // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRatingDataImport\\src\\main\\resources\\com\\mmpnc\\rating\\iso\\algorithm\\dataimport\\isoalgorithmwalker.g:161:2: arg returns [AlgorithmEvaluator eval] : ^( 'ARG' k= keyvalues ) ;
    public final AlgorithmEvaluator arg() throws RecognitionException {
        AlgorithmEvaluator eval = null;


        List<AlgorithmEvaluator> k =null;


        try {
            // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRatingDataImport\\src\\main\\resources\\com\\mmpnc\\rating\\iso\\algorithm\\dataimport\\isoalgorithmwalker.g:161:38: ( ^( 'ARG' k= keyvalues ) )
            // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRatingDataImport\\src\\main\\resources\\com\\mmpnc\\rating\\iso\\algorithm\\dataimport\\isoalgorithmwalker.g:161:40: ^( 'ARG' k= keyvalues )
            {
            match(input,13,FOLLOW_13_in_arg1103); 

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); 
                pushFollow(FOLLOW_keyvalues_in_arg1107);
                k=keyvalues();

                state._fsp--;


                eval = new ArgumentAlgorithmEvaluator(k); eval.setContext(context);

                match(input, Token.UP, null); 
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
    // $ANTLR end "arg"



    // $ANTLR start "keyvalues"
    // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRatingDataImport\\src\\main\\resources\\com\\mmpnc\\rating\\iso\\algorithm\\dataimport\\isoalgorithmwalker.g:163:2: keyvalues returns [List<AlgorithmEvaluator> evals] : (eval= keyvalue )* ;
    public final List<AlgorithmEvaluator> keyvalues() throws RecognitionException {
        List<AlgorithmEvaluator> evals = null;


        AlgorithmEvaluator eval =null;


        try {
            // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRatingDataImport\\src\\main\\resources\\com\\mmpnc\\rating\\iso\\algorithm\\dataimport\\isoalgorithmwalker.g:163:52: ( (eval= keyvalue )* )
            // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRatingDataImport\\src\\main\\resources\\com\\mmpnc\\rating\\iso\\algorithm\\dataimport\\isoalgorithmwalker.g:163:54: (eval= keyvalue )*
            {
             evals = new ArrayList<AlgorithmEvaluator>(); 

            // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRatingDataImport\\src\\main\\resources\\com\\mmpnc\\rating\\iso\\algorithm\\dataimport\\isoalgorithmwalker.g:163:103: (eval= keyvalue )*
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( (LA10_0==IDENTS) ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRatingDataImport\\src\\main\\resources\\com\\mmpnc\\rating\\iso\\algorithm\\dataimport\\isoalgorithmwalker.g:163:104: eval= keyvalue
            	    {
            	    pushFollow(FOLLOW_keyvalue_in_keyvalues1127);
            	    eval=keyvalue();

            	    state._fsp--;


            	     evals.add(eval);

            	    }
            	    break;

            	default :
            	    break loop10;
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
        return evals;
    }
    // $ANTLR end "keyvalues"



    // $ANTLR start "keyvalue"
    // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRatingDataImport\\src\\main\\resources\\com\\mmpnc\\rating\\iso\\algorithm\\dataimport\\isoalgorithmwalker.g:165:2: keyvalue returns [AlgorithmEvaluator eval] : ^( IDENTS STRING ) ;
    public final AlgorithmEvaluator keyvalue() throws RecognitionException {
        AlgorithmEvaluator eval = null;


        CommonTree IDENTS1=null;
        CommonTree STRING2=null;

        try {
            // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRatingDataImport\\src\\main\\resources\\com\\mmpnc\\rating\\iso\\algorithm\\dataimport\\isoalgorithmwalker.g:165:44: ( ^( IDENTS STRING ) )
            // C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRatingDataImport\\src\\main\\resources\\com\\mmpnc\\rating\\iso\\algorithm\\dataimport\\isoalgorithmwalker.g:165:46: ^( IDENTS STRING )
            {
            IDENTS1=(CommonTree)match(input,IDENTS,FOLLOW_IDENTS_in_keyvalue1145); 

            match(input, Token.DOWN, null); 
            STRING2=(CommonTree)match(input,STRING,FOLLOW_STRING_in_keyvalue1147); 

            match(input, Token.UP, null); 


            eval = new KeyValueAlgorithmEvaluator((IDENTS1!=null?IDENTS1.getText():null), (STRING2!=null?STRING2.getText():null)); eval.setContext(this.context);

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
    // $ANTLR end "keyvalue"

    // Delegated rules


 

    public static final BitSet FOLLOW_program_in_block69 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_statement_in_block75 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_classnode_in_block81 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_16_in_classnode114 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_keyvalues_in_classnode118 = new BitSet(new long[]{0x000000000C290008L});
    public static final BitSet FOLLOW_childnodes_in_classnode122 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_childnode_in_childnodes142 = new BitSet(new long[]{0x000000000C290002L});
    public static final BitSet FOLLOW_stat_in_childnode162 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rating_in_childnode168 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_error_in_childnode174 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_formattach_in_childnode180 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_classnode_in_childnode186 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_27_in_stat213 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_keyvalues_in_stat217 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_26_in_rating233 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_keyvalues_in_rating237 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_19_in_error253 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_keyvalues_in_error257 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_21_in_formattach273 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_keyvalues_in_formattach277 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_29_in_program294 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_keyvalues_in_program298 = new BitSet(new long[]{0x0000000001804008L});
    public static final BitSet FOLLOW_statements_in_program302 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_statement_in_statements322 = new BitSet(new long[]{0x0000000001804002L});
    public static final BitSet FOLLOW_iif_in_statement342 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_loop_in_statement348 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_assign_in_statement354 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_23_in_iif396 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_condition_in_iif400 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_then_in_iif404 = new BitSet(new long[]{0x0000000000040008L});
    public static final BitSet FOLLOW_eelse_in_iif408 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_17_in_condition452 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_condition456 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_28_in_then499 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_statements_in_then503 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_18_in_eelse546 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_statements_in_eelse550 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_24_in_loop593 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_keyvalue_in_loop597 = new BitSet(new long[]{0x0000000001804008L});
    public static final BitSet FOLLOW_statements_in_loop601 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_14_in_assign671 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_keyvalues_in_assign675 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_expression_in_assign679 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_20_in_expression751 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_keyvalues_in_expression755 = new BitSet(new long[]{0x0000000002508008L});
    public static final BitSet FOLLOW_expressions_in_expression759 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_expression_in_expressions895 = new BitSet(new long[]{0x0000000002508002L});
    public static final BitSet FOLLOW_bracket_in_expressions901 = new BitSet(new long[]{0x0000000002508002L});
    public static final BitSet FOLLOW_ratetable_in_expressions907 = new BitSet(new long[]{0x0000000002508002L});
    public static final BitSet FOLLOW_function_in_expressions913 = new BitSet(new long[]{0x0000000002508002L});
    public static final BitSet FOLLOW_15_in_bracket1016 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_bracket1020 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_25_in_ratetable1035 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_keyvalue_in_ratetable1039 = new BitSet(new long[]{0x0000000000002008L});
    public static final BitSet FOLLOW_args_in_ratetable1043 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_22_in_function1058 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_keyvalue_in_function1062 = new BitSet(new long[]{0x0000000000002008L});
    public static final BitSet FOLLOW_args_in_function1066 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_arg_in_args1085 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_13_in_arg1103 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_keyvalues_in_arg1107 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_keyvalue_in_keyvalues1127 = new BitSet(new long[]{0x0000000000000042L});
    public static final BitSet FOLLOW_IDENTS_in_keyvalue1145 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_STRING_in_keyvalue1147 = new BitSet(new long[]{0x0000000000000008L});

}