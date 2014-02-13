package com.adaptionsoft.games.uglytrivia;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public abstract class SystemOutCapture {
    private final PrintStream originalSystemOut;
    private final ByteArrayOutputStream capturedSystemOut;

    public SystemOutCapture() {
        originalSystemOut = System.out;
        capturedSystemOut = captureSystemOut();
    }

    private ByteArrayOutputStream captureSystemOut() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        return out;
    }

    protected String capturedSystemOut() {
        return new String(capturedSystemOut.toByteArray());
    }

    protected String cr() {
        return System.getProperty("line.separator");
    }

    public void resetSystemOut() {
        System.setOut(originalSystemOut);
    }

}
