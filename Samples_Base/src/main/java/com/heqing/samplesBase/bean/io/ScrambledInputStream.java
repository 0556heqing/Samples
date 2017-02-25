/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.heqing.samplesBase.bean.io;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

/**
 *
 * @author Administrator
 */
public class ScrambledInputStream extends InputStream {

    private boolean plainText;
    private byte[] mask = new byte[1024];
    private int c = 0;

    public int getDefaultKey() {
        return 0;
    }
    InputStream in;

    public ScrambledInputStream(InputStream client) {
        this.in = client;
        int key = getDefaultKey();
        new Random(key).nextBytes(mask);
        plainText = key == 0;
    }

    public ScrambledInputStream(InputStream client, long key) {
        this.in = client;
        new Random(key).nextBytes(mask);
        plainText = key == 0;
    }

    ScrambledInputStream(InputStream client, byte[] mask) {
        this.in = client;
        this.mask = mask;
        plainText = false;
    }

    public static ScrambledInputStream getLegacy(InputStream in, long key) {
        Random RAND = new Random(key);
        byte[] mask = new byte[537];
        for (int i = 0; i < mask.length; ++i) {
            mask[i] = (byte) RAND.nextInt(128);
        }
        return new ScrambledInputStream(in, mask);
    }

    public synchronized int read() throws IOException {
        int b = in.read();
        if (b == -1 || plainText) {
            return b;
        }
        b = 0xFF & (b ^ mask[c]);
        c = (c + 1) % 1024;
        return b;
    }

    public synchronized int read(byte[] data) throws IOException {
        return read(data, 0, data.length);
    }

    public synchronized int read(byte[] data, int off, int length) throws IOException {
        int len = in.read(data, off, length);
        if (plainText) {
            return len;
        }
        for (int i = off; i < off + len; ++i) {
            data[i] = (byte) (0xFF & (data[i] ^ mask[c]));
            c = (c + 1) % 1024;
        }
        return len;
    }
    
    public int available() throws IOException { 
        return in.available();
    }
}
