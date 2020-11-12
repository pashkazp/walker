package depavlo.walker.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * The Class WalkerApplication.
 * 
 * @author Pavlo Degtyaryev
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan({ "depavlo" })
@EntityScan({ "depavlo" })
public class WalkerApplication extends SpringBootServletInitializer {

	/**
	 * Configure web application.
	 *
	 * @param application the application
	 * @return the spring application builder
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(WalkerApplication.class);
	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws Exception the exception
	 */
	public static void main(String[] args) throws Exception {
		SpringApplication.run(WalkerApplication.class, args);
	}

}
