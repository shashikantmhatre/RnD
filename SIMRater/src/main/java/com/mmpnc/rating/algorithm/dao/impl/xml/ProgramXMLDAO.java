package com.mmpnc.rating.algorithm.dao.impl.xml;

import java.io.Reader;
import java.io.StringReader;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mmpnc.rating.dao.impl.xml.XMLDAO;
import com.mmpnc.rating.dao.util.JAXBUtil;
import com.mmpnc.rating.vo.Process;
import com.mmpnc.rating.vo.Program;

import com.mmpnc.connection.helper.Session;

/**
 * Author : Shashikant Mhatre
 *
 * Created On : Oct 12, 2013
 *
 * Description :
 *
 * Note :
 */

public class ProgramXMLDAO extends XMLDAO {
	private Program program;
	private Process process;
	
	private static final Logger logger = LoggerFactory
			.getLogger(ProgramXMLDAO.class);

	public ProgramXMLDAO(Program program) {
		this.program = program;
		this.process = this.program.getProcess();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T,S> T getUniqueRecord(S session) {
		boolean condition = false;
		
		StringBuffer sb = new StringBuffer("for $x in /Product/RatingEntity/RatingProcess ");
		
		sb.append("let $y := $x/RatingProgram ");
		
		if (this.program.getName() != null) {
//			sb = updateQuery(sb, condition, program, "name");
//			sb.append("$x/Name").append("='").append(this.program.getName()).append("'");
			sb.append("[ Name").append("='") .append(this.program.getName()).append("' ] ");
		}
		

		if (this.program.getProcess() != null) {
			sb.append("where ");
			
			if( this.program.getProcess().getId() != null && !this.program.getProcess().getId().equals("")) {
//				sb = updateQuery(sb, condition, program, "process.id");
				sb.append("$x/Id").append("='").append(this.program.getProcess().getId()).append("' ");
				condition = true;
			}
			if(! this.program.getProcess().getName().equals("")){
				if(condition){
					sb.append(" and ");
				}
//				sb = updateQuery(sb, condition, program, "process.name");
				sb.append("$x/Name").append("='").append(this.program.getProcess().getName()).append("' ");
				condition = true;
			}
		}
			
		sb.append("return <RatingProgram>{$y/*[local-name()=('Id','Name','ExecutionOrder')]}</RatingProgram>");

		if (logger.isInfoEnabled()) {
			logger.info("Program Query -> " + sb);
		}

//		System.out.println("Program Query " + sb);
		
		String outputString = (String) ((Session)session).createQuery(sb.toString()).execute();
		
		if(outputString != null && !outputString.equals(""))
		{
			
			try{
//				Reader stringReader = new StringReader(outputString);
//				JAXBContext jc = JAXBContext.newInstance(Program.class);
//				Unmarshaller um = jc.createUnmarshaller();
//				this.program = (Program) um.unmarshal(stringReader);
				this.program = JAXBUtil.getInstance().unmarshalObject(outputString);
			} catch(Exception _ex){
				logger.error("query is " + sb);
				logger.error("Error occured to get the Program " + _ex.toString());
			}
			
	//		this.program = (Program)session.load(Program.class, program);
			
			//This was added because XMLDB returns the XML string withouth Process object in Program object
			if(this.program.getId() != null){
				if(this.program.getProcess() == null){
					this.program.setProcess(this.process);
				} 
			} else {
				//This is to ensure that we will return null value if xQuery return empty RatingProgram String <RatingProgram/>
				this.program = null;
			}
		}
		else {
			this.program = null;
		}
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
