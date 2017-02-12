package net.mnb.codeprojects.javase.tenpinbowlingscore.exceptions;

/**
 * Exception when a validation rule of a score board is violated.
 *
 * @author Michael Truong
 */
public class BoardValidationException extends BaseRuntimeException {
    public BoardValidationException(String message, Object... params) {
        super(message, params);
    }
}
