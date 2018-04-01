package com.mmpnc.rating.algorithm.util;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Author : Shashikant Mhatre
 *
 * Created On : Oct 12, 2013
 *
 * Description :
 *
 * Note :
 */

public class CustomFunctions {

	private static final Logger logger = LoggerFactory
			.getLogger(CustomFunctions.class);

	public static BigDecimal getDeductible(Object[] values) {
		BigDecimal big = new BigDecimal(0);
		if (values.length > 0) {
			logger.error("Convert String " + values[0] + " to BigDecimal");
			String str = (String) values[0];
			if (str != null && ! str.equals("")) {
				StringBuffer number = new StringBuffer();
				for (char ch : str.toCharArray()) {
					switch (ch) {
					case '0':
					case '1':
					case '2':
					case '3':
					case '4':
					case '5':
					case '6':
					case '7':
					case '8':
					case '9':
					case '.':
						number.append(ch);
						break;
					default:
						continue;
					}
				}
				big = new BigDecimal(number.toString());
			}
		}
		return big;
	}
}
