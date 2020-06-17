package com.car.park.web.support;

import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
public class PasswordEncoder {

    private static final String ALGORITHM = "MD5";
    private static final int SIGNUM = 1;
    private static final int RADIX = 16;
    private static final String LEADING_ZERO = "0";

    public String encode(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance(ALGORITHM);
            byte[] messageDigest = md.digest(password.getBytes());
            BigInteger number = new BigInteger(SIGNUM, messageDigest);
            String hashPassword = number.toString(RADIX);
            while (hashPassword.length() < RADIX * 2) {
                hashPassword = LEADING_ZERO + hashPassword;
            }
            return hashPassword;
        } catch (NoSuchAlgorithmException ex) {
            throw new RuntimeException(ex);
        }
    }
}

