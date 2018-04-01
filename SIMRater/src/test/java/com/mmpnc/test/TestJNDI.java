package com.mmpnc.test;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.junit.Test;

/**
 * Author : Shashikant Mhatre
 *
 * Created On : Jan 10, 2014
 *
 * Description :
 *
 * Note :
 */

public class TestJNDI {

	@Test
	public void test() {

		try {
			Context initCtx = new InitialContext();
			Context jndiContext = (Context) initCtx.lookup("java:comp/env");
			
			jndiContext.bind("rootNode", "Policy");
			jndiContext.bind("ProductIdNode", "RatebookId");
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		try {
		    Context ctx = new InitialContext();
		    String rootNode = (String) ctx.lookup("rootNode");
		    System.out.println(rootNode);
		} catch (NamingException e) {
		    // Handle the error
		    System.err.println(e);
		}
		
		
	
	}

}
