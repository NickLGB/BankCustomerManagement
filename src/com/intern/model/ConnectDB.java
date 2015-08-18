package com.intern.model;

import java.sql.*;

public class ConnectDB {
private Connection ct=null;
	
	public Connection getConn(){
		
		try{
			
			String url  = "jdbc:sqlserver://localhost:1433; databaseName=spdb1;";
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			ct = DriverManager.getConnection(url,"sa","Xiaoxia8");
			
		}catch (Exception e){
			e.printStackTrace();
		}
		
		return ct;
	}
}
