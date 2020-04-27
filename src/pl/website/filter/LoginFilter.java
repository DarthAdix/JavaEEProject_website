package pl.website.filter;

import pl.website.model.User;
import pl.website.service.UserService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter("/*")
public class LoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) req;
        if (httpServletRequest.getUserPrincipal() != null && httpServletRequest.getSession().getAttribute("user") == null) {
            saveUserInSession(httpServletRequest);
        }
        chain.doFilter(req, resp);
    }

    private void saveUserInSession(HttpServletRequest httpServletRequest) {
        UserService userService = new UserService();
        String username = httpServletRequest.getUserPrincipal().getName();
        User userByUsername = userService.getUserByUsername(username);
        httpServletRequest.getSession(true).setAttribute("user", userByUsername);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
