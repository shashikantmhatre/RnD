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
import com.mmpnc.rating.vo.RatingEntity;

/**
 * Author : Shashikant Mhatre
 *
 * Created On : Oct 12, 2013
 *
 * Description :
 *
 * Note :
 */

public class ProcessXMLDAO extends XMLDAO {
	private Process process;
	private RatingEntity ratingEntity;
	private static final Logger logger = LoggerFactory.getLogger(ProcessXMLDAO.class);
	
	public ProcessXMLDAO(Process process){
		this.process = process;
		this.ratingEntity = this.process.getEntity();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T,S> T getUniqueRecord(S session) {
		boolean condition = false;
		StringBuffer sb = new StringBuffer(
				"for $x in /Product/RatingEntity ");
		
		sb.append("let $y := $x/RatingProcess [");
		
		sb.append(" . ");
		condition = true;
		
		if(this.process.getId() != null && !this.process.getId().equals("")){
			if(condition){
				sb.append(" and ");
			}
//			sb = updateQuery(sb, condition, process, "id");
			sb.append("Id").append("='").append(this.process.getId()).append("' ");
			condition = true;
		}
		
		if(this.process.getName() != null && !this.process.getName().equals("")){
			if(condition){
				sb.append(" and ");
			}
//			sb = updateQuery(sb, condition, process, "name");
			sb.append("Name").append("='").append(this.process.getName()).append("' ");
			condition = true;
		}

		if(this.process.getExecutionorder() != -1 ){
			if(condition){
				sb.append(" and ");
			}
//			sb = updateQuery(sb, condition, process, "executionorder");
			sb.append("ExecutionOrder").append("='").append(this.process.getExecutionorder()).append("' ");
			condition = true;
		}
		
		sb.append("] ");
		
		condition = false;
		
		sb.append("where ");
		
		if(this.process.getEntity() != null ){
			if((this.process.getEntity().getId() != null && !this.process.getEntity().getId().equals("") )){
//				sb = updateQuery(sb, condition, process, "entity.id");
				sb.append("$x/Id").append("='").append(this.process.getEntity().getId()).append("' ");
				condition = true;
			}
			
			if((this.process.getEntity().getName() != null && !this.process.getEntity().getName().equals("") )){
				
				if(condition){
					sb.append(" and ");
				}
				
				sb.append("$x/Name").append("='").append(this.process.getEntity().getName()).append("' ");
				condition = true;
			}
			
			if(this.process.getEntity().getProduct() != null){
				if(this.process.getEntity().getProduct().getId() != null && !this.process.getEntity().getProduct().getId().equals("")){
					if(condition){
						sb.append(" and ");
					}
					
					sb.append("$x/Product").append("='").append(this.process.getEntity().getProduct().getId()).append("' ");
					condition = true;					
				}
				
			}		
		}
			

		sb.append("return <RatingProcess>{$y/*[local-name()=('Id','Name','ExecutionOrder','ProcessStep')]}</RatingProcess>");
		
		if (logger.isInfoEnabled()) {
			logger.info("Process Query -> " + sb);
		}

//		System.out.println("Process Query " + sb);
		
		
		String outputString = (String) ((Session)session).createQuery(sb.toString()).execute();
		
		if(outputString != null && !outputString.equals("")){
			try{
//				Reader stringReader = new StringReader(outputString);
//				JAXBContext jc = JAXBContext.newInstance(Process.class);
//				Unmarshaller um = jc.createUnmarshaller();
//				this.process = (Process) um.unmarshal(stringReader);
				this.process = JAXBUtil.getInstance().unmarshalObject(outputString);
			} catch(Exception _ex){
				logger.error("query is " + sb);
				logger.error("Error occured to get the Process " + _ex.toString());
			}
		
			//This was added becuase XMLDB returns xml string without ratingentity object in process
			if(this.process.getEntity() == null){
				this.process.setEntity(this.ratingEntity);
			}
		} else {
			this.process = null;
		}
				
		return (T) this.process;
	}

	@Override
	public <S> void save(S session) {
		// TODO Auto-generated method stub
		
	}

	
	/*
	 * (non-Javadoc) 
	 * for $x in /Product/RatingEntity 
	 * let $y := $x/RatingProcess
	 * where $x/Name='CommercialAuto' and
	 * $x/Product='93BBD210-679F-4516-BA9F-3629CFE79583' return
	 * <RatingProcess>{$y/*[local-name()=(
	 * 'Id','Name','Product','ExecutionOrder','ProcessStep')]}</RatingProcess>
	 */
	
	@SuppressWarnings("unchecked")
	@Override
	public <T, S> List<T> getRecords(S session) {
		boolean condition = false;
		StringBuffer sb = new StringBuffer(
				"for $x in /Product/RatingEntity ");
		
		sb.append("let $y := $x/RatingProcess [ ");
		
		sb.append(" . ");
		condition = true;
		
		if(this.process.getId() != null && !this.process.getId().equals("")){
			if(condition){
				sb.append(" and ");
			}
//			sb = updateQuery(sb, condition, process, "id");
			sb.append("Id").append("='").append(this.process.getId()).append("' ");
			condition = true;
		}
		
		if(this.process.getName() != null && !this.process.getName().equals("")){
			if(condition){
				sb.append(" and ");
			}
//			sb = updateQuery(sb, condition, process, "name");
			sb.append("Name").append("='").append(this.process.getName()).append("' ");
			condition = true;
		}

		if(this.process.getExecutionorder() != -1 ){
			if(condition){
				sb.append(" and ");
			}
//			sb = updateQuery(sb, condition, process, "executionorder");
			sb.append("ExecutionOrder").append("='").append(this.process.getExecutionorder()).append("' ");
			condition = true;
		}
		
		sb.append("] ");
		
		condition = false;
		
		sb.append("where ");
		
		if(this.process.getEntity() != null ){
			if((this.process.getEntity().getId() != null && !this.process.getEntity().getId().equals("") )){
//				sb = updateQuery(sb, condition, process, "entity.id");
				sb.append("$x/Id").append("='").append(this.process.getEntity().getId()).append("' ");
				condition = true;
			}
			
			if((this.process.getEntity().getName() != null && !this.process.getEntity().getName().equals("") )){
				
				if(condition){
					sb.append(" and ");
				}
				
				sb.append("$x/Name").append("='").append(this.process.getEntity().getName()).append("' ");
				condition = true;
			}
			
			if(this.process.getEntity().getProduct() != null){
				if(this.process.getEntity().getProduct().getId() != null && !this.process.getEntity().getProduct().getId().equals("")){
					if(condition){
						sb.append(" and ");
					}
					
					sb.append("$x/Product").append("='").append(this.process.getEntity().getProduct().getId()).append("' ");
					condition = true;					
				}
				
			}		
		}

		sb.append("return <RatingProcess>{$y/*[local-name()=('Id','Name','ExecutionOrder','ProcessStep')]}</RatingProcess>");
		
		if (logger.isInfoEnabled()) {
			logger.info("Process Query -> " + sb);
		}

//		System.out.println("Process Query " + sb);

		String outputString = (String) ((Session)session).createQuery(sb.toString()).execute();
		
		RatingEntity re = new RatingEntity();
		
		if(outputString != null && ! outputString.equals("")){
			StringBuffer recordsString = new StringBuffer();
			recordsString.append("<RatingEntity>");
			recordsString.append(outputString);
			recordsString.append("</RatingEntity>");
			
			
			
			try{
//				Reader stringReader = new StringReader(recordsString.toString());
//				JAXBContext jc = JAXBContext.newInstance(RatingEntity.class);
//				Unmarshaller um = jc.createUnmarshaller();
//				re = (RatingEntity) um.unmarshal(stringReader);
				re = JAXBUtil.getInstance().unmarshalObject(recordsString.toString());
			
			} catch(Exception _ex){
				logger.error("query is " + sb);
				logger.error("Error occured to get the Process Records " + _ex.toString());
			}
			
			//This was added becuase XMLDB returns xml string without ratingentity object in process
			for(Process process : re.getProcesses()){
				if(process.getEntity() == null){
					process.setEntity(this.ratingEntity);
				}
			}
		}
		
		
		return (List<T>)re.getProcesses();
		
	}

}
