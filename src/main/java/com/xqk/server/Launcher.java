package com.xqk.server;

import org.eclipse.jetty.annotations.AnnotationConfiguration;
import org.eclipse.jetty.plus.webapp.EnvConfiguration;
import org.eclipse.jetty.plus.webapp.PlusConfiguration;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.Configuration;
import org.eclipse.jetty.webapp.FragmentConfiguration;
import org.eclipse.jetty.webapp.JettyWebXmlConfiguration;
import org.eclipse.jetty.webapp.WebAppContext;


public class Launcher {
	private static final int HTTPPORT = 8080;
	private static Server server;
	private static final String CONTEXT = "/LinkableVote/";

	
	public static void main(String[] args) throws Exception {
		//BasicConfigurator.configure();
		server = new Server(HTTPPORT);
		
		WebAppContext wac = new WebAppContext();
		wac.setContextPath(CONTEXT);
		wac.setServer(server);
		wac.setExtractWAR(true);
		wac.setWar("src/main/webapp");
		
		
		Configuration.ClassList classlist = Configuration.ClassList.setServerDefault(server);
		classlist.addAfter(FragmentConfiguration.class.getName(), EnvConfiguration.class.getName(), PlusConfiguration.class.getName());
		classlist.addBefore(JettyWebXmlConfiguration.XML_CONFIGURATION, AnnotationConfiguration.class.getName());
		
		server.setHandler(wac);
		server.start();
	}
}
