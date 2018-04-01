package com.mmpnc.rating.icm.content;

import com.mmpnc.context.Context;
import com.mmpnc.rating.vo.DomainTable;
import com.mmpnc.rating.vo.Product;
import com.mmpnc.rating.vo.RateTable;

/**
 * Author : Shashikant Mhatre
 *
 * Created On : Oct 12, 2013
 *
 * Description :
 *
 * Note :
 */

public interface DataEvaluator {

	Object evaluate();
	void setContext(Context context);
	void setProduct(Product product);
	void setRateTable(RateTable rateTable);
	void setDomainTable(DomainTable domainTable);
}
