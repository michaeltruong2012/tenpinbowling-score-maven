package net.mnb.codeprojects.javase.tenpinbowlingscore;

import org.junit.Test;

import static net.mnb.codeprojects.javase.tenpinbowlingscore.BowlingScoreCalculationApp.doCalculate;
import static org.junit.Assert.assertEquals;

/**
 * This test verifies all components together and therefore, focuses only on integration among components. Component-level tests cover all functions
 * in depth.
 */
public class BowlingScoreCalculationAppIntegrationTest {

    @Test
    public void shouldReturn10WhenInputIs1_2_3_4() throws Exception {
        int score = doCalculate(new String[]{"1 2  3 4"});

        assertEquals(10, score);
    }

    @Test
    public void shouldReturn29WhenInputIs9_1_9_1() throws Exception {
        int score = doCalculate(new String[]{"9 1 9 1"});

        assertEquals(29, score);
    }

    @Test
    public void shouldReturn300ForPerfectGame() throws Exception {
        int score = doCalculate(new String[]{"10 10 10 10 10 10 10 10 10 10 10 10"});

        assertEquals(300, score);
    }

    @Test
    public void shouldReturn18WhenInputIs1_1_1_1_10_1_1() throws Exception {
        int score = doCalculate(new String[]{"1 1 1 1 10 1 1"});

        assertEquals(18, score);
    }

    @Test
    public void shouldReturn94WhenInputIs1_5_5_3_2_3_4_3_2_4_6_4_1_3_3_5_0_10_10_8_1() throws Exception {
        int score = doCalculate(new String[]{
                "1 5 " +
                        "5 3 " +
                        "2 3 " +
                        "4 3 " +
                        "2 4 " +
                        "6 4 " +
                        "1 3 " +
                        "3 5 " +
                        "0 10 " +
                        "10 8 1"});

        assertEquals(94, score);
    }
}
