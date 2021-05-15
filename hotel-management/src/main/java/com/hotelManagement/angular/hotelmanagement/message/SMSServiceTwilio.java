package com.hotelManagement.angular.hotelmanagement.message;

import java.lang.Math;
import org.springframework.stereotype.Service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
@Service
public class SMSServiceTwilio{
    public static final String ACCOUNT_SID = "ACda4234d33791372d074af266c0dc665e"; //replace with ur details
    public static final String AUTH_TOKEN = "d4c9ef316ef3fc10718cddc62e5d0f1c"; //replace with ur details

    public String phnumverify(String num) {
    	int otp=(int) (Math.random()*(999999-100000+1)+100000);
    	
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        @SuppressWarnings("unused")
		Message message = Message.creator(
                new com.twilio.type.PhoneNumber(num),  //to ////replace with ur num
                new com.twilio.type.PhoneNumber("+16103793727"),   //from ////replace with ur acc ph num
                "Your OTP to register is "+Integer.toString(otp))
           .create();

        return Integer.toString(otp);
    }
    
    public String forgotpwd(String num) {
    	int otp=(int) (Math.random()*(999999-100000+1)+100000);
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        @SuppressWarnings("unused")
		Message message = Message.creator(
                new com.twilio.type.PhoneNumber(num),  //to
                new com.twilio.type.PhoneNumber("+16103793727"),   //from
                "Your OTP to reset password is "+Integer.toString(otp))
           .create();

        return Integer.toString(otp);
    }
    
    public static void main(String[] args) {
    	SMSServiceTwilio sms=new SMSServiceTwilio();
    	sms.forgotpwd(ACCOUNT_SID);
    }
}