package net.mnb.codeprojects.javase.tenpinbowlingscore;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Expected results are retrieved from the online bowling game score website <a href="http://bit.ly/2kVCSTk">here</a>.
 */
public class BowlingScoreBoardVerifyingScoreCalculationTest {

    @Test
    public void shouldReturn300ForPerfectGame() throws Exception {
        BowlingScoreBoard board = new BowlingScoreBoard(new String[]{
                "10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10"});

        int finalScore = board.calculateFrameScores();

        assertEquals(300, finalScore);
    }

    @Test
    public void shouldReturn0ForWorstGame() throws Exception {
        BowlingScoreBoard board = new BowlingScoreBoard(new String[]{
                "0", "0", "0", "0", "0", "0", "0", "0", "0", "0"});

        int finalScore = board.calculateFrameScores();

        assertEquals(0, finalScore);
    }

    @Test
    public void shouldReturnScoreForGameWithoutStrikeOrSpare() throws Exception {
        BowlingScoreBoard board = new BowlingScoreBoard(new String[]{"1", "2", "3", "4"});

        int finalScore = board.calculateFrameScores();

        assertEquals(10, finalScore);
    }

    // -------------------------------------------------
    // tests for games with spares
    // -------------------------------------------------

    @Test
    public void shouldReturnScoreForGameWithOneSpareBeforeTheLastFrame() throws Exception {
        BowlingScoreBoard board = new BowlingScoreBoard(new String[]{"9", "1", "9", "1"});

        int finalScore = board.calculateFrameScores();

        assertEquals(29, finalScore);
    }

    @Test
    public void shouldReturnScoreForGameWithOneSpareAtTheLastFrame() throws Exception {
        BowlingScoreBoard board = new BowlingScoreBoard(new String[]{
                "2", "4", "8", "1", "3", "3", "5", "5", "8", "2",
                "0", "0", "0", "0", "0", "0", "0", "0",
                "9", "1", // spare
                "8"
        });

        int finalScore = board.calculateFrameScores();

        assertEquals(67, finalScore);
    }

    @Test
    public void shouldReturnScoreForGameWithOneSpareAtTheLastFrameAndOneStrikeBonus() throws Exception {
        BowlingScoreBoard board = new BowlingScoreBoard(new String[]{
                "2", "4", "8", "1", "3", "3", "5", "5", "8", "2",
                "0", "0", "0", "0", "0", "0", "0", "0",
                "9", "1", // spare
                "10" // strike
        });

        int finalScore = board.calculateFrameScores();

        assertEquals(69, finalScore);
    }

    @Test
    public void shouldReturnScoreWhenGameHasOneSpareAtTheLastFrameButNoBonusRollExists() throws Exception {
        BowlingScoreBoard board = new BowlingScoreBoard(new String[]{
                "2", "4", "8", "1", "3", "3", "5", "5", "8", "2",
                "0", "0", "0", "0", "0", "0", "0", "0",
                "9", "1" // spare
        });

        int finalScore = board.calculateFrameScores();

        assertEquals(59, finalScore);
    }

    @Test
    public void shouldIgnoreRedundantBonusRollWhenGameHasOneSpareAtTheLastFrameButTwoBonusRollsExist() throws Exception {
        BowlingScoreBoard board = new BowlingScoreBoard(new String[]{
                "2", "4", "8", "1", "3", "3", "5", "5", "8", "2",
                "0", "0", "0", "0", "0", "0", "0", "0",
                "9", "1", // spare
                "8",
                "1" // redundant bonus
        });

        int finalScore = board.calculateFrameScores();

        assertEquals(67, finalScore);
    }

    // -------------------------------------------------
    // tests for games with strikes
    // -------------------------------------------------

    @Test
    public void shouldReturnScoreForGameWithOneStrikeBeforeTheLastFrame() throws Exception {
        BowlingScoreBoard board = new BowlingScoreBoard(new String[]{"1", "1", "1", "1", "10", "1", "1"});

        int finalScore = board.calculateFrameScores();

        assertEquals(18, finalScore);
    }

    @Test
    public void shouldReturnScoreForGameWithOneStrikeInTheLastFrameAndStrikeBonus() throws Exception {
        BowlingScoreBoard board = new BowlingScoreBoard(new String[]{
                "1", "5",
                "5", "3",
                "2", "3",
                "4", "3",
                "2", "4",
                "6", "4",
                "1", "3",
                "3", "5",
                "10", // strike at frame 9
                "10", // strike at frame 10
                "10", // bonus (strike)
                "10" // bonus (strike)
        });

        int finalScore = board.calculateFrameScores();

        assertEquals(115, finalScore);
    }

    @Test
    public void shouldReturnScoreForGameWithOneStrikeInTheLastFrameButNoStrikeOrSpareBonus() throws Exception {
        BowlingScoreBoard board = new BowlingScoreBoard(new String[]{
                "1", "5",
                "5", "3",
                "2", "3",
                "4", "3",
                "2", "4",
                "6", "4",
                "1", "3",
                "3", "5",
                "0", "10", // spare at frame 9
                "10", // strike at frame 10
                "8", "1" // bonus
        });

        int finalScore = board.calculateFrameScores();

        assertEquals(94, finalScore);
    }

    @Test
    public void shouldReturnScoreForGameWithOneStrikeInTheLastFrameAndSpareBonus() throws Exception {
        BowlingScoreBoard board = new BowlingScoreBoard(new String[]{
                "1", "5",
                "5", "3",
                "2", "3",
                "4", "3",
                "2", "4",
                "6", "4",
                "1", "3",
                "3", "5",
                "0", "10", // spare at frame 9
                "10", // strike at frame 10
                "8", "2" // bonus (spare)
        });

        int finalScore = board.calculateFrameScores();

        assertEquals(95, finalScore);
    }

    @Test
    public void shouldReturnScoreForGameWithOneStrikeInTheLastFrameAndZeroBonus() throws Exception {
        BowlingScoreBoard board = new BowlingScoreBoard(new String[]{
                "1", "5",
                "5", "3",
                "2", "3",
                "4", "3",
                "2", "4",
                "6", "4",
                "1", "3",
                "3", "5",
                "10", // strike at frame 9
                "10", // strike at frame 10
                "0", "0" // 0-bonus
        });

        int finalScore = board.calculateFrameScores();

        assertEquals(85, finalScore);
    }

}
