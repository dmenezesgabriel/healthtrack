package com.healthtrack.util;

import java.math.BigInteger;
import java.security.MessageDigest;

public class Cryptography {
    public static String encrypt(String text) throws Exception {
        MessageDigest md = MessageDigest.getInstance("md5");
        md.update(text.getBytes("ISO-8859-1"));
        BigInteger hash = new BigInteger(1, md.digest());
        return hash.toString(16);
    }
}
