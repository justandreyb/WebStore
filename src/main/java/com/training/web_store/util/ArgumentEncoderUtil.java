package com.training.web_store.util;

import com.training.web_store.util.exception.UtilException;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ArgumentEncoderUtil {

    public static String encodePassword(String password) throws UtilException {
        if (password == null) {
            return null;
        }
        password = md5(password);

        return password;
    }

    private static String md5(String input) throws UtilException {
        String md5 = null;
        if(null == input) {
            return null;
        }

        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");

            digest.update(input.getBytes(), 0, input.length());

            md5 = new BigInteger(1, digest.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            throw new UtilException(e);
        }
        return md5;
    }
}
