package poe.spring.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import poe.spring.delegate.LoginCreationDelegate;
import poe.spring.domain.User;
import poe.spring.exception.DuplicateLoginBusinessException;
import poe.spring.form.LoginForm;
import poe.spring.service.UserManagerService;

@Controller
@RequestMapping("/signup")
public class SignUpController {
	
	private static Logger log = LoggerFactory.getLogger(SignUpController.class);

	private static final String SIGNUP = "signup";

	@Autowired
	UserManagerService userManagerService;

	@GetMapping
	public String index(LoginForm form) {
		return SIGNUP; // affiche signup.html
	}

	@PostMapping
	public String save(@Valid LoginForm form, BindingResult bindingResult, RedirectAttributes attr, Model model) {
		log.info("ajout d'un utilisateur login: {0}", form.getLogin());
		System.out.println("login " + form.getLogin());
		System.out.println("password " + form.getPassword());
		if (bindingResult.hasErrors()) {
			System.out.println("aqwzsx");
			return SIGNUP;
		}
		try {
			userManagerService.signup(form.getLogin(), form.getPassword());
		} catch (DuplicateLoginBusinessException e) {
			model.addAttribute("error", "Ce login est déjà utilisé!");
			return SIGNUP;
		}
		attr.addAttribute("login", form.getLogin());
		return "redirect:/signup/success";
		// userManagerService.signup(form.getLogin(), form.getPassword());
		// attr.addAttribute("login", form.getLogin());
		// return "redirect:/signup/success";
	}

	@GetMapping("/success")
	public String success(@RequestParam("login") String login, Model model) {
		model.addAttribute("login", login);
		return "success"; // success.html
	}
}