package com.kenticocloud.delivery.models.exceptions;

public class KenticoCloudException extends RuntimeException {
    public KenticoCloudException(String message, Throwable cause){ super(message, cause);}
}
