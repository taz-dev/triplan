package be.triplan.security.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class MyFilter implements Filter {
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest servletRequest = (HttpServletRequest) request;
        HttpServletResponse servletResponse = (HttpServletResponse) response;

        //토큰 : cos
        if (servletRequest.getMethod().equals("POST")) {
            String headerAuth = servletRequest.getHeader("Authorization");

            if (headerAuth.equals("cos")) {
                chain.doFilter(servletRequest, servletResponse);
            } else {
                PrintWriter out = servletResponse.getWriter();
                out.println("인증 안됨");
            }
        }
    }
}
