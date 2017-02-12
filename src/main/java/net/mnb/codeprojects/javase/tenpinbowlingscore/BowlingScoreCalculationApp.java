package net.mnb.codeprojects.javase.tenpinbowlingscore;

/**
 * The main application which calculates the final score of a ten-pin bowling game.
 * The scoring rules can be found in the <a href="http://www.bowling2u.com/trivia/game/scoring.asp">Scoring</a> page.
 * <p>
 * <b>Syntax</b>
 * <pre>BowlingScoreCalculationApp "roll-score-1 roll-score-2 ... roll-score-N"</pre>
 * <i>Where</i> N in [1..10]
 *
 * <b>NOTE</b>: If N < 10, all frames from N+1 to 10 are deemed 0.
 *
 * @author Michael Truong
 */
public class BowlingScoreCalculationApp {

    public static void main(String[] args) {
        int totalScore = doCalculate(args);
        System.out.println("Final score: " + totalScore);
    }

    static int doCalculate(String[] args) {
        return BowlingScoreInputParser
                .parse(args)
                .map(BowlingScoreBoard::calculateFrameScores)
                .orElse(0);
    }
}

