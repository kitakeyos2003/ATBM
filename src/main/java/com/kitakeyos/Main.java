package com.kitakeyos;

import com.kitakeyos.util.Log;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/*
 *   Name: Hoang Huu Dung
 *   Github: https://github.com/kitakeyos2003/ATBM.git
*/
public class Main {
    public static void main(String[] args) {
        byte[] data = null;
        try {
            data = Files.readAllBytes(Paths.get("data/data.txt"));
        } catch (IOException e) {
            Log.error("Error reading file", e);
        }
        if (data == null) {
            Log.error("No data to send");
            return;
        }
        User user1 = new User("user 1", 10);
        User user2 = new User("user 2", 10);
        user1.send(data, user2);
    }
}
