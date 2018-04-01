package com.mmpnc.rating.icm.algorithm.evaluator;

import java.util.List;

import com.mmpnc.context.Context;
import com.mmpnc.rating.icm.algorithm.AlgoConstant;
import com.mmpnc.rating.icm.algorithm.AlgorithmEvaluator;
import com.mmpnc.rating.icm.algorithm.vo.DataValue;
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

public class ProgramAlgorithmEvaluator implements AlgorithmEvaluator {

	private List<AlgorithmEvaluator> keyvalues;
	private List<AlgorithmEvaluator> statements;
	private Context context;

	public ProgramAlgorithmEvaluator(List<AlgorithmEvaluator> keyValues,
			List<AlgorithmEvaluator> statements) {
		this.keyvalues = keyValues;
		this.statements = statements;
	}

	@Override
	public Object evaluate() {

		StringBuffer buffer = new StringBuffer();
		
		for(AlgorithmEvaluator keyvalue : this.keyvalues){
			keyvalue.setContext(context);
			DataValue dv = (DataValue) keyvalue.evaluate();
			System.out.println("Program attributes " + dv.getValue());
		}
		
		
		for(AlgorithmEvaluator statement : this.statements){
			statement.setContext(context);
			buffer.append(statement.evaluate());
			buffer.append(AlgoConstant.NewLine);
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
