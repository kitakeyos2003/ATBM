package com.kitakeyos.encrypt;

import com.kitakeyos.interfaces.IDecode;
import com.kitakeyos.interfaces.IEncode;
import com.kitakeyos.util.Log;

public class ShiftCipher implements IDecode, IEncode {

    private int z, k;
    private char[] array;

    public ShiftCipher(int z, int k, char[] array) {
        this.z = z;
        this.k = k;
        this.array = array;
    }

    @Override
    public String decode(String code) {
        StringBuilder decodedText = new StringBuilder();
        for (int i = 0; i < code.length(); i++) {
            char currentChar = code.charAt(i);
            int x = getIndex(currentChar);
            int d = (x - this.k) % z;
            if (d < 0) {
                d += z;
            }
            decodedText.append(array[d]);
        }

        return decodedText.toString();
    }

    @Override
    public String encode(String plainText) {
        StringBuilder encodedText = new StringBuilder();
        for (int i = 0; i < plainText.length(); i++) {
            char currentChar = plainText.charAt(i);
            int x = getIndex(currentChar);
            int d = (x + this.k) % z;
            encodedText.append(array[d]);
        }
        return encodedText.toString();
    }

    private int getIndex(char code) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == code) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        String plainText = "TONNAY";
        int z = 25;
        int k = 17;
        ShiftCipher shiftCipher = new ShiftCipher(z, k, new char[]{
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
                'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
        });
        Log.info("plainText:" + plainText + " z: " + z + " k: " + k);
        String encodedText = shiftCipher.encode(plainText);
        Log.info("encodedText: " + encodedText);
        String decodedText = shiftCipher.decode(encodedText);
        Log.info("decodedText: " + decodedText);
    }
}
