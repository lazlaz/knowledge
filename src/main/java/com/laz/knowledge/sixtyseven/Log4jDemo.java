package com.laz.knowledge.sixtyseven;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Log4jDemo {
	private static Logger logger = Logger.getLogger(Log4jDemo.class);  
	 
    /** 
     * @param args 
     * @throws URISyntaxException 
     * @throws FileNotFoundException 
     */  
    public static void main(String[] args) throws FileNotFoundException, URISyntaxException {  
        // System.out.println("This is println message.");  
    	URL url = Log4jDemo.class.getResource("./log4j.properties");
    	PropertyConfigurator.configure(new FileInputStream(new File(url.toURI())));
        // 记录debug级别的信息  
        logger.debug("This is debug message.");  
        // 记录info级别的信息  
        logger.info("This is info message.");  
        // 记录error级别的信息  
        logger.error("This is error message.");  
    }  

}
