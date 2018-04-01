package com.mmpnc.rating.icm.algorithm;

import com.mmpnc.context.Context;
import com.mmpnc.rating.vo.Product;
import com.mmpnc.rating.vo.Process;

/**
 * Author : Shashikant Mhatre
 *
 * Created On : Oct 12, 2013
 *
 * Description :
 *
 * Note :
 */

public interface AlgorithmEvaluator {
	Object evaluate();
	void setContext(Context context);
	void setProduct(Product product);
	void setProcess(Process process);
	
}
