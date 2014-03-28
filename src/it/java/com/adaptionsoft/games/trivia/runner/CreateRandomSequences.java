package com.adaptionsoft.games.trivia.runner;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class CreateRandomSequences {

    private static final int NUM_TEST_GAMES = 250;
    private static final int NUM_RANDOMS_PER_GAME = 50;

    private static final Random RAND = new Random();

    public static void main(String[] args) throws IOException {
        PrintWriter out = new PrintWriter(new FileWriter(getSequenceFile()));
        try {
            printRandomsForTestGames(out);
            out.flush();
        } finally {
            out.close();
        }
    }

    private static File getSequenceFile() {
        File sequenceFile = new File(GoldenMaster.RANDOM_DATA);
        sequenceFile.getParentFile().mkdir();
        return sequenceFile;
    }

    private static void printRandomsForTestGames(PrintWriter out) {
        for (int i = 0; i < NUM_TEST_GAMES; i++) {
            printRandomsForFive(out);
            printRandomsForNine(out);
        }
    }

    private static void printRandomsForFive(PrintWriter out) {
        printRandomsForGame(out, 5);
    }

    private static void printRandomsForNine(PrintWriter out) {
        printRandomsForGame(out, 9);
    }

    private static void printRandomsForGame(PrintWriter out, int limitOfRandom) {
        for (int j = 0; j < NUM_RANDOMS_PER_GAME; j++) {
            out.write(RAND.nextInt(limitOfRandom) + ",");
        }
        out.println();
    }

}
