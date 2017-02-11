package net.mnb.codeprojects.javase.tenpinbowlingscore;

import net.mnb.codeprojects.javase.tenpinbowlingscore.exceptions.BoardValidationException;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @author Michael Truong
 */
public class BowlingScoreBoard {
    private static final int MAX_FRAME_COUNT = 10;
    private static final int MAX_FRAME_WITH_BONUS_COUNT = MAX_FRAME_COUNT + 2;
    private static final int MAX_ROLL_COUNT = MAX_FRAME_WITH_BONUS_COUNT * 2;

    private List<BowlingScoreFrame> scoreFrames = new LinkedList<>();

    public BowlingScoreBoard(String[] rollScores) {
        ensureValidRollScores(rollScores);
        initFrames(rollScores);
    }

    private void ensureValidRollScores(String[] rollScores) {
        if (rollScores.length > MAX_ROLL_COUNT) {
            throw new BoardValidationException("ERROR: Only {0} roll scores (including bonus rolls) can be set", MAX_ROLL_COUNT);
        }
    }

    private void initFrames(String[] rollScores) {
        List<Integer> fullRollScores = new LinkedList<>();
        try {
            IntStream.range(0, MAX_ROLL_COUNT)
                    .mapToObj(i -> i >= rollScores.length ? "0" : rollScores[i].trim())
                    .map(Integer::parseInt)
                    .forEach(n -> {
                        fullRollScores.add(n);
                        if (n == BowlingScoreFrame.MAX_ROLL_SCORE) {
                            fullRollScores.add(0);
                        }
                    });

        } catch (NumberFormatException e) {
            throw new BoardValidationException("Non numeric string detected! All scores must be numeric");
        }

//        IntStream.iterate(0, i -> i + 2)
//                .limit(MAX_ROLL_COUNT)
//                .mapToObj(i -> new BowlingScoreFrame(fullRollScores.get(i), fullRollScores.get(i + 1)))
//                .forEach(f -> scoreFrames.add(f));

        for (int i = 0; i < MAX_ROLL_COUNT; i += 2) {
            scoreFrames.add(new BowlingScoreFrame(fullRollScores.get(i), fullRollScores.get(i + 1)));
        }

        scoreFrames.add(0, new BowlingScoreFrame(0, 0)); // seed frame
    }

    public int calculateFrameScores() {
        int totalScore = 0;
        for (int i = 1; i <= BowlingScoreBoard.MAX_FRAME_COUNT; i++) {
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