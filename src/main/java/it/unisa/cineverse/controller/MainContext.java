package it.unisa.cineverse.controller;


import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.naming.Context;



import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class MainContext implements ServletContextListener
{
	public void contextInitialized(ServletContextEvent sce)
	{
		ServletContext context = sce.getServletContext();
		DataSource ds = null;
		try
		{
			Context initCtx = new InitialContext();
			Context envCts = (Context) initCtx.lookup("java:comp/env");
			ds = (DataSource) envCts.lookup("jdbc/storage");
			
		}catch(NamingException e)
		{
			System.out.println("Error :" + e.getMessage());
		}
		
		context.setAttribute("DataSource", ds);
	}
	
	public void contextDestroyed(ServletContextEvent sce) {}
}
