package com.stream;

import java.io.ByteArrayInputStream;

public class StreamL {
    public static void main(String[] args) {
        byte[] bytes = "test".getBytes();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);

        int d;
        while ((d = byteArrayInputStream.read()) != -1) {
            System.out.print((char) d);
        }
    }
}
