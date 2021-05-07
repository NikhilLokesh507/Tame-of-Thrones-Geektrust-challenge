package com.geektrust.tameofthrones.model.encryption;

import java.util.LinkedList;
import java.util.List;

public class CaesarCipherImpl implements CaesarCipher {

    private static final List<Character> characterList;

    static {
        characterList = new LinkedList<>();
        for (int i = 1; i < 3; i++) {
            for (char c = 'A'; c <= 'Z'; c++) {
                characterList.add(c);
            }
        }
    }

    @Override
    public String encrypt(Integer key, final String decrypt) {
        key = key % 26;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < decrypt.length(); i++) {
            sb.append(encryptCharacter(decrypt.charAt(i), key));
        }
        return sb.toString();
    }

    private Character encryptCharacter(Character c, Integer key) {
        if (c.equals(' ')) {
            return ' ';
        }
        return characterList.get(characterList.lastIndexOf(c) - key);
    }
}
