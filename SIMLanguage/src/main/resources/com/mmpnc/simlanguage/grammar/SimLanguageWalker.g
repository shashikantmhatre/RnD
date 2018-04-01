tree grammar SimLanguageWalker;

/*
  Author : Shashikant Mhatre
  
  Created On : October 12, 2013
  
  Description : This document creates the language token/tree walker of the SIM (Simple) Language. Any modification to this language syntax will result in modification in the
  of language evaluators
  
  Note : Please contact Shashikant Mhatre before you modify the language syntax
*/

options {
  language = Java;
  tokenVocab = SimLanguageGrammar;
  ASTLabelType = CommonTree;
}

@header {
package com.mmpnc.simlanguage.parse;

import com.mmpnc.context.Context;
import com.mmpnc.simlanguage.Evaluator;
import com.mmpnc.simlanguage.execute.EvaluatorFactory;
}

@members {
private Context context = null;
private EvaluatorFactory evaluatorFactory = null;
 
public void setEvaluatorFactory(EvaluatorFactory evaluatorFactory){
  this.evaluatorFactory = evaluatorFactory;
}
}

block[Context context] : 
            {
            this.context = context;
            }
        e1=statements
            {
                for(Evaluator eval : $e1.eval)
                {
                    eval.setContext(context);
                    eval.evaluate();
                }
            }
        ;

statements returns [List<Evaluator> eval] 
        :
        {
            eval = new ArrayList<Evaluator>();
        } 
        e1=statement {$eval.add($e1.eval);} (e2=statement{$eval.add($e2.eval);})*;

statement returns [Evaluator eval]: 
        (assignment | ifStatement | loopStatement | executeProgram | attachProgram) 
            {
                if($assignment.eval != null)
                {
                    $eval = $assignment.eval;
                }
                else if($ifStatement.eval != null)
                {
                    $eval = $ifStatement.eval;
                }else if($loopStatement.eval != null)
                {
                    $eval = $loopStatement.eval;
                }else if($executeProgram.eval != null)
                {
                    $eval = $executeProgram.eval;
                }else if($attachProgram.eval != null)
                {
                    $eval = $attachProgram.eval;
                }
                
            }
        ;

executeProgram returns [Evaluator eval]:
    ^('Execute' (i=IDENTS | x=xpath) )
        {
            if($i != null) {
              $eval = this.evaluatorFactory.getProgramEvaluator(context,$i.text, null);
            } else {
              $eval = this.evaluatorFactory.getProgramEvaluator(context, null, $x.eval);
            }
        }
    ;

attachProgram returns [Evaluator eval]:
    ^('Attach' (i=IDENTS | x=xpath) )
        {
            if($i != null) {
              $eval = this.evaluatorFactory.getAttachProgramEvaluator(context,$i.text, null);
            } else {
              $eval = this.evaluatorFactory.getAttachProgramEvaluator(context, null, $x.eval);
            }
        }
    ;

assignment returns [Evaluator eval] :
    ^('=' (i=IDENTS | v=varType | x=xpath) e1=arithmeticExp) 
        {
            if($i != null)
            {
                $eval = this.evaluatorFactory.getAssignmentEvaluator(context, this.evaluatorFactory.getVarEvaluator(null, $i.text), $e1.eval);
            }
            else {
                if(v != null)
                {
                    $eval = this.evaluatorFactory.getAssignmentLocalEvaluator(context, $v.eval, $e1.eval);
                } else {
                    $eval = this.evaluatorFactory.getAssignmentXpathEvaluator(context, $x.eval, $e1.eval);
                }
            }
        }
    ;

function [String constant] returns [Evaluator eval]:
	    {
	        List<Evaluator> evallist = new ArrayList<Evaluator>();
	    }
    ^(e1=IDENTS (e2=arithmeticExp {evallist.add($e2.eval);} (',' e3=arithmeticExp {evallist.add($e3.eval);} )*)?)
	    {
	        $eval = this.evaluatorFactory.getFunctionEvaluator(constant,$e1.text, evallist);
	    }
    ;

specialFunction returns [Evaluator eval]:
    ^(t=SPECIALFUNCTION e1=arithmeticExp)
	    {
	        $eval = this.evaluatorFactory.getSpecialFunctionEvaluator(context,$t.text, $e1.eval);
	    }
    ;
    
rateFunction returns [Evaluator eval]:
    ^(e1='RateTable' e2=function[$e1.text])
	    {
	       $eval = $e2.eval;
//	       $eval = this.evaluatorFactory.getRateTableEvaluator(context,$e1.text);
	    }
    ;
         
ifStatement returns [Evaluator eval]:
    ^('IF' (n=NOT)? bool=booleanExp s1=statements ('ELSE' s2=statements)?)
        {
          if($n != null)
          {
            $eval = this.evaluatorFactory.getIfEvaluator(context, this.evaluatorFactory.getNotBooleanEvaluator($bool.eval), $s1.eval, $s2.eval);
          } else {
            $eval = this.evaluatorFactory.getIfEvaluator(context, this.evaluatorFactory.getBooleanEvaluator($bool.eval), $s1.eval, $s2.eval);
          }
        }
     ;
   
loopStatement returns [Evaluator eval]:
    ^('LOOP THROUGH' v1=STRING s1=statements)
        {
            $eval = this.evaluatorFactory.getLoopEvaluator(context, $v1.text, $s1.eval);
        }
    ;

xpath returns [Evaluator eval]
    : XPATHCONSTANT ':' t=STRING 
        {
            $eval = this.evaluatorFactory.getXpathEvaluator ($t.text);
        }
    ;

varType returns [Evaluator eval]
    : v=VARCONSTANT ':' t=IDENTS 
        {
            $eval = this.evaluatorFactory.getVarEvaluator ($v.text, $t.text);
        }
    ;    

atom returns [Evaluator eval]
    :  ( i=IDENTS | ti=INTEGER | ts=STRING )
            {
                if($i != null)
                {
                    $eval = this.evaluatorFactory.getVarEvaluator(null, $i.text);
                }
                else
                {
                    if($ti == null){
                        $eval = this.evaluatorFactory.getStringEvaluator ($ts.text);
                    }else{
                        $eval = this.evaluatorFactory.getIntegerEvaluator ($ti.text);
                    }
                }
            }
        |e=function[null]
            {
                $eval = $e.eval;
            }
        |e1=rateFunction
            {
                $eval = $e1.eval;
            }
        |e2=xpath
            {
              $eval = $e2.eval;  
            }    
        |e3=varType
            {
              $eval = $e3.eval;
            }
        |e4=specialFunction
            {
              $eval = $e4.eval;
            }
    ;

arithmeticExp returns [Evaluator eval]
    : ^('+' e1=arithmeticExp e2=arithmeticExp){$eval = this.evaluatorFactory.getPlusEvaluator ($e1.eval, $e2.eval);}
    | ^(SUBTRACT e1=arithmeticExp e2=arithmeticExp){$eval = this.evaluatorFactory.getMinusEvaluator ($e1.eval, $e2.eval);}
    | ^('*' e1=arithmeticExp e2=arithmeticExp){$eval = this.evaluatorFactory.getMultiplyEvaluator ($e1.eval, $e2.eval);}
    | ^('/' e1=arithmeticExp e2=arithmeticExp){$eval = this.evaluatorFactory.getDivideEvaluator ($e1.eval, $e2.eval);}
    | ^('mod' e1=arithmeticExp e2=arithmeticExp){$eval = this.evaluatorFactory.getModEvaluator ($e1.eval, $e2.eval);}
    | ^(STARTS e1=arithmeticExp e2=arithmeticExp) {$eval = this.evaluatorFactory.getCustomOperatorEvaluator ($e1.eval, $e2.eval);}
    | ^(NEGATION e1=atom){$eval = this.evaluatorFactory.getUnaryNegationEvaluator($e1.eval);}
    | e=atom { $eval = $e.eval;}       
    | e=assignment { $eval = $e.eval;}
    ;

booleanExp returns [Evaluator eval]
    : ^(AND e1=booleanExp e2=booleanExp) {$eval = this.evaluatorFactory.getAndEvaluator ($e1.eval, $e2.eval);}
    | ^(OR e1=booleanExp e2=booleanExp){$eval = this.evaluatorFactory.getOrEvaluator ($e1.eval, $e2.eval);}
    | ^(EQUALS e1=booleanExp e2=booleanExp){$eval = this.evaluatorFactory.getEqualsEvaluator ($e1.eval, $e2.eval);}
    | ^(NOTEQUALS e1=booleanExp e2=booleanExp){$eval = this.evaluatorFactory.getNotEqualsEvaluator ($e1.eval, $e2.eval);}
    | ^('<' e1=booleanExp e2=booleanExp){$eval = this.evaluatorFactory.getLessThanEvaluator ($e1.eval, $e2.eval);}
    | ^('<=' e1=booleanExp e2=booleanExp){$eval = this.evaluatorFactory.getLessThanEqualsEvaluator ($e1.eval, $e2.eval);}
    | ^('>' e1=booleanExp e2=booleanExp){$eval = this.evaluatorFactory.getGreaterThanEvaluator ($e1.eval, $e2.eval);}
    | ^('>=' e1=booleanExp e2=booleanExp){$eval = this.evaluatorFactory.getGreaterThanEqualsEvaluator ($e1.eval, $e2.eval);}
    | ^(NOT e1=booleanExp){$eval = this.evaluatorFactory.getNotBooleanEvaluator ($e1.eval);}
    | arithmeticExp {$eval = $arithmeticExp.eval;}
    ;
    
    
