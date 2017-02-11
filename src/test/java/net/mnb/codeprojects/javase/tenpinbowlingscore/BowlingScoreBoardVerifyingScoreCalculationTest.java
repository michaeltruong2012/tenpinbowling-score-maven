package net.mnb.codeprojects.javase.tenpinbowlingscore;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BowlingScoreBoardVerifyingScoreCalculationTest {

    @Test
    public void shouldReturn300ForPerfectGame() throws Exception {
        BowlingScoreBoard board = new BowlingScoreBoard(new String[]{"10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10"});

        int finalScore = board.calculateFrameScores();

        assertEquals(300, finalScore);
    }

    @Test
    public void shouldReturn10ForRollScores_1_2_3_4() throws Exception {
        BowlingScoreBoard board = new BowlingScoreBoard(new String[]{"1", "2", "3", "4"});

        int finalScore = board.calculateFrameScores();

        assertEquals(10, finalScore);
    }

    @Test
    public void shouldReturn29ForRollScores_9_1_9_1() throws Exception {
        BowlingScoreBoard board = new BowlingScoreBoard(new String[]{"9", "1", "9", "1"});

        int finalScore = board.calculateFrameScores();

        assertEquals(29, finalScore);
    }

    @Test
    public void shouldReturn18ForRollScores_1_1_1_1_10_1_1() throws Exception {
        BowlingScoreBoard board = new BowlingScoreBoard(new String[]{"1", "1", "1", "1", "10", "1", "1"});

        int finalScore = board.calculateFrameScores();

        assertEquals(18, finalScore);
    }
}
