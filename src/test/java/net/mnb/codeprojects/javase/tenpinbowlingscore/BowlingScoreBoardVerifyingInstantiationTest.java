package net.mnb.codeprojects.javase.tenpinbowlingscore;

import net.mnb.codeprojects.javase.tenpinbowlingscore.exceptions.BoardValidationException;
import org.junit.Test;

public class BowlingScoreBoardVerifyingInstantiationTest {

    @Test
    public void shouldInit13Frames() throws Exception {
    }

    @Test
    public void shouldInitSeedFrame() throws Exception {
    }

    @Test
    public void shouldInitUndefinedFramesAsZeroScoreFrames() throws Exception {
    }

    @Test
    public void shouldInitStrikeFrames() throws Exception {
    }

    @Test
    public void shouldInitSpareFrames() throws Exception {
    }

    @Test(expected = BoardValidationException.class)
    public void shouldRaiseErrorWhenMoreThan24RollsAreMade() throws Exception {
    }

}
