package com.kitakeyos.interfaces;

public interface IReceiver {
    void receive(byte[] data, ISender sender);
    String getName();
}
