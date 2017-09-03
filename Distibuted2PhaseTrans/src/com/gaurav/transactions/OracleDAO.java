package com.gaurav.transactions;

import oracle.jdbc.xa.OracleXADataSource;

public class OracleDAO {
	 OracleXADataSource oxds1;
	public OracleXADataSource getDBConnection() {
		try {
			
		        oxds1.setURL("jdbc:oracle:thin:@//Gaurav-VAIO:1521/XE");
		        oxds1.setUser("scott");
		        oxds1.setPassword("tiger");
		        return oxds1;

		} catch (Exception e) {

			System.out.println("Exception in getDBConnection: " + e);
			e.printStackTrace();
			return null;
		}
	}
	
}
