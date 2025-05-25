package com.project.template.exception;

/**
 * @author Pravesh Shashiraj Boodhoo
 */

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }

}