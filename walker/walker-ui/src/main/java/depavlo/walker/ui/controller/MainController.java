package depavlo.walker.ui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * The MainController class handles the Home Page call
 * 
 * @author Pavlo Degtyaryev
 */
@Controller
@RequestMapping("/")
public class MainController {

	/**
	 * Return the Home Page template
	 *
	 * @return the string
	 */
	@GetMapping()
	public String getHomePage(Model model) {
		return "home/main-page";
	}

	/**
	 * Return the About Page template
	 *
	 * @return the string
	 */
	@GetMapping("/about")
	public String getAboutPage(Model model) {
		return "home/about-page";
	}

	/**
	 * Return the FAQ Page template
	 *
	 * @return the string
	 */
	@GetMapping("/faq")
	public String getFaqPage(Model model) {
		return "home/faq-page";
	}

}
