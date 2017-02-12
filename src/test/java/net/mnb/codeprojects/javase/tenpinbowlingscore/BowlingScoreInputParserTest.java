package net.mnb.codeprojects.javase.tenpinbowlingscore;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Optional;

public class BowlingScoreInputParserTest {

    @Test
    public void shouldCreateScoreBoardWhenInputArgIsValid() throws Exception {
        Optional<BowlingScoreBoard> result = BowlingScoreInputParser.parse(new String[]{"1 8 1 8 2 0"});

        Assert.assertNotNull(result);
        Assert.assertTrue(result.isPresent());
    }

    @Test
    public void shouldIgnoreRedundantSpacesInRollScoreText() throws Exception {
        String[] expectedResult = BowlingScoreInputParser.parseRollScore("1 8 1 8 2 0");

        String[] result = BowlingScoreInputParser.parseRollScore("1 8 1   8 2 0");

        Assert.assertTrue(Arrays.equals(result, expectedResult));
    }

    @Test
    public void shouldNotCreateScoreBoardWhenInputArgIsEmpty() throws Exception {
        Optional<BowlingScoreBoard> result = BowlingScoreInputParser.parse(new String[]{""});

        Assert.assertNotNull(result);
        Assert.assertFalse(result.isPresent());
    }

    @Test
    public void shouldNotCreateScoreBoardWhenInputArgIsNull() throws Exception {
        Optional<BowlingScoreBoard> result = BowlingScoreInputParser.parse(new String[]{null});

        Assert.assertNotNull(result);
        Assert.assertFalse(result.isPresent());
    }

    @Test
    public void shouldNotCreateScoreBoardWhenAppStartsWithNoArg() throws Exception {
        Optional<BowlingScoreBoard> result = BowlingScoreInputParser.parse(null);

        Assert.assertNotNull(result);
        Assert.assertFalse(result.isPresent());
    }

    @Test
    public void shouldNotCreateScoreBoardWhenAppStartsWithMoreThanOneArg() throws Exception {
        Optional<BowlingScoreBoard> result = BowlingScoreInputParser.parse(new String[3]);

        Assert.assertNotNull(result);
        Assert.assertFalse(result.isPresent());
    }
}
