package com.intangible.utils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 过滤器解决跨域
 */
public class CrosFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        String curOrigin = request.getHeader("Origin");
        // 设置响应头
        //该字段是必须的。它的值要么是请求时Origin字段的值，要么是一个*，表示接收任意域名的请求。
        response.setHeader("Access-Control-Allow-Origin", curOrigin);
        //该字段是必须的，用来列出浏览器的CORS请求会用到哪些HTTP方法
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT");
        //预检间隔时间
        response.setHeader("Access-Control-Max-Age", "3600");
        //该字段是一个逗号分隔的字符串，指定浏览器CORS请求会额外发送的头信息字段
        response.setHeader("Access-Control-Allow-Headers", "Content-Type,X-CAF-Authorization-Token,sessionToken,token,customercoderoute,authorization,conntectionid,Cookie,request-ajax");

        //Access-Control-Allow-Credentials：该字段可选。它的值是一个布尔值，表示是否允许发送Cookie。
        // 默认情况下，Cookie不包括在CORS请求之中，设为True，
        // 即表示服务器明确许可，Cookie可以包含在请求中，一起发送给服务器。
        // 这个值也只能设为True，如果服务器不要浏览器发送Cookie，删除即可
        //　Access-Control-Allow-Credentials为True的时候，Access-Control-Allow-Origin一定不能设置为“*”，否则报错
        response.setHeader("Access-Control-Allow-Credentials","true");

        // 浏览器默认会发起异常 OPTIONS 的请求方式 这个时候我们通过过滤器直接拦截返回200后就可以解决跨越问题
        if ("OPTIONS".equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            return;
        }

        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void destroy() {
    }
}
