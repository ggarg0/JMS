package com.gaurav.TwoPhase;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.XAConnection;
import javax.transaction.xa.XAException;
import javax.transaction.xa.XAResource;
import javax.transaction.xa.Xid;
import oracle.jdbc.xa.OracleXid;
import oracle.jdbc.xa.OracleXAException;
import oracle.jdbc.xa.OracleXAConnection;
import oracle.jdbc.xa.OracleXADataSource;

import com.gaurav.transactions.MyXid;
import com.gaurav.transactions.OracleDAO;

public class CmtOneTranBranch {
	OracleXADataSource xaDS;
	XAConnection xaCon;
	XAResource xaRes;
	Xid xid;
	Connection con;
	Statement stmt;
	int ret;

	public void CloseAll() throws SQLException {
		stmt.close();
		con.close();
		xaCon.close();
	}

	public static void main(String[] args) {

		try {
			CmtOneTranBranch obj = new CmtOneTranBranch();
			obj.commitOneTransactionBranch();
			obj.CloseAll();
		} catch (SQLException | XAException e) {
			System.out.println("Exp : " + e);
		}

	}

	public void commitOneTransactionBranch() throws SQLException, XAException {
		//xaDS.setDriverType("oracle.jdbc.driver.OracleDriver");
		xaDS.setURL("jdbc:oracle:thin:@//Gaurav-VAIO:1521/XE");
		xaDS.setUser("scott");
		xaDS.setPassword("tiger");
		xaCon = xaDS.getXAConnection();

		xaRes = xaCon.getXAResource();
		con = xaCon.getConnection();
		stmt = con.createStatement();

		xid = new MyXid(100, new byte[] { 0x01 }, new byte[] { 0x02 });

		xaRes.start(xid, XAResource.TMNOFLAGS);
		stmt.executeUpdate("insert into student_details values (50121,'Gary Kristen',32,'gary.kristen@gmail.com',1004)");
		xaRes.end(xid, XAResource.TMSUCCESS);

		ret = xaRes.prepare(xid);
		if (ret == XAResource.XA_OK) {
			System.out.println("Response : " + (ret == XAResource.XA_OK));
			xaRes.commit(xid, false);
		}

	}
}