package poe.spring.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import poe.spring.domain.Greeting;

@RestController
public class GreetingController {
	
	
	private static final Logger logger = LoggerFactory.getLogger(GreetingController.class);
	
	@RequestMapping("/hello")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		String logg = String.format("hello here");
		logger.debug(logg);
		return new Greeting(1, "hello " + name);
	}

	@RequestMapping("/hi/name/{name}/lastname/{lastname}")
	public Greeting hi(@PathVariable(value = "name") String name, @PathVariable(value = "lastname") String lastname) {
		return new Greeting(1, "hi " + name);
	}
	
	
}