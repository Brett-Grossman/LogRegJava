package com.LoginRegistration.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.LoginRegistration.models.Account;
import com.LoginRegistration.models.LoginUser;
import com.LoginRegistration.repositories.AccountRepository;

import jakarta.validation.Valid;

@Service
public class AccountService {
    
    @Autowired
    private AccountRepository accountRepo;
    

    public Account register(Account newAcc, BindingResult result) {
    	if (result.hasErrors()) {

            return null;
        }
        Optional<Account> existingUser = accountRepo.findByEmail(newAcc.getEmail());
        if (existingUser.isPresent()) {
            result.rejectValue("email", "emailInUse", "This email is already being used.");
        }
        if (!newAcc.getPassword().equals(newAcc.getConfirmation())) {

        	result.rejectValue("confirmation", "matchError", "Your passwords do not match.");
        }
        if (result.hasErrors()) 
        	return null;
        else {
        	String hashed = BCrypt.hashpw(newAcc.getPassword(), BCrypt.gensalt());
        	newAcc.setPassword(hashed);
        	accountRepo.save(newAcc);
        }
        return newAcc;
    }
    public Account login(@Valid LoginUser authenticatedAcc, BindingResult result) {
    	Optional<Account> availableAcc = accountRepo.findByEmail(authenticatedAcc.getEmail());
    	if (availableAcc.isEmpty()) {
    		result.rejectValue("email","noEmail","No account is linked to that email.");
    	}
    	Account acc = availableAcc.get();
    	
    	if (!BCrypt.checkpw(authenticatedAcc.getPassword(), acc.getPassword())) {
    		result.rejectValue("password", "incorrectPass","Incorrect password entered");
    	}
    	if(result.hasErrors()) return null;
    	else return acc;
    }
    
    public Account findById(Long id) {


        // Find the user in the DB by email
        Optional<Account> acc = accountRepo.findById(id);
        if (acc == null) {

            return null;
        }

        return acc.get();
    }
}