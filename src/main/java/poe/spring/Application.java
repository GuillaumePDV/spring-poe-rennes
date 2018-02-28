package poe.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class Application {
	
	private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		log.info("Démarrage de l'application");
		log.trace("trace log");
		log.debug("debug log");
		log.info("info log");
		log.warn("warn log");
		log.error("error log");
		
		SpringApplication.run(Application.class, args);

	}

}