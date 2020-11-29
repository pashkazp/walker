package depavlo.walker.ui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * The MainController class handles the Home Page call
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
	 * Return the Home Page template
	 *
	 * @return the string
	 */
	@GetMapping("/about")
	public String getAboutPage(Model model) {
		return "home/about-page";
	}

	/**
	 * Return the Home Page template
	 *
	 * @return the string
	 */
	@GetMapping("/faq")
	public String getFaqPage(Model model) {
		return "home/faq-page";
	}

}
