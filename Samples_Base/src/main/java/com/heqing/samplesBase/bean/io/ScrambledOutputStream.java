/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.heqing.samplesBase.bean.io;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

/**
 *
 * @author Administrator
 */
public class ScrambledOutputStream extends OutputStream{

    private final boolean plainText;
    private final byte[] mask = new byte[1024];
    private int c = 0;

    public int getDefaultKey() {
        return 0;
    }

    OutputStream out;

    public ScrambledOutputStream(OutputStream client, long key) {
        this.out = client;
        new Random(key).nextBytes(mask);
        plainText = key == 0;
    }

    public ScrambledOutputStream(OutputStream client) {
        this.out = client;
        int key = getDefaultKey();
        new Random(key).nextBytes(mask);
        plainText = key == 0;
    }

    public synchronized void write(int data) throws IOException {
        out.write(plainText ? data : data ^ mask[c]);
        c = (c + 1) % 1024;
    }

    public synchronized void write(byte[] data) throws IOException {
        write(data, 0, data.length);
    }

    public synchronized void write(byte[] data, int off, int length) throws IOException {
        if (plainText) {
            out.write(data, off, length);
            c += length;
        } else {
            byte[] buff = new byte[length];
            for (int i = off; i < off + length; ++i) {
                buff[i - off] = (byte) (0xFF & (mask[c] ^ data[i]));
                c = (c + 1) % 1024;
            }
            out.write(buff, 0, length);
        }
    }
}
