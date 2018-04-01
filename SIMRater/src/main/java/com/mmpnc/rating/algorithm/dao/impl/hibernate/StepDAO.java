package com.mmpnc.rating.algorithm.dao.impl.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mmpnc.rating.dao.impl.hibernate.ObjectDAO;
import com.mmpnc.rating.vo.Step;

/**
 * Author : Shashikant Mhatre
 *
 * Created On : Oct 12, 2013
 *
 * Description :
 *
 * Note :
 */

public class StepDAO extends ObjectDAO {
	private Step step;
	private static final Logger logger = LoggerFactory.getLogger(StepDAO.class);

	public StepDAO(Step step) {
		this.step = step;
	}

	
	@Override
	public <T,S> T getUniqueRecord(S session) {
		if (logger.isInfoEnabled()) {
			logger.info("Need to implement StepDAO getUniqueRecord");
		}
		return null;
	}

	@Override
	public <S> void save(S session) {
		// TODO Auto-generated method stub
		
	}


	@SuppressWarnings("unchecked")
	@Override
	public <T, S> List<T> getRecords(S session) {
		
		List<Step> steps = new ArrayList<Step>();
		boolean condition = false;
		
		StringBuffer sb = new StringBuffer("from Step as step where ");
		
		if (this.step.getProgram() != null) {
			if( this.step.getProgram().getId() != null && !this.step.getProgram().getId().equals("")) {
				sb = updateQuery(sb, condition, this.step, "program.id");
				condition = true;
			}
			
			if( this.step.getProcess().getId() != null && !this.step.getProcess().getId().equals("")) {
				sb = updateQuery(sb, condition, this.step, "process.id");
				condition = true;
			}
			
			sb.append(" order by executionorder asc");
		}
		
		try{
			steps = (List<Step>) ((Session)session).createQuery(sb.toString()).list();
			} catch(Exception _ex){
				logger.error("query is " + sb);
				logger.error("Error occured to get the Steps " + _ex.toString());
			}		
		
		return (List<T>) steps;
	}

}
