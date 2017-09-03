package com.gaurav.transactions;

import javax.transaction.xa.*;

public class MyXid implements Xid {

	protected int formatId;
	protected byte gtrid[];
	protected byte bqual[];

	MyXid() {
	}

	public MyXid(int formatId, byte gtrid[], byte bqual[]) {
		this.formatId = formatId;
		this.gtrid = gtrid;
		this.bqual = bqual;
	}

	@Override
	public byte[] getBranchQualifier() {
		// TODO Auto-generated method stub
		return bqual;
	}

	@Override
	public int getFormatId() {
		return formatId;

	}

	@Override
	public byte[] getGlobalTransactionId() {
		// TODO Auto-generated method stub
		return gtrid;
	}
}
