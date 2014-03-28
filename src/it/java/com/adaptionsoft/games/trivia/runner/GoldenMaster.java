package com.adaptionsoft.games.trivia.runner;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

public abstract class GoldenMaster {

    private static final String TEST_DATA = "testData/";
    public static final String RANDOM_DATA = TEST_DATA + "randomSequences";
    private static final String EXPECTED_DATE = TEST_DATA + "many/expected";

    private static final String DEFAULT_ENCODING = "ISO-8859-1";

    private int numberTestGames;
    private String randomsForFive;
    private String randomsForNine;

    public void iterateInputData() throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(RANDOM_DATA));
        try {
            iterateLinesOfRandoms(in);
        } finally {
            in.close();
        }
    }

    private void iterateLinesOfRandoms(BufferedReader randoms) throws IOException {
        while ((randomsForFive = randoms.readLine()) != null) {
            randomsForNine = randoms.readLine();

            String printOutput = playGame();

            inspectOutput(numberTestGames, printOutput);

            numberTestGames++;
        }
    }

    private String playGame() throws UnsupportedEncodingException {
        PrintStream oldOut = System.out;
        ByteArrayOutputStream rawOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(rawOutput));

        GameRunner.play(new ReplayingRandom(randomsForFive, randomsForNine));

        System.setOut(oldOut);
        String printOutput = rawOutput.toString(DEFAULT_ENCODING);

        return printOutput;
    }

    protected abstract void inspectOutput(int numberOfTest, String gameOutput) throws IOException;

    protected static String expectedFileName(int numberOfTest) {
        return EXPECTED_DATE + numberOfTest;
    }

}
