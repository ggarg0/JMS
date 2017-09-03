package com.gaurav.servlet;

import java.io.IOException;
import java.util.Properties;

import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gaurav.sessionBean.SessionBeanRemote;


/**
 * Servlet implementation class EJBServlet
 */
public class EJBServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EJBServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
try {
			Properties props= new Properties();
			props.put(Context.PROVIDER_URL, "t3://localhost:7001");
			props.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
			
			System.out.println("Hi A");
			Context ctx= new InitialContext(props);
			SessionBeanRemote bean = (SessionBeanRemote)ctx.lookup("/BeanDemo#com.gaurav.sessionBean.SessionBeanRemote");
			response.getWriter().println("Hi " + bean.RemoteBeanCall("gaurav"));
			bean.RemoveSesssion();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Hi  e" +  e);
		} 
	}

}
