package net.mnb.codeprojects.javase.tenpinbowlingscore;

import net.mnb.codeprojects.javase.tenpinbowlingscore.exceptions.BoardValidationException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BowlingScoreBoardVerifyingInstantiationTest {


    @Test
    public void shouldInit13Frames() throws Exception {
        BowlingScoreBoard board = new BowlingScoreBoard(new String[]{"9", "1", "9", "1"});

        assertEquals(13, board.getScoreFrames().size());
    }

    @Test
    public void shouldInitSeedFrame() throws Exception {
        BowlingScoreBoard board = new BowlingScoreBoard(new String[]{"9", "1", "9", "1"});

        BowlingScoreFrame seedFrame = board.getScoreFrames().get(0);
        assertEquals(0, seedFrame.getRollScore());
        assertEquals(0, seedFrame.getRollScore1());
        assertEquals(0, seedFrame.getRollScore2());
    }

    @Test
    public void shouldInitUndefinedFramesAsZeroScoreFrames() throws Exception {
        BowlingScoreBoard board = new BowlingScoreBoard(new String[]{"1", "1", "1", "1", "10", "0", "1", "1"});

        long zeroFrameCount = board.getScoreFrames().stream().filter(f -> f.getRollScore() == 0).count();
        assertEquals((13L - 4), zeroFrameCount);
    }

    @Test
    public void shouldInitStrikeFrames() throws Exception {
        BowlingScoreBoard board = new BowlingScoreBoard(new String[]{"10", "0", "1", "1", "10", "0", "1", "1"});

        long strikeFrameCount = board.getScoreFrames().stream().filter(BowlingScoreFrame::isStrike).count();
        assertEquals(2, strikeFrameCount);
    }

    @Test
    public void shouldInitSpareFrames() throws Exception {
        BowlingScoreBoard board = new BowlingScoreBoard(new String[]{"9", "1", "3", "2", "10", "0", "2", "8"});

        long spareFrameCount = board.getScoreFrames().stream().filter(BowlingScoreFrame::isSpare).count();
        assertEquals(2, spareFrameCount);
    }

    @Test(expected = BoardValidationException.class)
    public void shouldRaiseErrorWhenMoreThan24RollsAreMade() throws Exception {
        new BowlingScoreBoard(new String[26]);
    }

    @Test(expected = BoardValidationException.class)
    public void shouldRaiseErrorWhenRollScoreIsNotNumeric() throws Exception {
    }

    @Test(expected = BoardValidationException.class)
    public void shouldRaiseErrorWhenRollScoreIsOutOfRange() throws Exception {
    }
}
