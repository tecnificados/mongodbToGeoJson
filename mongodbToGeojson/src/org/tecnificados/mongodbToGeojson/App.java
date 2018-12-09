package org.tecnificados.mongodbToGeojson;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class App 
{
	final static Logger log = Logger.getLogger(App.class);
	
    public static void main( String[] args )
    {
    	BasicConfigurator.configure();
        log.info( "Tecnificados 2018" );
    }
}
