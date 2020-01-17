package com.tensquare.web.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class WebFilter extends ZuulFilter {

    // 过滤器的类型
    @Override
    public String filterType() {
        return "pre";
    }

    // 定义执行的顺序,返回值越小,优先级越高
    @Override
    public int filterOrder() {
        return 0;
    }

    // 开启过滤器
    @Override
    public boolean shouldFilter() {
        return true;
    }
    // 过滤器的具体的逻辑 默认的放行 拦截的话需要编写方法
    @Override
    public Object run() throws ZuulException {

        // 将token转发过去
        RequestContext requestContext =
                RequestContext.getCurrentContext();
        // 获取header
        HttpServletRequest request = requestContext.getRequest();
        // 获取到请求头的值
        String authorization = request.getHeader("Authorization");
        if(authorization!=null){
            requestContext.addZuulRequestHeader("Authorization",authorization);
        }
        return null;
    }
}
