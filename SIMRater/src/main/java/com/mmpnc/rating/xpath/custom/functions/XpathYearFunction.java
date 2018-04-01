package com.mmpnc.rating.xpath.custom.functions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.jaxen.Context;
import org.jaxen.Function;
import org.jaxen.FunctionCallException;
import org.jaxen.Navigator;
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

public class XpathYearFunction implements Function {

	private static final Logger logger = LoggerFactory
			.getLogger(XpathYearFunction.class);
	
	@SuppressWarnings("rawtypes")
	@Override
	public Object call(Context context, List args) throws FunctionCallException {
//		System.out.println("i am getting called");
		if (args.size() == 1) {
			return evaluate(args.get(0), context.getNavigator());
		}

		throw new FunctionCallException("Year Function requires one argument.");
	}

	@SuppressWarnings("rawtypes")
	public static int evaluate(Object obj, Navigator nav) {

//		System.out.println("object " + obj.getClass());
//		System.out.println("object " + obj.toString());

		if (nav != null && nav.isText(obj)) {
			if(logger.isInfoEnabled()){
				logger.info("We got a string value instead of an element");
			}
			return getYear(nav.getTextStringValue(obj));
		}

		if (obj instanceof List)
        {
            List parameters = (List) obj;
            if (parameters.isEmpty())
            {
                return 0;
            }
            // do not recurse: only first list should unwrap
            obj = parameters.get(0);
        }
		
		if (nav != null) {
			if (nav.isElement(obj)) {
				return getYear(nav.getElementStringValue(obj));
			} else if (nav.isText(obj)) {
                return getYear(nav.getTextStringValue(obj));
            }
		}
		
		 if (obj instanceof String)
         {
             return getYear((String)obj);
         }

		return 0;
	}
	
	private static int getYear(String str){
		String date = str.substring(
				0,
				(str.indexOf("T") == -1 ? str.length() : str
						.indexOf("T")));

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Calendar cal = Calendar.getInstance();
		try {
			cal.setTime(sdf.parse(date));
			
			if(logger.isInfoEnabled()){
				logger.info("Date is " + cal.toString() + " ----- Year : " + cal.get(Calendar.YEAR));
			}
			
		} catch (ParseException e) {
			logger.error("Error in XpathYearFunction : get-year function", e);
		}
		
		return cal.get(Calendar.YEAR);
	}
	
}
