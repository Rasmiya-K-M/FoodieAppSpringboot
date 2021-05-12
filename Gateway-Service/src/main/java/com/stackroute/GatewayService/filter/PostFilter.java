package com.stackroute.GatewayService.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;

public class PostFilter extends ZuulFilter {
    private static Logger logger= LoggerFactory.getLogger(PostFilter.class);
    @Override
    public String filterType() {
        return "postFilter";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        HttpServletResponse response= RequestContext.getCurrentContext().getResponse();
        logger.info("postfilter"+response.getStatus());
        return null;
    }
}
