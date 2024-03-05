package com.kitakeyos.encrypt;

import com.kitakeyos.interfaces.IDecode;
import com.kitakeyos.interfaces.IEncode;

public class SubstitutionCipher implements IDecode, IEncode {
    private int key;
    private char[] substitution;
    private int substitutionSize;

    public SubstitutionCipher(int key, char[] substitution) {
        this.key = key;
        this.substitution = substitution;
        this.substitutionSize = substitution.length;
    }

    @Override
    public String decode(String code) {
        StringBuilder decodedText = new StringBuilder();
        for (int i = 0; i < code.length(); i++) {
            char currentChar = code.charAt(i);
            int index = getIndexFromSubstitution(currentChar);
            int key = this.key % substitutionSize;
            if (index != -1) {
                index = (index - key);
                if (index < 0) {
                    index += substitution.length;
                }
                decodedText.append(substitution[index]);
            } else {
                decodedText.append(currentChar);
            }
        }

        return decodedText.toString();
    }

    @Override
    public String encode(String plainText) {
        StringBuilder encodedText = new StringBuilder();
        for (int i = 0; i < plainText.length(); i++) {
            char currentChar = plainText.charAt(i);
            int index = getIndexFromSubstitution(currentChar);
            if (index != -1) {
                index = (index + key) % substitutionSize;
                encodedText.append(substitution[index]);
            } else {
                encodedText.append(currentChar);
            }
        }

        return encodedText.toString();
    }

    private int getIndexFromSubstitution(char code) {
        for (int i = 0; i < substitution.length; i++) {
            if (substitution[i] == code) {
                return i;
            }
        }
        return -1;
    }
}

