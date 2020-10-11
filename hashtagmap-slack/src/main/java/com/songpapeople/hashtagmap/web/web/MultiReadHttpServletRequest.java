package com.songpapeople.hashtagmap.web.web;

import org.springframework.util.StreamUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MultiReadHttpServletRequest extends HttpServletRequestWrapper {
    public static final String DEFAULT_ENCODING = "UTF-8";

    private final String encoding;
    private final byte[] body;

    public MultiReadHttpServletRequest(HttpServletRequest httpServletRequest, String encoding) throws IOException {
        super(httpServletRequest);
        this.encoding = encoding;

        InputStream is = super.getInputStream();
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        StreamUtils.copy(is, output);
        this.body = output.toByteArray();
    }

    @Override
    public ServletInputStream getInputStream() {
        return new CachedServletInputStream(new ByteArrayInputStream(this.body));
    }

    @Override
    public BufferedReader getReader() throws IOException {
        String enc = super.getCharacterEncoding();
        if (enc == null) {
            enc = this.encoding;
        }
        return new BufferedReader(new InputStreamReader(getInputStream(), enc));
    }

    private static class CachedServletInputStream extends ServletInputStream {
        private final InputStream inputStream;

        public CachedServletInputStream(InputStream inputStream) {
            this.inputStream = inputStream;
        }

        @Override
        public int read() throws IOException {
            return inputStream.read();
        }

        @Override
        public boolean markSupported() {
            return false;
        }

        @Override
        public synchronized void mark(int i) {
            throw new RuntimeException(new IOException("mark/reset not supported"));
        }

        @Override
        public synchronized void reset() throws IOException {
            throw new IOException("mark/reset not supported");
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
        public void setReadListener(final ReadListener listener) {
        }
    }

}