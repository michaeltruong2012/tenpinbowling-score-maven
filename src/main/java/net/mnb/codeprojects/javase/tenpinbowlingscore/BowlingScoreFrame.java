package net.mnb.codeprojects.javase.tenpinbowlingscore;

import net.mnb.codeprojects.javase.tenpinbowlingscore.exceptions.BoardValidationException;

/**
 * @author Michael Truong
 */
class BowlingScoreFrame {
    private static final int MAX_ROLL_SCORE = 10;

    private int rollScore1 = -1;
    private int rollScore2 = -1;
    private int rollScore = 0;
    private int frameScore = 0;

    BowlingScoreFrame(int rollScore1) {
        this.rollScore1 = rollScore1;
        assertRollScoreRange(this.rollScore1);
    }

    BowlingScoreFrame(int rollScore1, int rollScore2) {
        this(rollScore1);
        setRollScore2(rollScore2);
    }

    static BowlingScoreFrame newStrike() {
        return new BowlingScoreFrame(10, 0);
    }

    boolean isStrike() {
        return isMaxScore(rollScore1);
    }

    boolean isSpare() {
        return !isStrike() && isMaxScore(rollScore);
    }

    int getRollScore1() {
        return rollScore1;
    }

    int getRollScore2() {
        return rollScore2;
    }

    void setRollScore2(int rollScore2) {
        this.rollScore2 = rollScore2;
        assertRollScoreRange(this.rollScore2);

        updateRollScore();
    }

    int getFrameScore() {
        return frameScore;
    }

    void setFrameScore(int frameScore) {
        this.frameScore = frameScore;
    }

    boolean hasAllScores() {
        return rollScore1 >= 0 && rollScore2 >= 0;
    }

    int getRollScore() {
        return rollScore;
    }

    private void updateRollScore() {
        this.rollScore = this.rollScore1 + this.rollScore2;
        assertRollScoreRange(this.rollScore);
    }

    private static void assertRollScoreRange(int score) {
        if (score < 0 || score > MAX_ROLL_SCORE) {
            throw new BoardValidationException(
                    "Roll scores contain invalid score (value: {0}). Scores must be between 0 and 10", score);
        }
    }

    static boolean isMaxScore(Integer score) {
        return score == MAX_ROLL_SCORE;
    }

    @Override
    public String toString() {
        return "Frame [" +
                "r1=" + rollScore1 +
                ", r2=" + rollScore2 +
                ", score=" + rollScore +
                ", strike/spare?=" + (isStrike() ? "strike" : (isSpare() ? "spare" : "N/A")) +
                ", frameScore=" + frameScore +
                ']';
    }
}
