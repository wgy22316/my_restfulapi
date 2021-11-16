package com.my.restfulapi.common.framework.filter;


import com.my.restfulapi.common.util.trace.TraceLogUtil;

import javax.servlet.*;
import java.io.IOException;

import static com.my.restfulapi.common.util.trace.TraceLogUtil.generateDefaultTraceLogIdPrefix;
import static com.my.restfulapi.common.util.trace.TraceLogUtil.generateRandomSed;

public class TraceFilter implements Filter {
    @Override
    public void init(javax.servlet.FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String traceId = generateRandomSed(generateDefaultTraceLogIdPrefix());
        TraceLogUtil.put(traceId);
        filterChain.doFilter(servletRequest, servletResponse);
        TraceLogUtil.remove();
    }

    @Override
    public void destroy() {
    }
}
