package net.mnb.codeprojects.javase.tenpinbowlingscore.exceptions;

import java.text.MessageFormat;

/**
 * Base class for all runtime exceptions in the application.
 *
 * @author Michael Truong
 */
public abstract class BaseRuntimeException extends RuntimeException {
    public BaseRuntimeException(String message, Object... params) {
        super(MessageFormat.format(message, params));
    }
}
