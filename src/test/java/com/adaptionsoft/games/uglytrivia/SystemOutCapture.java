package com.adaptionsoft.games.uglytrivia;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class SystemOutCapture {

    private PrintStream sysOut;

    public interface CodeBlock {
        void whileCaptured(ByteArrayOutputStream systemOut);
    }

    public static void execute(CodeBlock body) {
        SystemOutCapture c = new SystemOutCapture();
        try {
            ByteArrayOutputStream systemOut = c.captureSystemOut();

            body.whileCaptured(systemOut);
        } finally {
            c.resetSystemOut();
        }
    }

    private ByteArrayOutputStream captureSystemOut() {
        sysOut = System.out;

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        return out;
    }

    private void resetSystemOut() {
        System.setOut(sysOut);
    }
}
