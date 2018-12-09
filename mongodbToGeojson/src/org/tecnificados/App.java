package org.tecnificados;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class App 
{
	final static Logger log = Logger.getLogger(App.class);
	
    public static void main( String[] args )
    {
    	BasicConfigurator.configure();
    	
    	Properties prop = new Properties();
    	log.info("Reading configuration in file: conf.properties");	 
    	try {
			prop.load(new FileInputStream("conf.properties"));
    	} catch (Exception e) {
			log.error("Error reading configuration",e);
			return;
		}
    	
    	log.info(prop.get("server"));
        log.info( "Connecting..." );
        
        MongoDBClient ll = new MongoDBClient();
		ll.setDatabase((String) prop.get("database"));
		ll.setUsuario((String) prop.get("user"));
		ll.setPassword((String) prop.get("password"));
		ll.setServer((String) prop.get("server"));
		
		
		ll.initClient();	
		
	
		
		ll.extractCollection((String) prop.get("collection"));
		
		
		
        log.info("End");
    }
}
