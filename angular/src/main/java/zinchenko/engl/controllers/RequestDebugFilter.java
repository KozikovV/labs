package zinchenko.engl.controllers;

import javax.servlet.*;
import java.io.IOException;

public class RequestDebugFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            Thread.sleep(4000L);
        } catch (InterruptedException e) {
            new RuntimeException("Fail debug filter.", e);
        }
    }

    @Override
    public void destroy() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
