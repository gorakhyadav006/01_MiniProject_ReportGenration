package com.fotp.serviceimpl;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fotp.binding.LoginForm;
import com.fotp.binding.SignUpForm;
import com.fotp.binding.UnlockForm;
import com.fotp.constant.AppConstant;
import com.fotp.entity.UserDtlsEntity;
import com.fotp.repo.UserDtlsRepo;
import com.fotp.service.UserService;
import com.fotp.util.EmailUtils;
import com.fotp.util.PwdUtils;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDtlsRepo userDtlsrepo;

	@Autowired
	EmailUtils emailutil;
	
	@Autowired
	private HttpSession session;

	@Override
	public String loginFormUser(LoginForm form) {

		UserDtlsEntity entity = userDtlsrepo.findByEmailAndPwd(form.getEmail(), form.getPwd());

		if (entity == null) {
			return "invalid email&pwd";
		}
		if (entity != null && "LOCKED".equals(entity.getAccStatus())) {
			return "Please Unlock your account then login";
		}
		//Create session and store user data in session
			session.setAttribute("userId", entity.getUserid());
			return "success";
	}
	

	@Override
	public boolean signUp(SignUpForm form) {

		// checking where email id exit or not
		UserDtlsEntity user = userDtlsrepo.findByEmail(form.getEmail());

		if (user != null) {
			return false;
		}

		// TODO Copy Data from binding obj to entity obj
		UserDtlsEntity entity = new UserDtlsEntity();
		BeanUtils.copyProperties(form, entity);

		// ToDo: generate random pwd
		String tempPwd = PwdUtils.generateRandomPwd();
		entity.setPwd(tempPwd);

		// ToDo: set account status as locked
		entity.setAccStatus(AppConstant.STR_LOCK);

		// ToDo: insert record into table
		userDtlsrepo.save(entity);

		// ToDo: send email to unlock the account
		String to = form.getEmail();
		String subject = "Unlock Your Account ! JTC ";
		StringBuffer body = new StringBuffer("");
		body.append("<h1>Use bellow pwd to unlock your account</h1>");
		body.append("Temporary pwd : " + tempPwd);
		body.append("<br/>");
		body.append("<a href=\"http://localhost:8080/unlock?email=" + to + "\"> click here to unlock your account</a>");
		emailutil.sendEmail(to, subject, body.toString());
		return true;
	}

	@Override
	public boolean unlockAccount(UnlockForm form) {
		String email = form.getEmail();
		String pwd = form.getCurrentPwd();
		String tempPwd = form.getTempPwd();
		UserDtlsEntity objforpwd = userDtlsrepo.findByEmail(email);

		if (objforpwd.getPwd().equals(tempPwd)) {
			objforpwd.setPwd(pwd);
			objforpwd.setAccStatus(AppConstant.STR_UNLOCK);
			userDtlsrepo.save(objforpwd);
			return true;

		} else {
			return false;
		}
	}

	@Override
	public boolean forgotPwd(String email) {
		
		UserDtlsEntity user = userDtlsrepo.findByEmail(email);
		// checking where email id exit or not
		if (user != null) {

			String pwd = user.getPwd();
			String to = user.getEmail();
			String subject = "Your Password for Account :"+to;
			String body= "Your password is :"+pwd;
			emailutil.sendEmail(to, subject, body);
			return true;
		}else {
			return false;
		}
			
	}

}
