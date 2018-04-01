tree grammar isoalgorithmwalker;

/*
 * Author : Shashikant Mhatre
 *
 * Created On : Oct 12, 2013
 *
 * Description :
 *
 * Note :
 */

options {
  language = Java;
  tokenVocab = isoalgorithmimport;
  ASTLabelType = CommonTree;
}

@header {
  package com.mmpnc.rating.iso.algorithm.dataimport;
  
  import com.mmpnc.context.Context;
  import com.mmpnc.rating.icm.algorithm.AlgorithmEvaluator;
  import com.mmpnc.rating.icm.algorithm.evaluator.*;
  import com.mmpnc.rating.icm.algorithm.vo.*;
}

@members {
  private Context context;
  private List<AlgorithmEvaluator> evals;
  
  public void setContext(Context context) {
    this.context = context;
  }
}


 block returns [StringBuffer buffer]: { buffer = new StringBuffer();} (p=program | s=statement | c=classnode)
        {
          evals = new ArrayList<AlgorithmEvaluator>();
          
          if(p != null){
            $p.eval.setContext(context);
            evals.add($p.eval);
            buffer.append($p.eval.evaluate());
          } else if ( s != null) {
            $s.eval.setContext(context);
            evals.add($s.eval);
            buffer.append($s.eval.evaluate());
          } else {
            $c.eval.setContext(context);
            evals.add($c.eval);
            buffer.append($c.eval.evaluate());
          }
        }
        ;

 classnode returns[AlgorithmEvaluator eval] : ^('CLASS' k=keyvalues c=childnodes { eval = new ClassEvaluator($k.evals, $c.evals); });
 
 childnodes returns[List<AlgorithmEvaluator> evals] : { evals = new ArrayList<AlgorithmEvaluator>(); }(c=childnode { evals.add(c);})*;
 
 childnode returns[AlgorithmEvaluator eval] : (s=stat | r=rating | e=error | f=formattach | c=classnode)
          {
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
          };
 
 stat returns[AlgorithmEvaluator eval] : ^('STAT' k=keyvalues { eval = new StatEvaluator($k.evals); });

 rating returns[AlgorithmEvaluator eval] : ^('RATING' k=keyvalues { eval = new RatingEvaluator($k.evals); });

 error returns[AlgorithmEvaluator eval] : ^('ERROR' k=keyvalues { eval = new ErrorEvaluator($k.evals); });

 formattach returns[AlgorithmEvaluator eval] : ^('FORMATTACHMENT' k=keyvalues { eval = new FormAttachEvaluator($k.evals); });
 
 program returns[AlgorithmEvaluator eval] : ^('program' k=keyvalues s=statements { eval = new ProgramAlgorithmEvaluator($k.evals, $s.evals); });
 
 statements returns[List<AlgorithmEvaluator> evals] : { evals = new ArrayList<AlgorithmEvaluator>(); }(s=statement { evals.add(s);})*;
 
 statement returns[AlgorithmEvaluator eval] : (i=iif | l=loop | a=assign)
                          {
                            if(i != null){
                              eval = i;
                            } else if ( l != null){
                              eval = l;
                            } else {
                              eval = a;
                            }
                          };
 
 iif returns[AlgorithmEvaluator eval]: ^('IF' c=condition t=then e=eelse?)
                          {
                            eval = new IfAlgorithmEvaluator(c,t,e);
                          };
 
 condition returns[AlgorithmEvaluator eval] : ^('CONDITION' e=expression)
                          {
                            eval = new ConditionAlgorithmEvaluator(e);
                          };
 
 then returns[AlgorithmEvaluator eval] : ^('THEN' s=statements)
                          {
                            eval = new ThenAlgorithmEvaluator($s.evals);
                          };
 
 eelse returns[AlgorithmEvaluator eval] : ^('ELSE' s=statements)
                          {
                            eval = new ElseAlgorithmEvaluator($s.evals);
                          };
 
 loop returns[AlgorithmEvaluator eval] : ^('LOOP' k=keyvalue s=statements)
                          {
                            eval = new LoopAlgorithmEvaluator(k,$s.evals);
                          }
                          ;
 
 assign returns[AlgorithmEvaluator eval] : ^('ASSIGN' k=keyvalues e=expression )
                          {
                            eval = new AssignAlgorithmEvaluator($k.evals,e);
                          }
                          ;
  
 expression returns[AlgorithmEvaluator eval] : ^('EXPRESSION' k=keyvalues e=expressions ) 
                          {eval = new ExpressionAlgorithmEvaluator($k.evals, $e.evals); eval.setContext(this.context);}  
                          ;
 
 expressions returns[List<AlgorithmEvaluator> evals] : {evals = new ArrayList<AlgorithmEvaluator>(); }
                           ( 
                           ( e=expression | b=bracket | r=ratetable | f=function) 
                           {
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
                           )*
                           ;
 
 bracket returns[AlgorithmEvaluator eval] : ^('BRACKET' e=expression) {eval = new BracketAlgorithmEvaluator(e); eval.setContext(this.context);};

 ratetable returns[AlgorithmEvaluator eval]: ^('RATETABLE' k=keyvalue a=args {eval = new RateTableAlgorithmEvaluator(k,a);});

 function returns[AlgorithmEvaluator eval]: ^('FUNCTION' k=keyvalue a=args {eval = new FunctionAlgorithmEvaluator(k,a);} );

 args returns[List<AlgorithmEvaluator> evals]: {evals = new ArrayList<AlgorithmEvaluator>();}(eval=arg { evals.add(eval);} )*;
 
 arg returns[AlgorithmEvaluator eval]: ^('ARG' k=keyvalues {eval = new ArgumentAlgorithmEvaluator(k); eval.setContext(context);});

 keyvalues returns[List<AlgorithmEvaluator> evals] : { evals = new ArrayList<AlgorithmEvaluator>(); } (eval=keyvalue { evals.add(eval);} )*;

 keyvalue returns[AlgorithmEvaluator eval] : ^(IDENTS STRING) {eval = new KeyValueAlgorithmEvaluator($IDENTS.text, $STRING.text); eval.setContext(this.context);};
 
 
 
 
 