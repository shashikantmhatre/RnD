package com.mmpnc.rating.icm.algorithm.evaluator;

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

public class IfAlgorithmEvaluator implements AlgorithmEvaluator {

	private AlgorithmEvaluator conditionevaluator;
	private AlgorithmEvaluator thenevaluator;
	private AlgorithmEvaluator elseevaluator;
	private Context context;

	public IfAlgorithmEvaluator(AlgorithmEvaluator conditionevaluator, AlgorithmEvaluator thenevaluator,
			AlgorithmEvaluator elseevaluator) {
		
		this.conditionevaluator = conditionevaluator;
		this.thenevaluator = thenevaluator;
		this.elseevaluator = elseevaluator;
	}

	@Override
	public StringBuffer evaluate() {
		StringBuffer buffer = new StringBuffer();
		
		int spaceCount = (Integer) this.context.getValue(ContextParam.SPACECOUNT);

		if((Boolean) context.getValue(ContextParam.FORMAT)){
			AlgorithmUtil.appendSpace(buffer, spaceCount);
		}
		buffer.append(AlgoConstant.If);
		
		if(this.conditionevaluator != null){
			this.conditionevaluator.setContext(context);
			buffer.append(this.conditionevaluator.evaluate());
			
		}
		
		if(this.thenevaluator != null){
			this.context.putValue(ContextParam.SPACECOUNT , spaceCount + 1 );
			this.thenevaluator.setContext(context);
			buffer.append(this.thenevaluator.evaluate());
		}
		
		if(this.elseevaluator != null){
			this.context.putValue(ContextParam.SPACECOUNT , spaceCount + 1 );
			this.elseevaluator.setContext(context);
			buffer.append(this.elseevaluator.evaluate());
		}
		
		if((Boolean) context.getValue(ContextParam.FORMAT)){
			AlgorithmUtil.appendSpace(buffer, spaceCount);
			buffer.append(AlgoConstant.EndIf);
			buffer.append(AlgoConstant.NewLine);
		} else{
			buffer.append(AlgoConstant.EndIf);
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
