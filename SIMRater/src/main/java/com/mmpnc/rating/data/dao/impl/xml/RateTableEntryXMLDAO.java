package com.mmpnc.rating.data.dao.impl.xml;

import java.util.List;

import org.apache.commons.math3.analysis.interpolation.LinearInterpolator;
import org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mmpnc.connection.helper.Session;

import com.mmpnc.rating.dao.impl.xml.XMLDAO;
import com.mmpnc.rating.dao.util.JAXBUtil;
import com.mmpnc.rating.vo.RateTable;
import com.mmpnc.rating.vo.RateTableConfiguration;
import com.mmpnc.rating.vo.RateTableEntry;

/**
 * Author : Shashikant Mhatre
 *
 * Created On : Oct 12, 2013
 *
 * Description :
 *
 * Note :
 */

public class RateTableEntryXMLDAO extends XMLDAO{

	private RateTable rt;
	private Object[] values;

	private static final Logger logger = LoggerFactory
			.getLogger(RateTableEntryXMLDAO.class);

	public RateTableEntryXMLDAO(RateTable rt, Object[] values) {
		super();
		this.rt = rt;
		this.values = values;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T,S> T getUniqueRecord(S session) {
		boolean condition = false;
		RateTableEntry entry = null;

		StringBuffer orderBy = new StringBuffer(" order by ");
		boolean orderClause = false;

		StringBuffer sb = new StringBuffer(
				"for $x in ");
//		sb.append("/Product[Id ='").append(this.rt.getProductId().getId()).append("']").append("/RateTable[Id ='").append(this.rt.getId()).append("']");
//		sb.append("/RateTableEntry where ");
		sb.append("/Product/RateTable/RateTableEntry where ");

		if (this.rt != null && rt.getId() != null) {
//			sb = updateQuery(sb, condition, "ratetableentry.rateTable.id", "'"+ this.rt.getId() + "'");
			sb.append("$x/RateTable").append("='").append(this.rt.getId()).append("' ");
			
			sb.append(" and ");
			
			sb.append("$x/Product").append("='").append(this.rt.getProductId().getId()).append("' ");
			condition = true;
		}

		if(condition){
			sb.append(" and ");
		}
		
//		sb = updateQuery(sb, condition, "ratetableentry.status","'A'");
		sb.append("$x/Status").append("='A' ");

		StringBuffer countString = new StringBuffer();
		countString.append("count( ");
		countString.append(sb);
		countString.append("return $x )");

		if (logger.isInfoEnabled()) {
			logger.info("RateTableEntry Count Query -> " + countString);
//			logger.info("RateTableEntry Count Query Execution Start Time , " + Calendar.getInstance().getTimeInMillis());
		}
		
		
		long count = Long.parseLong((String)((Session)session).createQuery(countString.toString()).execute());

//		if (logger.isInfoEnabled()) {
//			logger.info("RateTableEntry Count Query Execution End Time , " + Calendar.getInstance().getTimeInMillis());
//		}

		
		int valueCount = 0;

		if (count > 0) {
			
			Object[][] interpolationColumn = new Object[1][2];
			boolean interpolationApply = false;
			
			if (logger.isInfoEnabled()) {
				logger.info("Records Found");
			}
			
			for (RateTableConfiguration rtConfig : rt.getRateTableConfiguration()) {

				if(rtConfig.getConfigurationType().equals("VALUE")){
					continue;
				}				
				
				if(rtConfig.getLookupType() == null || rtConfig.getLookupType().equals("user") ){
					rtConfig.setLookupType("None");
				}
				
				
				VarType varType = VarType.valueOf(rtConfig.getType().toUpperCase());

				if (varType == VarType.NVARCHAR) {
					StringBuffer left = new StringBuffer();
					left.append("$x/Column").append(
							rtConfig.getSequence());

					StringBuffer right = new StringBuffer();
//					right.append("'").append(values[rtkey.getSequence() - 1]).append("'");
					right.append("'").append(values[valueCount++]).append("' ");

					if(condition){
						sb.append(" and ");
					}
//					sb = updateQuery(sb, condition, left.toString(),right.toString(),getOperator(rtConfig.getLookupType()));
					sb.append(left.toString()).append(getOperator(rtConfig.getLookupType())).append(right.toString());
					
					condition = true;
				} else {
					
					if(OperatorTypes.valueOf(rtConfig.getLookupType()).equals(OperatorTypes.Interpolate)){
						
						interpolationColumn[0][0] = rtConfig.getSequence();
						interpolationColumn[0][1] = values[valueCount++];
						interpolationApply = true;
						//continue to the next iteration of loop
						continue;
					}
					
					StringBuffer left = new StringBuffer();
					left.append("number(").append("$x/NColumn").append(
							rtConfig.getSequence()).append(")");

					StringBuffer right = new StringBuffer();
//					right.append(values[rtkey.getSequence() - 1]);
					right.append(values[valueCount++]).append(" ");

					if(condition){
						sb.append(" and ");
					}
					
//					sb = updateQuery(sb, condition, left.toString(),right.toString(),getOperator(rtConfig.getLookupType()));
					sb.append(left.toString()).append(getOperator(rtConfig.getLookupType())).append(right.toString());
					
					condition = true;
					
//					if(logger.isInfoEnabled()){
//						logger.info("we got " + rtConfig.getLookupType() + " : " + OperatorTypes.valueOf(rtConfig.getLookupType()));					
//					}
					
					switch (OperatorTypes.valueOf(rtConfig.getLookupType())) {
					case ExactMatch:
						break;
					case GreaterThan:
					case GreaterThanOrEqual:
						if (orderClause == true) {
							orderBy.append(", ");
						}
						// implemented as per ISO
						orderBy.append("number($x/NColumn")
								.append(rtConfig.getSequence()).append(")").append(" descending ");
						
						// implemented for MAIF
//						orderBy.append("ratetableentry.nColumn")
//						.append(rtConfig.getSequence()).append(" asc ");

						orderClause = true;
						break;
					case Interpolate:
						break;
					case LessThanOrEqual:
						if (orderClause == true) {
							orderBy.append(", ");
						}
						// implemented as per ISO
						orderBy.append("number($x/NColumn")
								.append(rtConfig.getSequence()).append(")").append(" ascending ");
						
						// implemented for MAIF
//						orderBy.append("ratetableentry.nColumn")
//						.append(rtConfig.getSequence()).append(" desc ");
						orderClause = true;
						break;
					default:
						break;
					}

				}

			}

			if (orderClause) {
				sb.append(orderBy);
			} else if(interpolationApply){
				sb.append(" order by number($x/Sequence) ascending ");
			}
			
			sb.append("return $x");
			
			if (logger.isInfoEnabled()) {
				logger.info("RateTableEntry Query -> " + sb);
//				logger.info("RateTableEntry Query Execution Start Time , " + Calendar.getInstance().getTimeInMillis());
			}

//			Query query = ((Session)session).createQuery(sb.toString());
//			query.setMaxResults(1);
			
			StringBuffer outputStringBuffer = new StringBuffer();
			try{
				outputStringBuffer.append("<RateTable>");
				outputStringBuffer.append(((Session)session).createQuery(sb.toString()).execute());
				outputStringBuffer.append("</RateTable>");
			} catch(Exception _ex){
				logger.error("Error Occurred ******** : ", _ex);
				return (T) entry;
			}
			
//			if (logger.isInfoEnabled()) {
//				logger.info("RateTableEntry Query Execution End Time , " + Calendar.getInstance().getTimeInMillis());
//			}
			
			RateTable rateTable = new RateTable();
			try{
//				Reader stringReader = new StringReader(outputStringBuffer.toString());
//				JAXBContext jc = JAXBContext.newInstance(RateTable.class);
//				Unmarshaller um = jc.createUnmarshaller();
//				rateTable = (RateTable) um.unmarshal(stringReader);
				rateTable = JAXBUtil.getInstance().unmarshalObject(outputStringBuffer.toString());
			}catch(Exception _ex){
				_ex.printStackTrace();
			}
			
//			if (logger.isInfoEnabled()) {
//				logger.info("RateTableEntry JAXB Object Creation Completion Time , " + Calendar.getInstance().getTimeInMillis());
//			}
			
			if (orderClause) {
					if (rateTable.getRateTableEntry() != null && rateTable.getRateTableEntry().size() > 0) {
					entry = rateTable.getRateTableEntry().get(0);
				}
			} else if(interpolationApply){ 
				
				if(logger.isInfoEnabled()){
					logger.info("We are in interpolation function to get the interpolation value for " + interpolationColumn[0][1] + " with value stored in column " + interpolationColumn[0][0]);
				}
				
				List<RateTableEntry> entries = rateTable.getRateTableEntry();
				
				if(logger.isInfoEnabled()){
					logger.info("we got the total values " + entries.size());
				}
				
				try{					
					LinearInterpolator intpl = new LinearInterpolator();
					double[] x = new double[entries.size()];
					double[] y = new double[entries.size()];
					
					int rtcount = 0;
					
					for(RateTableEntry rtentry : entries){
						
						switch((Integer)interpolationColumn[0][0]){
						case 1:
							x[rtcount] = rtentry.getnColumn1();						
							break;
						case 2:
							x[rtcount] = rtentry.getnColumn2();
							break;
						case 3:
							x[rtcount] = rtentry.getnColumn3();
							break;
						case 4:
							x[rtcount] = rtentry.getnColumn4();
							break;
						case 5:
							x[rtcount] = rtentry.getnColumn5();
							break;
						case 6:
							x[rtcount] = rtentry.getnColumn6();
							break;
						case 7:
							x[rtcount] = rtentry.getnColumn7();
							break;
						case 8:
							x[rtcount] = rtentry.getnColumn8();
							break;
						case 9:
							x[rtcount] = rtentry.getnColumn9();
							break;
						case 10:
							x[rtcount] = rtentry.getnColumn10();
							break;
						}
						y[rtcount] = rtentry.getReturnNValue();
						rtcount++;
					}
					
					PolynomialSplineFunction  pol = intpl.interpolate(x,y);
					double value = 0;
					if(interpolationColumn[0][1] instanceof String){
						value = pol.value(Double.valueOf((String)interpolationColumn[0][1]));
					}else{
						value = pol.value((Double)interpolationColumn[0][1]);
					}
					
	
					if(logger.isInfoEnabled()){
						logger.info("We got the interpolation value as " + value);
					}
					
					entry = new RateTableEntry();
					entry.setReturnNValue(value);
					
				}catch(Exception _ex){
					logger.error("Error occured during interpolation ", _ex);
				}

					
				
			} else {
//				entry = (RateTableEntry) query.uniqueResult();
				if (rateTable.getRateTableEntry() != null && rateTable.getRateTableEntry().size() > 0) {
					entry = rateTable.getRateTableEntry().get(0);
				} 
			}

		} else {
			if (logger.isInfoEnabled()) {
				logger.info("no record");
			}
		}

		return (T) entry;
	}

	private String getOperator(String operation) {
		OperatorTypes op = OperatorTypes.valueOf(operation);
		switch (op) {
		case ExactMatch:
			return "=";
		case GreaterThan:
			return ">";
		case GreaterThanOrEqual:
			// Implemented as per ISO
			return "<=";
			
			// implemented for MAIF
//			return ">=";
		case Interpolate:
			if (logger.isInfoEnabled()) {
				logger.info("***********************(Interpolate Value)*************************");
			}
			return "=";
		case LessThanOrEqual:
			// Implemented as per ISO
			return ">=";
			
			// implemented for MAIF
//			return "<=";
		case LessThan:
			return "<";
		default:
			return "=";
		}
	}

	private enum OperatorTypes {
		ExactMatch, GreaterThan, GreaterThanOrEqual, Interpolate, LessThan, LessThanOrEqual,None ;
	}

	private enum VarType {
		DECIMAL, NVARCHAR, INTEGER, INT;
	}

	@Override
	public <S> void save(S session) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <T, S> List<T> getRecords(S session) {
		// TODO Auto-generated method stub
		return null;
	}
}
