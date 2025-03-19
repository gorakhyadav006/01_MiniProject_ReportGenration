package com.fotp.service;

import org.springframework.stereotype.Service;

import com.fotp.binding.LoginForm;
import com.fotp.binding.SignUpForm;
import com.fotp.binding.UnlockForm;

@Service
public interface UserService {
	
	public String loginFormUser(LoginForm form);
	
	public boolean signUp(SignUpForm form);
	
	public boolean unlockAccount(UnlockForm form);
	
	public boolean forgotPwd(String email);
	
}
