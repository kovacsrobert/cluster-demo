package hello.util;

import static hello.util.EnvironmentVariables.INSTANCE_NAME;

import org.apache.logging.log4j.ThreadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoggingUtil {

	private final InstanceConfiguration instanceConfiguration;

	@Autowired
	public LoggingUtil(InstanceConfiguration instanceConfiguration) {
		this.instanceConfiguration = instanceConfiguration;
	}

	public void fillLoggingContext() {
		ThreadContext.put(INSTANCE_NAME, instanceConfiguration.getInstanceName());
	}

	public void removeLoggingContext() {
		ThreadContext.remove(INSTANCE_NAME);
	}
}
