package org.blender.exception;

public class BlenderException extends RuntimeException {

    public BlenderException(Throwable t) {
        super(t);
    }

    public BlenderException(String msg) {
        super(msg);
    }
}
