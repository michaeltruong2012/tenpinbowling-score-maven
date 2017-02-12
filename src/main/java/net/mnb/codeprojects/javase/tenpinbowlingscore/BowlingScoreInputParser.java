package net.mnb.codeprojects.javase.tenpinbowlingscore;

import java.util.Arrays;
import java.util.Optional;

/**
 * @author Michael Truong
 */
class BowlingScoreInputParser {

    static Optional<BowlingScoreBoard> parse(String[] args) {
        if (args == null || args.length != 1) {
            System.err.println("ERROR: Only 1 argument is allowed");
            return Optional.empty();
        }

        if (args[0] == null || args[0].trim().isEmpty()) {
            System.err.println("ERROR: The argument cannot be empty or blank");
            return Optional.empty();
        }

        String[] rollScores = parseRollScore(args[0]);
        return Optional.of(new BowlingScoreBoard(rollScores));
    }

    static String[] parseRollScore(String arg) {
        String[] rollScores = arg.split(" ");

        rollScores = Arrays.stream(rollScores)
                .filter(s -> s != null && !s.isEmpty())
                .toArray(String[]::new);
        System.out.println("INFO: The actual input argument is " + Arrays.toString(rollScores));

        return rollScores;
    }
}
