package net.mnb.codeprojects.javase.tenpinbowlingscore;

import net.mnb.codeprojects.javase.tenpinbowlingscore.exceptions.BoardValidationException;

/**
 * @author Michael Truong
 */
public class BowlingScoreFrame {
    public static final int MAX_ROLL_SCORE = 10;

    private int rollScore1;
    private int rollScore2;
    private int rollScore;
    private int frameScore;

    public BowlingScoreFrame(int rollScore1, int rollScore2) {
        this.rollScore1 = rollScore1;
        assertRollScoreRange(this.rollScore1);

        this.rollScore2 = rollScore2;
        assertRollScoreRange(this.rollScore2);

        this.rollScore = rollScore1 + rollScore2;
        assertRollScoreRange(this.rollScore);
    }

    public boolean isStrike() {
        return rollScore1 == MAX_ROLL_SCORE;
    }

    public boolean isSpare() {
        return !isStrike() && rollScore == MAX_ROLL_SCORE;
    }

    public int getRollScore1() {
        return rollScore1;
    }

    public int getRollScore2() {
        return rollScore2;
    }

    public int getRollScore() {
        return rollScore;
    }

    public int getFrameScore() {
        return frameScore;
    }

    public void setFrameScore(int frameScore) {
        this.frameScore = frameScore;
    }

    private static void assertRollScoreRange(int score) {
        if (score < 0 || score > MAX_ROLL_SCORE) {
            throw new BoardValidationException(
                    "Roll scores contain invalid score (value: {0}). Scores must be between 0 and 10", score);
        }
    }
}
