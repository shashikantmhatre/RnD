package com.mmpnc.rating.icm.algorithm.evaluator;

import java.util.List;

import com.mmpnc.context.Context;
import com.mmpnc.context.ContextParam;
import com.mmpnc.rating.icm.algorithm.AlgoConstant;
import com.mmpnc.rating.icm.algorithm.AlgorithmEvaluator;
import com.mmpnc.rating.icm.content.util.AlgorithmUtil;
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

public class ElseAlgorithmEvaluator implements AlgorithmEvaluator {
	private List<AlgorithmEvaluator> statements;
	private Context context;
	
	public ElseAlgorithmEvaluator(List<AlgorithmEvaluator> statements) {
		this.statements = statements;
	}

	@Override
	public StringBuffer evaluate() {
		StringBuffer buffer = new StringBuffer();
		int spaceCount = (Integer) this.context.getValue(ContextParam.SPACECOUNT);
		
		if((Boolean) context.getValue(ContextParam.FORMAT)){
			AlgorithmUtil.appendSpace(buffer, spaceCount - 1);
			buffer.append(AlgoConstant.Else);
			buffer.append(AlgoConstant.NewLine);
		} else{
			buffer.append(AlgoConstant.Else);
		}
		
//		this.context.putValue("SpaceCount", spaceCount + 1);
		for(AlgorithmEvaluator statement : statements){
			statement.setContext(context);
			buffer.append(statement.evaluate());
		}
		
		this.context.putValue(ContextParam.SPACECOUNT, spaceCount);
		
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
