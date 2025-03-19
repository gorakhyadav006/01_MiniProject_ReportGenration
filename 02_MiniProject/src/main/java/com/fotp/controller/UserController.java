package com.fotp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fotp.binding.LoginForm;
import com.fotp.binding.SignUpForm;
import com.fotp.binding.UnlockForm;
import com.fotp.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/signup")
	public String signUpPage(Model model) {
		model.addAttribute("user", new SignUpForm());
		return "signup";
	}

	@PostMapping("/signup")
	public String handleSingUp(@ModelAttribute("user") SignUpForm form, Model model) {
		boolean status = userService.signUp(form);

		if (status) {
			model.addAttribute("succMsg", "Your Account has been Created.....Check Your Email");
		} else {
			model.addAttribute("errMsg", "Choose another email id..... ");
		}
		return "signup";
	}

	@PostMapping("/unlock")
	public String unlockUserAccount(@ModelAttribute("unlock") UnlockForm unlokForm, Model model) {
		if (unlokForm.getCurrentPwd().equals(unlokForm.getConfirmPwd())) {
			boolean unlockAccount = userService.unlockAccount(unlokForm);
			if (unlockAccount) {
				model.addAttribute("accUnlockMsg", "You Account has been unlock please login ");
			} else {
				model.addAttribute("accerrorMsg", "Please Enter correct Temp Password ");
			}

		} else {
			model.addAttribute("pwdsameMsg", "Please enter same pwd for current pwd and confirm pwd.. ");
		}
		    return "unlock";
	}

	@GetMapping("/unlock")
	public String unlockPage(@RequestParam String email, Model model) {
		UnlockForm unlockFromObj = new UnlockForm();
		unlockFromObj.setEmail(email);
		// model.addAttribute("email",email);
		model.addAttribute("unlock", unlockFromObj);
		return "unlock";
	}

	@GetMapping("/login")
	public String loginPage(Model model) {
		model.addAttribute("loginForm", new LoginForm());
		return "login";
	}

	@PostMapping("/login")
	public String login(@ModelAttribute("loginForm") LoginForm form, Model model) {
		String status = userService.loginFormUser(form);
		if (status=="success") {
			System.out.println("Dashboard method is called");
			return "redirect:/dashboard";
		} else if (status=="invalid email&pwd") {
			model.addAttribute("faildlogin", "Please Enter Correct email id and password ");
			return "login";
		}else {
			model.addAttribute("lockedAcc", "Please unlock your Account First and Login again ");
			return "login";
		}
	}

	@GetMapping("/forgetPwd")
	public String forgotPwdPage(@RequestParam String email, Model model) {
		boolean forgotPwd = userService.forgotPwd(email);
		if(forgotPwd) {
			model.addAttribute("passrest", "Your password has been reset Check your email");
		  return "forgotPwd";
		}else {
			model.addAttribute("correctemail","Please enter correct email id ......");
			return "forgotPwd";
		}		
	}

	@GetMapping("/forgotPwd")
	public String forgotPwd() {	   
	    return "forgotPwd";
	}
}
