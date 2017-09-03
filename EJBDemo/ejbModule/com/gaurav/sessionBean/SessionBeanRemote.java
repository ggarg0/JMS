package com.gaurav.sessionBean;

import javax.ejb.Remote;

@Remote
public interface SessionBeanRemote {
	public String RemoteBeanCall(String param);
	public void RemoveSesssion();
}
