package com.gaurav.sessionBean;

import javax.ejb.Local;

@Local
public interface SessionBeanLocal {

	public String LocalBeanCall(String param);
}
