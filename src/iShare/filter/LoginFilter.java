package iShare.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import iShare.model.User;
import iShare.service.UserService;

@WebFilter("/*")
public class LoginFilter implements Filter {


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpReq = (HttpServletRequest) request;
		if(httpReq.getUserPrincipal()!=null && httpReq.getAttribute("user")==null) {
			saveUserInSession(httpReq);
		}
		chain.doFilter(request, response);
	}

	private void saveUserInSession(HttpServletRequest httpReq) {
		UserService userService = new UserService();
		String userName = httpReq.getUserPrincipal().getName();
		User user = userService.getUserByUsername(userName);
		httpReq.getSession(true).setAttribute("user", user);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}
	public void destroy() {
	}

}
