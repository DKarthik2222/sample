package com.hotelManagement.angular.hotelmanagement.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SMSController {

	@Autowired
	private SMSServiceTwilio smsServiceTwilio;
	
	@CrossOrigin(origins="http://localhost:4200/")
	@GetMapping(value = "/phnumverify/{num}")
	public String phNumVerify(@PathVariable String num) {
		return smsServiceTwilio.phnumverify(num);
	}
	
	@CrossOrigin(origins="http://localhost:4200/")
	@GetMapping(value = "/forgotpwd/{num}")
	public String forgotPwd(@PathVariable String num) {
		return smsServiceTwilio.forgotpwd(num);
	}
}