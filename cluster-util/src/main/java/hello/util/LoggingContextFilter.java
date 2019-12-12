package hello.util;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class LoggingContextFilter extends OncePerRequestFilter {

	private final LoggingUtil loggingUtil;

	@Autowired
	public LoggingContextFilter(LoggingUtil loggingUtil) {
		this.loggingUtil = loggingUtil;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		loggingUtil.fillLoggingContext();
		filterChain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		loggingUtil.removeLoggingContext();
	}
}
