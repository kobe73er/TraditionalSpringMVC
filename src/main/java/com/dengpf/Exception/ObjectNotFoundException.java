package com.dengpf.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by kobe73er on 16/10/27.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "This object is not founded")
public class ObjectNotFoundException extends RuntimeException {
    /**
     * Unique ID for Serialized object
     */
    private static final long serialVersionUID = -8790211652911971729L;

    public ObjectNotFoundException(int personId) {
        super("person id: "+personId + " not found");
    }
}
