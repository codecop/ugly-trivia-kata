package com.adaptionsoft.games.trivia.runner;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SaveGoldenMaster extends GoldenMaster {

    @Override
    protected void inspectOutput(int numberOfTest, String gameOutput) throws IOException {
        String fileName = expectedFileName(numberOfTest);
        saveOutputTo(fileName, gameOutput);
    }

    private void saveOutputTo(String fileName, String gameOutput) throws IOException {
        FileWriter out = new FileWriter(getExpectedFile(fileName));
        try {
            saveOutputTo(out, gameOutput);
        } finally {
            out.close();
        }
    }

    private File getExpectedFile(String fileName) {
        File expectedFile = new File(fileName);
        expectedFile.getParentFile().mkdirs();
        return expectedFile;
    }

    private void saveOutputTo(FileWriter out, String gameOutput) throws IOException {
        out.write(gameOutput);
    }

    public static void main(String[] args) throws IOException {
        new SaveGoldenMaster().iterateInputData();
    }
}
