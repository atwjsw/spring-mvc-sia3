package com.habuma.spitter.mvc;

/**
 * Created by wenda on 7/31/2017.
 */
public class ImageUploadException extends RuntimeException {

    public ImageUploadException(String message) {
        super(message);
    }

    public ImageUploadException(String message, Throwable cause) {
        super(message, cause);
    }
}
