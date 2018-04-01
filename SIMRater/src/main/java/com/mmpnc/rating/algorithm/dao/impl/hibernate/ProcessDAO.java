package com.mmpnc.rating.algorithm.dao.impl.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mmpnc.rating.dao.impl.hibernate.ObjectDAO;
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

public class ProcessDAO extends ObjectDAO {
	private Process process;
	private static final Logger logger = LoggerFactory.getLogger(ProcessDAO.class);
	
	public ProcessDAO(Process process){
		this.process = process;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T,S> T getUniqueRecord(S session) {
		boolean condition = false;
		StringBuffer sb = new StringBuffer(
				"from Process as process where ");
		
		if(this.process.getId() != null && !this.process.getId().equals("")){
			sb = updateQuery(sb, condition, process, "id");
			condition = true;
		}
		
		if(this.process.getName() != null ){
			sb = updateQuery(sb, condition, process, "name");
			condition = true;
		}

		if(this.process.getExecutionorder() != -1 ){
			sb = updateQuery(sb, condition, process, "executionorder");
			condition = true;
		}

		if(this.process.getEntity() != null && (this.process.getEntity().getId() != null && !this.process.getEntity().getId().equals("") ) ){
			sb = updateQuery(sb, condition, process, "entity.id");
			condition = true;
		}
		
		if (logger.isInfoEnabled()) {
			logger.info("Process Query -> " + sb);
		}

		this.process = (Process) ((Session)session).createQuery(sb.toString())
				.uniqueResult();
		
		return (T) this.process;
	}

	@Override
	public <S> void save(S session) {
		// TODO Auto-generated method stub
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T, S> List<T> getRecords(S session) {
		boolean condition = false;
		StringBuffer sb = new StringBuffer(
				"from Process as process where ");
		
		if(this.process.getId() != null && !this.process.getId().equals("")){
			sb = updateQuery(sb, condition, process, "id");
			condition = true;
		}
		
		if(this.process.getName() != null && ! this.process.getName().equals("")){
			sb = updateQuery(sb, condition, process, "name");
			condition = true;
		}

		if(this.process.getExecutionorder() != -1 ){
			sb = updateQuery(sb, condition, process, "executionorder");
			condition = true;
		}

		if(this.process.getEntity() != null && (this.process.getEntity().getId() != null && !this.process.getEntity().getId().equals("") ) ){
			sb = updateQuery(sb, condition, process, "entity.id");
			condition = true;
		}
		
		if (logger.isInfoEnabled()) {
			logger.info("Process Query -> " + sb);
		}

		return (List<T>)((Session)session).createQuery(sb.toString()).list();
		
	}

}
