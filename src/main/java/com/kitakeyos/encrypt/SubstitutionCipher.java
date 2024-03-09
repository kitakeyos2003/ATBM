package com.kitakeyos.encrypt;

import com.kitakeyos.interfaces.IDecode;
import com.kitakeyos.interfaces.IEncode;

public class SubstitutionCipher implements IDecode, IEncode {
    private String alphabet;
    private String cipherAlphabet;

    public SubstitutionCipher(String alphabet, String cipherAlphabet) {
        this.alphabet = alphabet;
        this.cipherAlphabet = cipherAlphabet;
    }

    @Override
    public String decode(String cipherText) {
        StringBuilder plainText = new StringBuilder();
        for (int i = 0; i < cipherText.length(); i++) {
            int index = cipherAlphabet.indexOf(cipherText.charAt(i));
            if (index != -1) {
                plainText.append(alphabet.charAt(index));
            } else {
                plainText.append(cipherText.charAt(i));
            }
        }
        return plainText.toString();
    }

    @Override
    public String encode(String plainText) {
        StringBuilder cipherText = new StringBuilder();
        for (int i = 0; i < plainText.length(); i++) {
            int index = alphabet.indexOf(plainText.charAt(i));
            if (index != -1) {
                cipherText.append(cipherAlphabet.charAt(index));
            } else {
                cipherText.append(plainText.charAt(i));
            }
        }
        return cipherText.toString();
    }
}

