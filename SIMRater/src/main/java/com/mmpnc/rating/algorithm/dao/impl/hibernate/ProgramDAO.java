package com.mmpnc.rating.algorithm.dao.impl.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mmpnc.rating.dao.impl.hibernate.ObjectDAO;
import com.mmpnc.rating.vo.Program;

/**
 * Author : Shashikant Mhatre
 *
 * Created On : Oct 12, 2013
 *
 * Description :
 *
 * Note :
 */

public class ProgramDAO extends ObjectDAO {
	private Program program;
	
	private static final Logger logger = LoggerFactory
			.getLogger(ProgramDAO.class);

	public ProgramDAO(Program program) {
		this.program = program;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T,S> T getUniqueRecord(S session) {
		boolean condition = false;
		
		StringBuffer sb = new StringBuffer("from Program as program where ");

		if (this.program.getName() != null) {
			sb = updateQuery(sb, condition, program, "name");
			condition = true;
		}

		if (this.program.getProcess() != null) {
			
			if( this.program.getProcess().getId() != null && !this.program.getProcess().getId().equals("")) {
				sb = updateQuery(sb, condition, program, "process.id");
				condition = true;
			}
			if(! this.program.getProcess().getName().equals("")){
				sb = updateQuery(sb, condition, program, "process.name");
				condition = true;
			}
		}

		if (logger.isInfoEnabled()) {
			logger.info("Program Query -> " + sb);
		}

		try{
		this.program = (Program) ((Session)session).createQuery(sb.toString())
				.uniqueResult();
		} catch(Exception _ex){
			logger.error("query is " + sb);
			logger.error("Error occured to get the Program " + _ex.toString());
		}
		
//		this.program = (Program)session.load(Program.class, program);
		
		return (T) this.program;
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
