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

    public void doCapture() {
        try {
            whileCaptured();
        } finally {
            resetSystemOut();
        }
    }

    public abstract void whileCaptured();

    public String capturedSystemOut() {
        return new String(capturedSystemOut.toByteArray());
    }

    private void resetSystemOut() {
        System.setOut(originalSystemOut);
    }
}
