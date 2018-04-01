package com.mmpnc.rating.data.dao.impl.hibernate;

import java.util.List;

import org.apache.commons.math3.analysis.interpolation.LinearInterpolator;
import org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction;
import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mmpnc.rating.dao.impl.hibernate.ObjectDAO;
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

public class RateTableEntryDAO extends ObjectDAO {

	private RateTable rt;
	private Object[] values;

	private static final Logger logger = LoggerFactory
			.getLogger(RateTableEntryDAO.class);

	public RateTableEntryDAO(RateTable rt, Object[] values) {
		super();
		this.rt = rt;
		this.values = values;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T,S> T getUniqueRecord(S session) {
		boolean condition = false;
		RateTableEntry entry = null;

		StringBuffer orderBy = new StringBuffer(" Order By ");
		boolean orderClause = false;

		StringBuffer sb = new StringBuffer(
				"from RateTableEntry as ratetableentry where ");

		if (this.rt != null && rt.getId() != null) {
			sb = updateQuery(sb, condition, "ratetableentry.rateTable.id", "'"
					+ this.rt.getId() + "'");
			condition = true;
		}

		sb = updateQuery(sb, condition, "ratetableentry.status",
				"'A'");

		if (logger.isInfoEnabled()) {
			logger.info("Query -> " + sb.toString());
		}

		StringBuffer countString = new StringBuffer();
		countString.append("Select count(id) ");
		countString.append(sb);

		long count = (Long) ((Session)session).createQuery(countString.toString())
				.uniqueResult();
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
					left.append("ratetableentry.column").append(
							rtConfig.getSequence());

					StringBuffer right = new StringBuffer();
//					right.append("'").append(values[rtkey.getSequence() - 1]).append("'");
					right.append("'").append(values[valueCount++]).append("'");

					sb = updateQuery(sb, condition, left.toString(),
							right.toString(),
							getOperator(rtConfig.getLookupType()));
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
					left.append("ratetableentry.nColumn").append(
							rtConfig.getSequence());

					StringBuffer right = new StringBuffer();
//					right.append(values[rtkey.getSequence() - 1]);
					right.append(values[valueCount++]);

					sb = updateQuery(sb, condition, left.toString(),
							right.toString(),
							getOperator(rtConfig.getLookupType()));
					condition = true;

					logger.info("we got " + rtConfig.getLookupType() + " : " + OperatorTypes.valueOf(rtConfig.getLookupType()));					
					
					switch (OperatorTypes.valueOf(rtConfig.getLookupType())) {
					case ExactMatch:
						break;
					case GreaterThan:
					case GreaterThanOrEqual:
						if (orderClause == true) {
							orderBy.append(", ");
						}
						// implemented as per ISO
						orderBy.append("ratetableentry.nColumn")
								.append(rtConfig.getSequence()).append(" desc ");
						
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
						orderBy.append("ratetableentry.nColumn")
								.append(rtConfig.getSequence()).append(" asc ");
						
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
				sb.append(" order by ratetableentry.sequence asc ");
			}

			if (logger.isInfoEnabled()) {
				logger.info("Query -> " + sb.toString());
			}

			Query query = ((Session)session).createQuery(sb.toString());
//			query.setMaxResults(1);

			if (orderClause) {
				List<RateTableEntry> entries = (List<RateTableEntry>) query
						.list();
				if (entries != null && entries.size() > 0) {
					entry = entries.get(0);
				}
			} else if(interpolationApply){ 
				
				if(logger.isInfoEnabled()){
					logger.info("We are in interpolation function to get the interpolation value for " + interpolationColumn[0][1] + " with value stored in column " + interpolationColumn[0][0]);
				}
				
				List<RateTableEntry> entries = (List<RateTableEntry>) query
						.list();
				
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
				entry = (RateTableEntry) query.uniqueResult();
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
