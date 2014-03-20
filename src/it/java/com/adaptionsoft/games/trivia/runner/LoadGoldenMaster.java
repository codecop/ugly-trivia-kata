package com.adaptionsoft.games.trivia.runner;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoadGoldenMaster extends GoldenMaster {

    private final List<Object[]> values = new ArrayList<Object[]>();

    @Override
    protected void inspectOutput(int numberOfTest, String gameOutput) throws IOException {
        String expectedOutput = loadExpectedOutputFor(numberOfTest);
        values.add(new Object[] { expectedOutput, gameOutput });
    }

    private String loadExpectedOutputFor(int numberOfTest) throws IOException {
        String fileName = expectedFileName(numberOfTest);
        File file = new File(fileName);
        char[] data = loadCharsFrom(file);
        return new String(data);
    }

    private char[] loadCharsFrom(File file) throws IOException {
        FileReader in = new FileReader(file);
        try {
            char[] data = new char[(int) file.length()];
            in.read(data);
            return data;
        } finally {
            in.close();
        }
    }

    public List<Object[]> getValues() {
        return values;
    }
}