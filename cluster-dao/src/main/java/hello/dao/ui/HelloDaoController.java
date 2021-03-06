package hello.dao.ui;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import hello.dao.db.HelloDao;

@RestController
public class HelloDaoController {

	private static final Logger logger = LogManager.getLogger(HelloDaoController.class);

	private final HelloDao helloDao;

	public HelloDaoController(HelloDao helloDao) {
		this.helloDao = helloDao;
	}

	@GetMapping("/hello/{name}")
	public String welcome(@PathVariable("name") String name) {
		logger.info("hello called with: {}", name);

		return helloDao.welcome(name);
	}
}
