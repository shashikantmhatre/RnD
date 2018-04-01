package com.mmpnc.rating.icm.algorithm.evaluator;

import java.util.ArrayList;
import java.util.List;

import com.mmpnc.context.Context;
import com.mmpnc.context.ContextParam;
import com.mmpnc.rating.icm.algorithm.AlgoConstant;
import com.mmpnc.rating.icm.algorithm.AlgorithmEvaluator;
import com.mmpnc.rating.icm.algorithm.vo.DataValue;
import com.mmpnc.rating.icm.content.util.AlgorithmUtil;
import com.mmpnc.rating.vo.Process;
import com.mmpnc.rating.vo.Product;
import com.mmpnc.rating.vo.Step;

/**
 * Author : Shashikant Mhatre
 *
 * Created On : Oct 12, 2013
 *
 * Description :
 *
 * Note :
 */

public class LoopAlgorithmEvaluator implements AlgorithmEvaluator {

	private AlgorithmEvaluator keyvalue;
	private List<AlgorithmEvaluator> statements;
	private Context context;

	public LoopAlgorithmEvaluator(AlgorithmEvaluator keyvalue,
			List<AlgorithmEvaluator> statements) {
		this.keyvalue = keyvalue;
		this.statements = statements;
	}

	@SuppressWarnings("unchecked")
	@Override
	public StringBuffer evaluate() {
		StringBuffer buffer = new StringBuffer();
		
		// get the local PCH Steps before current PCH statement execution
		List<Step> priorPchAlgoSteps = (List<Step>) context.getValue(ContextParam.PCHSTEPS);
		
		int spaceCount = (Integer) this.context.getValue(ContextParam.SPACECOUNT);

		if((Boolean) context.getValue(ContextParam.FORMAT)){
			AlgorithmUtil.appendSpace(buffer, spaceCount);
		}
		
//		buffer.append("before Loop Statement ********** ");
		
		buffer.append(AlgoConstant.Loop);
		
		keyvalue.setContext(context);
		DataValue dv = (DataValue) keyvalue.evaluate();
		
		if(dv.getKey().equals("through")){
			if(!dv.getValue().equals("")){
				buffer.append("\"").append(dv.getValue()).append("\" ");
			}				
		} 
		if((Boolean) context.getValue(ContextParam.FORMAT)){
			buffer.append(AlgoConstant.NewLine);
		}
		
//		System.out.println("we got " + priorPchAlgoSteps.size() + " steps before loop statement execution ");
//		int count = 1;
		
		for(AlgorithmEvaluator statement : statements){
			this.context.putValue(ContextParam.SPACECOUNT , spaceCount + 1 );
			this.context.putValue(ContextParam.PCHSTEPS , new ArrayList<Step>() );
			
			statement.setContext(context);			
			StringBuffer loopStatement = (StringBuffer) statement.evaluate();
			
			
			
			List<Step> loopPchAlgoSteps = (List<Step>) context.getValue(ContextParam.PCHSTEPS);
//			System.out.println("we got " + loopPchAlgoSteps.size() + " steps after "+ count++ +" loop statement execution ");

//			below steps will get the pch calls within loop
			for(Step step : loopPchAlgoSteps){
//				System.out.println("we get " + step.getBlock());
				buffer.append(step.getBlock()).append(AlgoConstant.NewLine);
			}
			
			
			buffer.append(loopStatement);
		}
		
//		buffer.append(spaceCount);
		if((Boolean) context.getValue(ContextParam.FORMAT)){
			AlgorithmUtil.appendSpace(buffer, spaceCount);
			buffer.append(AlgoConstant.EndLoop);		
			buffer.append(AlgoConstant.NewLine);
		} else{
			buffer.append(AlgoConstant.EndLoop);		
		}
		
		this.context.putValue(ContextParam.SPACECOUNT, spaceCount);
		
		this.context.putValue(ContextParam.PCHSTEPS, priorPchAlgoSteps);
		
		return buffer;
	}

	@Override
	public void setContext(Context context) {
		this.context = context;
	}

	@Override
	public void setProduct(Product product) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setProcess(Process process) {
		// TODO Auto-generated method stub
		
	}

}
