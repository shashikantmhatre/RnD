package com.mmpnc.rating.algorithm.dao.impl.xml;

import java.io.Reader;
import java.io.StringReader;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mmpnc.connection.helper.Session;
import com.mmpnc.rating.dao.impl.xml.XMLDAO;
import com.mmpnc.rating.dao.util.JAXBUtil;
import com.mmpnc.rating.vo.Process;
import com.mmpnc.rating.vo.Program;
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

public class StepXMLDAO extends XMLDAO {
	private Step step;
	private Program program;
	private Process process;
	private static final Logger logger = LoggerFactory.getLogger(StepXMLDAO.class);

	public StepXMLDAO(Step step) {
		this.step = step;
		this.program = this.step.getProgram();
		this.process = this.step.getProcess();
	}

	
	@Override
	public <T,S> T getUniqueRecord(S session) {
		// TODO Auto-generated method stub
		if (logger.isInfoEnabled()) {
			logger.info("Need to implement StepXMLDAO getUniqueRecord");
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
		boolean condition = false;
		
		StringBuffer sb = new StringBuffer("for $x in /Product/RatingEntity/RatingProcess/RatingProgram ");
		
		sb.append("let $y := $x/ProgramStep ");
		sb.append("where ");
		
		if (this.step.getProgram() != null) {
			if( this.step.getProgram().getId() != null && !this.step.getProgram().getId().equals("")) {
//				sb = updateQuery(sb, condition, this.step, "program.id");
				sb.append("$x/Id").append("='").append(this.step.getProgram().getId()).append("' ");
				condition = true;
			}
			
			if( this.step.getProcess().getId() != null && !this.step.getProcess().getId().equals("")) {
				
				if(condition){
					sb.append(" and ");
				}
				
//				sb = updateQuery(sb, condition, this.step, "process.id");
				sb.append("$x/RatingProcess").append("='").append(this.step.getProcess().getId()).append("' ");
				condition = true;
			}
			
			sb.append("for $z in $y ");
			sb.append("order by number($z/ExecutionOrder) ascending "); 
			sb.append("return $z");
		}
		
		if (logger.isInfoEnabled()) {
			logger.info("ProgramStep Query -> " + sb);
		}

//		System.out.println("ProgramStep Query " + sb);
		
		StringBuffer recordsString = new StringBuffer();
		recordsString.append("<RatingProgram>");
		recordsString.append((String) ((Session)session).createQuery(sb.toString()).execute());
		recordsString.append("</RatingProgram>");
		
		
		Program ratingProgram = new Program();
		try{
//			Reader stringReader = new StringReader(recordsString.toString());
//			JAXBContext jc = JAXBContext.newInstance(Program.class);
//			Unmarshaller um = jc.createUnmarshaller();
//			ratingProgram = (Program) um.unmarshal(stringReader);
			ratingProgram = JAXBUtil.getInstance().unmarshalObject(recordsString.toString());
		} catch(Exception _ex){
			logger.error("query is " + sb);
			logger.error("Error occured to get the Step Records " + _ex.toString());
		}
		
		//This was added becuase XMLDB returns xml string without ratingprogram and ratingprocess object in step
		for(Step step : ratingProgram.getSteps()){
			if(step.getProgram() == null){
				step.setProgram(this.program);
			}
			
			if(step.getProcess() == null){
				step.setProcess(this.process);
			}
		}
		
		return (List<T>) ratingProgram.getSteps();
	}

}
