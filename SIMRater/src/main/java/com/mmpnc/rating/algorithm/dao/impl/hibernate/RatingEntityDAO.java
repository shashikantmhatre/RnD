package com.mmpnc.rating.algorithm.dao.impl.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mmpnc.rating.dao.impl.hibernate.ObjectDAO;
import com.mmpnc.rating.vo.Program;
import com.mmpnc.rating.vo.RatingEntity;
import com.mmpnc.rating.vo.Process;
import com.mmpnc.rating.vo.Step;
import com.mmpnc.rating.vo.UUIDGenerator;

/**
 * Author : Shashikant Mhatre
 *
 * Created On : Oct 12, 2013
 *
 * Description :
 *
 * Note :
 */

public class RatingEntityDAO extends ObjectDAO {

	private RatingEntity ratingEntity;
	private static final Logger logger = LoggerFactory.getLogger(RatingEntityDAO.class);
	
	public RatingEntityDAO(RatingEntity ratingEntity){
		this.ratingEntity = ratingEntity;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T, S> T getUniqueRecord(S session) {
		boolean condition = false;
		StringBuffer sb = new StringBuffer(
				"from RatingEntity as ratingentity where ");

		if (this.ratingEntity.getName() != null) {
			sb = updateQuery(sb, condition, ratingEntity, "name");
			condition = true;
		}

		if (this.ratingEntity.getId() != null && !this.ratingEntity.getId().equals("")) {
			sb = updateQuery(sb, condition, ratingEntity, "id");
			condition = true;
		}

		if (this.ratingEntity.getProduct() != null && ( this.ratingEntity.getProduct().getId() != null && !this.ratingEntity.getProduct().getId().equals(""))) {
			sb = updateQuery(sb, condition, ratingEntity, "product.id");
			condition = true;
		}

		if (logger.isInfoEnabled()) {
			logger.info("RatingEntity Query -> " + sb);
		}

		this.ratingEntity = (RatingEntity) ((Session)session).createQuery(sb.toString())
				.uniqueResult();

		return (T) this.ratingEntity;
	}

	@Override
	public <S> void save(S session) {
		Session hSession = (Session) session;	
		
//		System.out.println(ratingEntity.getId());
		
		hSession.saveOrUpdate(ratingEntity);
		
		for(Process process : ratingEntity.getProcesses()){
			
			if(process.getEntity().getId() == null){
//				System.out.println("set entity for process " + process.getName() + " : " + ratingEntity.getName());
				process.setEntity(ratingEntity);
			} 
			
			if(process.getId() == null){
				String processIDKey = ratingEntity.getId() + "-" + process.getName();
				process.setId(UUIDGenerator.generate(processIDKey).toUpperCase());
//				System.out.println("set process id as " + process.getId());
			}
			
			hSession.saveOrUpdate(process);
			
			int count = 0;
			for(Step step : process.getSteps()){
				
				step.setExecutionorder(count);
				
				if(step.getProcess() == null){
					step.setProcess(process);
				}
				
				if(step.getId() == null){
					String stepIDKey = process.getId() + "-" + step.getExecutionorder();
					step.setId(UUIDGenerator.generate(stepIDKey).toUpperCase());
				}
				
				hSession.saveOrUpdate(step);
				count++;
			}
			
			for(Program program : process.getPrograms()){
				
				if(program.getProcess().getId() == null){
					program.setProcess(process);
//					System.out.println("set the process as " + process.getId());
				}
				
				if(program.getId() == null){
					String programIDKey = process.getId() + "-" + program.getName();
					program.setId(UUIDGenerator.generate(programIDKey).toUpperCase());
					
//					System.out.println("after creating id - " + program.getId());
					
				}
				
				System.out.println("Saving Program - " + program.getName());
				
				hSession.saveOrUpdate(program);
				
				
				count = 0;
				for(Step step : program.getSteps()){
					step.setExecutionorder(count);
					
					if(step.getProcess() == null){
						step.setProcess(process);
					}
					
					if(step.getProgram() == null){
						step.setProgram(program);
					}
					
					if(step.getId() == null){
						String stepIDKey = program.getId() + "-" + step.getExecutionorder();
						step.setId(UUIDGenerator.generate(stepIDKey).toUpperCase());
					}
					
//					System.out.println("stepID - " + step.getId());
					
					hSession.saveOrUpdate(step);
					count++;
				}
				
			}
			
		}
		
	}

	@Override
	public <T, S> List<T> getRecords(S session) {
		// TODO Auto-generated method stub
		return null;
	}

}
