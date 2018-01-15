package com.hys.exam.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

public class NcmeProperties extends PropertyPlaceholderConfigurer {
	private static Map<String, Object> ctxPropertiesMap; 
	 
    @Override 
    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props) { 
        super.processProperties(beanFactoryToProcess, props); 
        ctxPropertiesMap = new HashMap<String, Object>(); 
        for (Object key : props.keySet()) { 
            String keyStr = key.toString(); 
            String value = props.getProperty(keyStr); 
            ctxPropertiesMap.put(keyStr, value); 
        } 
    } 
 
    public static Object getContextProperty(String name) { 
        return ctxPropertiesMap.get(name); 
    } 
    
    public static String getContextPropertyValue(String name) { 
    	String resTmp = null ;
    	Object valueObj = ctxPropertiesMap.get(name) ;
    	if (valueObj != null) resTmp = (String) valueObj ;
        return resTmp ; 
    }

	public static Map<String, Object> getCtxPropertiesMap() {
		return ctxPropertiesMap;
	}

	public static void setCtxPropertiesMap(Map<String, Object> ctxPropertiesMap) {
		NcmeProperties.ctxPropertiesMap = ctxPropertiesMap;
	}
    
    
	
}
