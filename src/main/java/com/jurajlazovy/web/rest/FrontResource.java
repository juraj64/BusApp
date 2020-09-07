// Upravené tak, aby som do aplikácie vstupoval cez http://localhost:8888/rest/login
// a predvolené heslo v LoginService (123)

package com.jurajlazovy.web.rest;

import com.jurajlazovy.bus.serviceimpl.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Implementation of FrontResource.
 */
@Controller
public class FrontResource extends FrontResourceBase {

	public FrontResource() {
	}

	@Autowired
	private LoginService loginService;

	// GET pre login.
	// GET pre front (Home) je front() vo FrontResourceBase
	// adresa pre Home je vo footer.jsp, na ktorý sa odvolava overview.jsp
	@RequestMapping(value = "/login", method = RequestMethod.GET) // oznacuje url http://localhost:8888/rest/login
	public String login() {
		return "front/login";  // oznacuje nazov a cestu k jsp suboru
	}

	// POST metoda pre url http://localhost:8888/rest/front
	@RequestMapping(value = "/front", method = RequestMethod.POST)
	public String frontLogin(ModelMap model,@RequestParam String password) {

		if(!loginService.validatePassword(password)) { // ak false
			model.put("errorMessage", "Wrong password!"); // do modelu namapujeme chybove hlasenie
			return "front/login"; // vrat spat do login.jsp
		}
		//ak nie false (teda true)
		model.put("password", password); // password do modelu
		return "front/overview"; // vrat overview.jsp
	}

}
