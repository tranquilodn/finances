package uk.org.tdn.finances.util.filters;

import java.io.IOException;

import javax.faces.application.ViewExpiredException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uk.org.tdn.finances.entity.UserEntity;
import uk.org.tdn.finances.util.jsf.JsfUtils;

@WebFilter(urlPatterns = { "/pages/*", "/m/*" })
public class LoginFilter implements Filter {

	public LoginFilter() {
	}

	@Override
	public void init(FilterConfig fConfig) throws ServletException {
	}

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
		boolean isLogin = false;
		boolean isMLogin = false;
		HttpSession httpSession = null;
		HttpServletRequest httpServletRequest = null;
		HttpServletResponse httpServletResponse = null;
		UserEntity userLoggedIn = null;
		try {
			httpSession = ((HttpServletRequest) request).getSession();
			httpServletRequest = (HttpServletRequest) request;
			httpServletResponse = (HttpServletResponse) response;
			isLogin = !(httpServletRequest.getRequestURI().indexOf("login.faces") <= -1);
			isMLogin = !(httpServletRequest.getRequestURI().indexOf("mlogin.faces") <= -1);
			userLoggedIn = (UserEntity) httpSession.getAttribute(JsfUtils.SESSION_USER_LOGGED_IN);
			if ((isLogin || isMLogin) || userLoggedIn == null) {
				if (isLogin) {
					httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/login.faces");
				} else if (isMLogin) {
					httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/mlogin.faces");
				} else {
					httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/index.faces");
				}
			} else {
				chain.doFilter(request, response);
			}
		} catch (IOException ioe) {
			Throwable t = ioe.getCause();
			t.printStackTrace();
		} catch (ServletException se) {
			Throwable t = se.getCause();
			t.printStackTrace();
		} catch (ViewExpiredException vee) {
			try {
				if (isLogin) {
					httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/login.faces");
				} else if (isMLogin) {
					httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/mlogin.faces");
				} else {
					httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/index.faces");
				}
			} catch (IOException ioe) {
				Throwable t = ioe.getCause();
				t.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}