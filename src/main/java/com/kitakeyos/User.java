package com.kitakeyos;

import com.kitakeyos.encrypt.ShiftCipher;
import com.kitakeyos.interfaces.IReceiver;
import com.kitakeyos.interfaces.ISender;
import com.kitakeyos.util.Log;

public class User implements ISender, IReceiver {
    private String name;
    private ShiftCipher cipher;

    public User(String name, int key) {
        this.name = name;
        char[] substitution = new char[]{
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                ' '};
        this.cipher = new ShiftCipher(substitution.length, key, substitution);
    }

    @Override
    public void receive(byte[] data, ISender sender) {
        String message = new String(data);
        Log.info(String.format("[%s] received message: %s", this.name, message));
        String decodedMessage = cipher.decode(message);
        String receiverName = getName();
        String senderName = sender.getName();
        Log.info(String.format("[%s] received message from [%s]: %s", receiverName, senderName, decodedMessage));
    }

    @Override
    public void send(byte[] data, IReceiver receiver) {
        String message = new String(data);
        Log.info(String.format("[%s] sent message: %s", this.name, message));
        String encodedMessage = cipher.encode(message);
        Log.info(String.format("[%s] encoded message: %s", this.name, encodedMessage));
        byte[] encodedData = encodedMessage.getBytes();
        receiver.receive(encodedData, this);
    }

    @Override
    public String getName() {
        return this.name;
    }
}
