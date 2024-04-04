package com.LoginRegistration.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.LoginRegistration.models.Account;
import com.LoginRegistration.models.LoginUser;
import com.LoginRegistration.services.AccountService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class LoginController {
    

	@Autowired
	private AccountService accountService;
    
    @GetMapping("/")
    public String main(Model model) {
    
        model.addAttribute("newAcc", new Account());
        model.addAttribute("newLogin", new LoginUser());
        return "LogRegForm";
    }
    
    @PostMapping("/register")
    public String register(Model model, HttpSession session,
    		@Valid @ModelAttribute("newAcc") Account newAccount,BindingResult result) {
        
        Account newAcc = accountService.register(newAccount, result);
        
        if (result.hasErrors()) {
            model.addAttribute("newLogin", new LoginUser());
            return "LogRegForm";
        }
        
        if (newAcc == null) {
            return "LogRegForm";
        }
        
        session.setAttribute("accoundId",newAcc.getId());
    
        return "redirect:/home";

    }
   
    @GetMapping("/home")
    public String homepage(Model model, HttpSession session) {
    	Account account = accountService.findById((Long)session.getAttribute("accountId"));
    	model.addAttribute("acc", account);
    	return "Welcome";
    }
   
    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("LoginUser") LoginUser newLogin, 
            BindingResult result, Model model, HttpSession session) {
        
        Account authenticatedAcc = accountService.login(newLogin, result);
    	
    	if (result.hasErrors()) {
            model.addAttribute("newAcc", new Account());
            return "LogRegForm";
        }
    
        if (authenticatedAcc == null) {
            return "LogRegForm";
        }
    
        session.setAttribute("accountId", authenticatedAcc.getId());
    
        return "redirect:/home";
    }
    
    @GetMapping("/logout")
    public String logout(HttpSession session) {

        session.invalidate();
        return "redirect:/"; 
    }
}