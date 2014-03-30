package org.blender.exception;

public class BlendingException extends RuntimeException {

    public BlendingException(Throwable t) {
        super(t);
    }

    public BlendingException(String msg) {
        super(msg);
    }
}
