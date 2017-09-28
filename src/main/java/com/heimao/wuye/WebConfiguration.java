package com.heimao.wuye;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.filters.RemoteIpFilter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;
/**
 * 
 * @author wuyeheimao
 *
 */
@Configuration
public class WebConfiguration {
	@Bean
	public RemoteIpFilter remoteIpFilter() {
		return new RemoteIpFilter();
	}

	@Bean
	public FilterRegistrationBean testFilterRegistration() {

		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(new CharacterFilter());
		registration.addUrlPatterns("/*");
		registration.addInitParameter("paramName", "paramValue");
		registration.setName("MyFilter");
		registration.setOrder(1);
		return registration;
	}

	class CharacterFilter extends OncePerRequestFilter {

	    @Override
	    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

	        filterChain.doFilter(new HttpServletRequestWrapper(request) {
	            @Override
	            public String getParameter(String name) {
	                // 返回值之前 先进行过滤
	                return filterBlankString(super.getParameter(name));
	            }

	            @Override
	            public String[] getParameterValues(String name) {
	                // 返回值之前 先进行过滤
	                String[] values = super.getParameterValues(name);
	                if (values != null) {
	                    for (int i = 0; i < values.length; i++) {
	                        values[i] = filterBlankString(values[i]);
	                    }
	                }
	                return values;
	            }
	        }, response);
	    }

	    private String filterBlankString(String value) {
	        if (StringUtils.isBlank(value)) {
	            return null;
	        }
	        return value.trim();
	    }

	}
}
