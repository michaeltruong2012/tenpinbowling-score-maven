package net.mnb.codeprojects.javase.tenpinbowlingscore;

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

        return Optional.of(new BowlingScoreBoard(args[0].split(" ")));
    }
}
