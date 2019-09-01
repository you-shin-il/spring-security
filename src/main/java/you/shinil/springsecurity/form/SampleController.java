package you.shinil.springsecurity.form;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
public class SampleController {
	@RequestMapping("/info")
	public String info(Model model) {
		model.addAttribute("message", "info");
		return "info";
	}

	@RequestMapping("/admin")
	public String admin(Model model, Principal principal) {
		model.addAttribute("message", "admin : " + principal.getName());
		return "admin";
	}

	@RequestMapping("/dashboard")
	public String dashboard(Model model, Principal principal) {
		model.addAttribute("message", "dashboard : " + principal.getName());
		return "dashboard";
	}

	@RequestMapping("/")
	public String index(Model model, Principal principal) {
		if(principal == null) {
			model.addAttribute("message", "Hello Security");
		}else {
			model.addAttribute("message", "Hello :" + principal.getName());
		}
		return "index";
	}

}