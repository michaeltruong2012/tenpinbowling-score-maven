package net.mnb.codeprojects.javase.tenpinbowlingscore.exceptions;

import java.text.MessageFormat;

/**
 * @author Michael Truong
 */
public class BoardValidationException extends RuntimeException {

    public BoardValidationException(String message, Object... params) {
        super(MessageFormat.format(message, params));
    }
}
