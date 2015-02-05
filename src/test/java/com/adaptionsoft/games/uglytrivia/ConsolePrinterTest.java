package com.adaptionsoft.games.uglytrivia;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

public class ConsolePrinterTest {
    @Test
    public void shouldPrintToSystemOut() {
       final ConsolePrinter consolePrinter = new ConsolePrinter();
        new SystemOutCapture() {
            {

                consolePrinter.print("Message");

                Assert.assertThat(capturedSystemOut(), is("Message" + cr()));

            }
        }.resetSystemOut();
    }
}
