package com.nathan.customer.api.filter;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

@Component
public class AuthenticationServiceFilter  extends ZuulFilter {

	private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationServiceFilter.class);
	
	@Override
	public boolean shouldFilter() {
		return false;
	}

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 1;
	}

	@Override
	public Object run() {

		RequestContext ctx = RequestContext.getCurrentContext();
	    HttpServletRequest request = ctx.getRequest();
	    LOGGER.info(String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));
		return null;
	}
}
