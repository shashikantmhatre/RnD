tree grammar isodatawalker;

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
  tokenVocab = isodataimport;
  ASTLabelType = CommonTree;
}

@header {
  package com.mmpnc.rating.iso.content.dataimport;
  
  import com.mmpnc.context.Context;
  import com.mmpnc.rating.icm.content.DataEvaluator;
  import com.mmpnc.rating.icm.content.evaluator.*;
  import com.mmpnc.rating.vo.*;
}

@members {
  private Context context;
  
  public void setContext(Context context) {
    this.context = context;
  }
}

data: rs=records
        {
                for(DataEvaluator eval : $rs.evals)
                {
                    eval.setContext(this.context);
                    eval.evaluate();
                }
            }

        ;

records returns [List<DataEvaluator> evals] : 
        {
          evals = new ArrayList<DataEvaluator>();
        }
        r1=record {  evals.add(r1); } (record {  evals.add(r1); })*;

record returns [ DataEvaluator eval ] : (rb=ratebook {eval = rb;} | rt=ratetable {eval = rt;}  | dt=domaintable {eval = dt;} );

ratebook returns [DataEvaluator ratebook]: 
        ^(constant='Ratebook' keyvalueevaluators=keyvalues rb=ratebook?)  
        {
          ratebook = new RatebookEvaluator($constant.text, $keyvalueevaluators.evals, rb);
          ratebook.setContext(this.context);
        };

ratetable returns [DataEvaluator rateTableEvaluator] : ^(constant='RateTable' keyvalueevaluators=keyvalues rkevals=ratetablekeys rvevals=ratetablevalues reevals=ratetableentrys 
        {
          rateTableEvaluator = new RateTableEvaluator ($constant.text, $keyvalueevaluators.evals, $rkevals.evals, $rvevals.evals, $reevals.evals);
          rateTableEvaluator.setContext(this.context);
        }  
        );

ratetablekeys returns [List<DataEvaluator> evals]: 
        {
          evals = new ArrayList<DataEvaluator>();
        }
        (r1=ratetablekey {  evals.add(r1); })*;

ratetablekey  returns[DataEvaluator eval]: ^(constant='RateTableKey' keyvalueevaluators=keyvalues { eval = new RateTableKeyEvaluator($constant.text, $keyvalueevaluators.evals); eval.setContext(this.context);});

ratetablevalues returns [List<DataEvaluator> evals]: 
        {
          evals = new ArrayList<DataEvaluator>();
        }
        (r1=ratetablevalue {  evals.add(r1); })*;

ratetablevalue returns[DataEvaluator eval]: ^(constant='RateTableValue' keyvalueevaluators=keyvalues { eval = new RateTableValueEvaluator($constant.text, $keyvalueevaluators.evals); eval.setContext(this.context);});

ratetableentrys returns [List<DataEvaluator> evals]: 
        {
          evals = new ArrayList<DataEvaluator>();
        }
        (r1=ratetableentry {  evals.add(r1); })*;

ratetableentry returns[DataEvaluator eval]: ^(constant='RateTableEntry' keyvalueevaluators=keyvalues k1=keys v1=values { eval = new RateTableEntryEvaluator($constant.text, $keyvalueevaluators.evals, $k1.evals, $v1.evals); eval.setContext(this.context);});

domaintable returns [DataEvaluator domainTableEvaluator]: ^(constant='DomainTable' keyvalueevaluators=keyvalues dkevals=domaintablekeys dvevals=domaintablevalues deevals=domaintableentrys 
        {
          domainTableEvaluator = new DomainTableEvaluator ($constant.text, $keyvalueevaluators.evals, $dkevals.evals, $dvevals.evals, $deevals.evals);
          domainTableEvaluator.setContext(this.context);
        }
        );

domaintablekeys returns [List<DataEvaluator> evals]: 
        {
          evals = new ArrayList<DataEvaluator>();
        }
        (d1=domaintablekey {  evals.add(d1); })*;

domaintablekey returns[DataEvaluator eval]: ^(constant='DomainTableKey' keyvalueevaluators=keyvalues { eval = new DomainTableKeyEvaluator($constant.text, $keyvalueevaluators.evals); eval.setContext(this.context);});

domaintablevalues returns [List<DataEvaluator> evals]: 
        {
          evals = new ArrayList<DataEvaluator>();
        }
        (d1=domaintablevalue {  evals.add(d1); })*;

domaintablevalue returns[DataEvaluator eval]: ^(constant='DomainTableValue' keyvalueevaluators=keyvalues { eval = new DomainTableValueEvaluator($constant.text, $keyvalueevaluators.evals); eval.setContext(this.context);});

domaintableentrys returns [List<DataEvaluator> evals]: 
        {
          evals = new ArrayList<DataEvaluator>();
        }
        (d1=domaintableentry {  evals.add(d1); }) *;

domaintableentry returns[DataEvaluator eval]: ^(constant='DomainTableEntry' keyvalueevaluators=keyvalues k1=keys v1=values { eval = new DomainTableEntryEvaluator($constant.text, $keyvalueevaluators.evals, $k1.evals, $v1.evals); eval.setContext(this.context);});

keys returns [List<DataEvaluator> evals] : 
        {
            evals = new ArrayList<DataEvaluator>();
        } (k1=key {evals.add(k1);})*;

key returns [DataEvaluator eval] : ^('Key' k1=keyvalues) { eval = new KeyEvaluator($k1.evals); eval.setContext(context);};

values returns [List<DataEvaluator> evals] : 
        {
            evals = new ArrayList<DataEvaluator>();
        } (v1=value {evals.add(v1);})*;

value returns [DataEvaluator eval] : ^('Value' v1=keyvalues) { eval = new ValueEvaluator($v1.evals); eval.setContext(context);};

keyvalues returns [List<DataEvaluator> evals] : 
        {
            evals = new ArrayList<DataEvaluator>();
        }
        (k1=keyvalue {  evals.add(k1); })*;

keyvalue returns [DataEvaluator eval] : ^(IDENTS STRING) {eval = new KeyValueDataEvaluator($IDENTS.text, $STRING.text); eval.setContext(this.context);};