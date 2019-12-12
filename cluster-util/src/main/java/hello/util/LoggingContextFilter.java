package hello.util;

import static hello.util.EnvironmentVariables.INSTANCE_NAME;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.ThreadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class LoggingContextFilter extends OncePerRequestFilter {

	private final InstanceConfiguration instanceConfiguration;

	@Autowired
	public LoggingContextFilter(InstanceConfiguration instanceConfiguration) {
		this.instanceConfiguration = instanceConfiguration;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		ThreadContext.put(INSTANCE_NAME, instanceConfiguration.getInstanceName());
		filterChain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		ThreadContext.remove(INSTANCE_NAME);
	}
}
