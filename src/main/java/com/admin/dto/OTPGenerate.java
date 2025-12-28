package com.admin.dto;

import java.security.SecureRandom;

public class OTPGenerate {

    private static final SecureRandom random=new SecureRandom();
    public static String otpGenerate(int num){

        StringBuilder otp=new StringBuilder();
        for(int i=0;i<num;i++){

            otp.append(random.nextInt(10));
        }
        return otp.toString();
    }
}
