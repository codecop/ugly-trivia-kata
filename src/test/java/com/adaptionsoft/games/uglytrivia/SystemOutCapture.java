package com.adaptionsoft.games.uglytrivia;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

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

    protected List<String> capturedSystemOutLines() {
        return Arrays.asList(capturedSystemOut().split(cr()));
    }

    public void resetSystemOut() {
        System.setOut(originalSystemOut);
    }

}
