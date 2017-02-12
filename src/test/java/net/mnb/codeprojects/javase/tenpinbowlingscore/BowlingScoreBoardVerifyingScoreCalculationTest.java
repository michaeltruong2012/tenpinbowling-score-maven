package net.mnb.codeprojects.javase.tenpinbowlingscore;

import net.mnb.codeprojects.javase.tenpinbowlingscore.exceptions.GameRuleViolationException;
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
                "2", "4",
                "8", "1",
                "3", "3",
                "5", "5",
                "8", "2",
                "0", "0",
                "0", "0",
                "0", "0",
                "0", "0",
                "9", "1",
                "8"
        });

        int finalScore = board.calculateFrameScores();

        assertEquals(67, finalScore);
    }

    @Test(expected = GameRuleViolationException.class)
    public void shouldRaiseErrorWhenGameHasOneSpareAtTheLastFrameButNoBonusRoll() throws Exception {
        BowlingScoreBoard board = new BowlingScoreBoard(new String[]{
                "2", "4",
                "8", "1",
                "3", "3",
                "5", "5",
                "8", "2",
                "0", "0",
                "0", "0",
                "0", "0",
                "0", "0",
                "9", "1", // spare
                // "8" <= this bonus roll is not made
        });

        board.calculateFrameScores();
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
}
