package com.adaptionsoft.games.trivia.runner;

import java.io.IOException;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class GoldenTest {

    @Parameters
    public static Collection<Object[]> data() throws IOException {
        LoadGoldenMaster goldenMaster = new LoadGoldenMaster();
        goldenMaster.iterateInputData();
        return goldenMaster.getValues();
    }

    private String expectedOutput;
    private String actualOutput;

    public GoldenTest(String expectedOutput, String gameOutput) {
        this.expectedOutput = expectedOutput;
        this.actualOutput = gameOutput;
    }

    @Test
    public void shouldPlayGame() {
        assertEquals(expectedOutput, actualOutput);
    }

}
