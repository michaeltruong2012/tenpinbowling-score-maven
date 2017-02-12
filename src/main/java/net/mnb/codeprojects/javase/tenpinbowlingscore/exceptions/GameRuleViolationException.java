package net.mnb.codeprojects.javase.tenpinbowlingscore.exceptions;

/**
 * Exception when a game rule is violated or not conformed. For example, if a spare roll is scored in the last frame,
 * a bonus roll must be played or this exception is raised.
 *
 * @author Michael Truong
 */
public class GameRuleViolationException extends BaseRuntimeException {
    public GameRuleViolationException(String message, Object... params) {
        super(message, params);
    }
}
