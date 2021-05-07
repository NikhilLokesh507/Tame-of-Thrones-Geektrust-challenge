package com.geektrust.tameofthrones.model.encryption;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

public class TestEncryption {

    private CaesarCipher caesarCipher;

    @Before
    public void setup() {
        caesarCipher = new CaesarCipherImpl();
    }

    @Test
    public void testCaesarCipherCorrectness() {
        String decrypt = "ROZO";
        String encrypt = "OLWL";
        Assert.assertEquals(encrypt, caesarCipher.encrypt(3, decrypt));
    }

    @Test
    public void testCaesarCipherModulus26() {
        String decrypt = "AKSFJGLAJIGJ";
        Random random = new Random();
        int integer = Math.abs(random.nextInt());
        Assert.assertEquals(caesarCipher.encrypt(integer, decrypt), caesarCipher.encrypt(Math.abs(random.nextInt(1000)) * 26 + integer , decrypt));
    }
}
