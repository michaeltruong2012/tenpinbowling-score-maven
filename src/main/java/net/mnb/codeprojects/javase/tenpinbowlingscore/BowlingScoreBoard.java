package net.mnb.codeprojects.javase.tenpinbowlingscore;

import net.mnb.codeprojects.javase.tenpinbowlingscore.exceptions.BoardValidationException;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static net.mnb.codeprojects.javase.tenpinbowlingscore.BowlingScoreFrame.isMaxScore;

/**
 * @author Michael Truong
 */
class BowlingScoreBoard {
    private static final int MAX_FRAME_COUNT = 10;
    private static final int MAX_FRAME_WITH_BONUS_COUNT = MAX_FRAME_COUNT + 2;
    private static final int MAX_ROLL_COUNT = MAX_FRAME_WITH_BONUS_COUNT * 2;

    private List<BowlingScoreFrame> scoreFrames = new LinkedList<>();

    BowlingScoreBoard(String[] rollScores) {
        ensureValidRollScores(rollScores);
        initFrames(rollScores);

        System.out.println("INFO: Bowling board");
        scoreFrames.stream().forEach(f -> System.out.println("INFO: " + f));
    }

    private void ensureValidRollScores(String[] rollScores) {
        if (rollScores.length > MAX_ROLL_COUNT) {
            throw new BoardValidationException("ERROR: Only {0} roll scores (including bonus rolls) can be set", MAX_ROLL_COUNT);
        }
    }

    private void initFrames(String[] rollScores) {
        addSeedFrame();

        try {
            String[] fullRollScores = Arrays.copyOf(rollScores, MAX_ROLL_COUNT);
            Arrays.stream(fullRollScores)
                    .map(s -> s == null ? "0" : s.trim())
                    .mapToInt(Integer::parseInt)
                    .forEach(this::completeOrAddFrame);

        } catch (NumberFormatException e) {
            throw new BoardValidationException("Non numeric string detected! All scores must be numeric");
        }
    }

    private void completeOrAddFrame(int score) {
        BowlingScoreFrame lastFrame = scoreFrames.get(scoreFrames.size() - 1);
        if (lastFrame.hasAllScores()) {
            if (isMaxScore(score)) {
                scoreFrames.add(BowlingScoreFrame.newStrike());
            } else {
                scoreFrames.add(new BowlingScoreFrame(score));
            }
        } else {
            lastFrame.setRollScore2(score);
        }
    }

    private void addSeedFrame() {
        scoreFrames.add(new BowlingScoreFrame(0, 0)); // seed frame
    }

    int calculateFrameScores() {
        int totalScore = 0;
        for (int i = 1; i <= MAX_FRAME_COUNT; i++) {
            BowlingScoreFrame currentFrame = scoreFrames.get(i);
            BowlingScoreFrame previousFrame = scoreFrames.get(i - 1);
            BowlingScoreFrame nextFrame = scoreFrames.get(i + 1);
            BowlingScoreFrame followingFrame = scoreFrames.get(i + 2);

            totalScore = previousFrame.getFrameScore() + currentFrame.getRollScore();

            if (currentFrame.isStrike()) {
                totalScore += nextFrame.getRollScore1();
                if (nextFrame.isStrike()) {
                    totalScore += followingFrame.getRollScore1();
                } else {
                    totalScore += nextFrame.getRollScore2();
                }
            } else if (currentFrame.isSpare()) {
                totalScore += nextFrame.getRollScore1();
            }

            currentFrame.setFrameScore(totalScore);
        }

        return totalScore;
    }

    List<BowlingScoreFrame> getScoreFrames() {
        return scoreFrames;
    }
}
