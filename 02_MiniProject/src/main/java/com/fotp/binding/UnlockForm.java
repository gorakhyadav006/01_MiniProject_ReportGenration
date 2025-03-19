package com.fotp.binding;

import lombok.Data;

@Data
public class UnlockForm {

	private String email;
	private String tempPwd;
	
	private String currentPwd;
	
	private String confirmPwd;
}
