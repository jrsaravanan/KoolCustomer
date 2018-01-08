package com.nathan.customer.api.filter;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.util.ZuulRuntimeException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.nathan.customer.api.client.AuthClient;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class EdgeServiceFilter  extends ZuulFilter {

	private static final String X_AUTH_TOKEN = "X-Auth-Token";
	private static final Logger LOGGER = LoggerFactory.getLogger(EdgeServiceFilter.class);

	@Autowired
	private AuthClient authClient;
	
	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 2;
	}

	@Override
	public Object run() {

		RequestContext ctx = RequestContext.getCurrentContext();
	    HttpServletRequest request = ctx.getRequest();
	    LOGGER.info(String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));
	    
	    String requestURL = request.getRequestURL().toString();
	    String authenticationToken = request.getHeader(X_AUTH_TOKEN);
	    
	    if (StringUtils.isEmpty(authenticationToken) && ! requestURL.endsWith("/login")) {
	    	 throw new ZuulRuntimeException(new ZuulException("Unauthorized Access or Invalid Session", HttpStatus.UNAUTHORIZED.value(), "Login required"));
	    } else  if (! StringUtils.isEmpty(authenticationToken)) {
	    	if (!authClient.validateToken(authenticationToken)) {
	    		throw new ZuulRuntimeException(new ZuulException("Unauthorized Access or Invalid Session", HttpStatus.UNAUTHORIZED.value(), "Login required"));
	    	}
	    }
	    
	     
		return null;
	}
}
