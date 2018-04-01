package com.mmpnc.rating.icm.algorithm.evaluator;

import java.util.List;

import com.mmpnc.context.Context;
import com.mmpnc.context.ContextParam;
import com.mmpnc.rating.icm.algorithm.AlgoConstant;
import com.mmpnc.rating.icm.algorithm.AlgorithmEvaluator;
import com.mmpnc.rating.vo.Process;
import com.mmpnc.rating.vo.Product;

/**
 * Author : Shashikant Mhatre
 *
 * Created On : Oct 12, 2013
 *
 * Description :
 *
 * Note :
 */

public class ThenAlgorithmEvaluator implements AlgorithmEvaluator {


	private Context context;
	private List<AlgorithmEvaluator> statements;

	public ThenAlgorithmEvaluator(List<AlgorithmEvaluator> statements) {
		this.statements = statements;
	}

	@Override
	public Object evaluate() {
		StringBuffer buffer = new StringBuffer();
		
		buffer.append(AlgoConstant.Then);		
		if((Boolean) context.getValue(ContextParam.FORMAT)){
			buffer.append(AlgoConstant.NewLine);
		}
		
		for(AlgorithmEvaluator statement : statements){
			statement.setContext(context);
			buffer.append(statement.evaluate());
		}
		
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
