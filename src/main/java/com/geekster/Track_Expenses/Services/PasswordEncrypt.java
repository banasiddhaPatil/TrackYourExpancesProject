package com.geekster.Track_Expenses.Services;

import jakarta.xml.bind.DatatypeConverter;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class PasswordEncrypt {
    public static String encrypt(String password) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");

        md5.update(password.getBytes());
        byte[] digested = md5.digest();

        return DatatypeConverter.printHexBinary(digested);

    }
}
