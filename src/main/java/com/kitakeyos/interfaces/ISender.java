package com.kitakeyos.interfaces;

public interface ISender {
    void send(byte[] data, IReceiver receiver);
    String getName();
}
