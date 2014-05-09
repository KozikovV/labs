package zinchenko.engl.controllers;

import javax.servlet.*;
import java.io.IOException;

public class RequestDebugFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("Debug filter");
        try {
            Thread.sleep(500L);
        } catch (InterruptedException e) {
            new RuntimeException("Fail debug filter.", e);
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
