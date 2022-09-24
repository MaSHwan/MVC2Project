package com.mvcmem.model;

import java.sql.*;
import javax.sql.DataSource;
import javax.naming.*;


public class ConnUtil {

	private static DataSource ds;
	
	static {
		
		
		
		try {
			 InitialContext ctx = new InitialContext();								 
			 ds = (DataSource)ctx.lookup("java:/comp/env/jdbc/myoracle"); 
			
		}catch(NamingException ne) {
			ne.printStackTrace();
		}
	}


	public static Connection getConnection() throws SQLException {
		return ds.getConnection();
	}




}