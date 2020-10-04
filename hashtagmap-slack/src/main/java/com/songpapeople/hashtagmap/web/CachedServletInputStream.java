package com.songpapeople.hashtagmap.web;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class CachedServletInputStream extends ServletInputStream {
    private final ByteArrayInputStream input;

    public CachedServletInputStream(ByteArrayOutputStream byteArrayOutputStream) {
        input = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public boolean isReady() {
        return true;
    }

    @Override
    public void setReadListener(ReadListener listener) {
    }

    @Override
    public int read() {
        return input.read();
    }
}