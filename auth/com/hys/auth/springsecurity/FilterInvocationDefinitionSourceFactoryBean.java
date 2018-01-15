package com.hys.auth.springsecurity;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.security.ConfigAttributeDefinition;
import org.springframework.security.ConfigAttributeEditor;
import org.springframework.security.intercept.web.DefaultFilterInvocationDefinitionSource;
import org.springframework.security.intercept.web.FilterInvocationDefinitionSource;
import org.springframework.security.intercept.web.RequestKey;
import org.springframework.security.util.AntUrlPathMatcher;
import org.springframework.security.util.UrlMatcher;

import com.hys.auth.dto.ResourceDTO;
import com.hys.auth.sessionfacade.AuthFacade;

/**
 * 定义过滤器资源
 * 
 * @author zdz
 * 
 */
public class FilterInvocationDefinitionSourceFactoryBean implements FactoryBean {

	private AuthFacade facade;

	public void setFacade(AuthFacade facade) {
		this.facade = facade;
	}

	public Object getObject() throws Exception {
		return new DefaultFilterInvocationDefinitionSource(this.getUrlMatcher(), this.buildRequestMap());
	}

	@SuppressWarnings("unchecked")
	public Class getObjectType() {
		return FilterInvocationDefinitionSource.class;
	}

	public boolean isSingleton() {
		return true;
	}

	protected Map<String, String> findResources() {

		Map<String, String> resourceMap = new LinkedHashMap<String, String>();

		for (ResourceDTO resource : (List<ResourceDTO>) facade.findResources()) {
			String url = resource.getUrl();
			String role = resource.getRole();
			if (resourceMap.containsKey(url)) {
				String value = resourceMap.get(url);
				resourceMap.put(url, value + "," + role);
			} else {
				resourceMap.put(url, role);
			}
		}

		return resourceMap;
	}

	protected LinkedHashMap<RequestKey, ConfigAttributeDefinition> buildRequestMap() {
		LinkedHashMap<RequestKey, ConfigAttributeDefinition> requestMap = null;
		requestMap = new LinkedHashMap<RequestKey, ConfigAttributeDefinition>();

		ConfigAttributeEditor editor = new ConfigAttributeEditor();

		Map<String, String> resourceMap = this.findResources();

		for (Map.Entry<String, String> entry : resourceMap.entrySet()) {
			RequestKey key = new RequestKey(entry.getKey(), null);
			editor.setAsText(entry.getValue());
			requestMap.put(key, (ConfigAttributeDefinition) editor.getValue());
		}

		return requestMap;
	}

	protected UrlMatcher getUrlMatcher() {
		return new AntUrlPathMatcher();
	}
}
