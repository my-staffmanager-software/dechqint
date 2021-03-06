package launch;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class HttpsEnforcer implements Filter {
  public static String X_FORWARDED_PROTO = "X-Forwarded-Proto";

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {}

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
    HttpServletRequest request = (HttpServletRequest) servletRequest;
    HttpServletResponse response = (HttpServletResponse) servletResponse;

    if (request.getHeader(X_FORWARDED_PROTO) != null) {
      if (request.getHeader(X_FORWARDED_PROTO).indexOf("https") != 0) {
        String pathInfo = (request.getPathInfo() != null) ? request.getPathInfo() : "";
        response.sendRedirect("https://" + request.getServerName() + pathInfo);
        return;
      }
    }

    filterChain.doFilter(request, response);
  }

  @Override
  public void destroy() { }
}
