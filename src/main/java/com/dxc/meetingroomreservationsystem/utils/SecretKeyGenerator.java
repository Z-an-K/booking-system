package com.dxc.meetingroomreservationsystem.utils;

import java.security.SecureRandom;
import java.util.Base64;

public class SecretKeyGenerator {
    public static String generateSecretKey() {
        SecureRandom secureRandom = new SecureRandom(); //threadsafe
        byte[] randomBytes = new byte[24];
        secureRandom.nextBytes(randomBytes);
        return Base64.getEncoder().encodeToString(randomBytes);
    }
}